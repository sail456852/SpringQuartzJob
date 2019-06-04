package spring.douban;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * 描述:
 *
 * @outhor lizhichao
 * @create 2018-05-18 16:33
 */
//@PropertySource(value = "classpath:redis.properties")
@Service
public class DouBanService {

    private static Logger logger;
    private static Random random = new Random(100);
    private static boolean calledByJob = false;

    @Autowired
    private RedisTemplate redisTemplate;

    static {
        logger = LoggerFactory.getLogger(DouBanService.class);
    }

    public static void main(String[] args) {
        List<String> urls = new ArrayList<>();
        urls.add("https://www.douban.com/group/topic/127050074/");
        System.err.println("DouBanService.main Hello World main is running!");
    }


    /**
     * check if current account joined topic page
     *
     * @param url topic page url
     * @param cookies
     * @return
     */
    public boolean checkJoinedGroup(String url, Map<String, String> cookies) throws IOException {
        Connection.Response html = downloadThisLink(url, Connection.Method.POST, cookies);
        Document doc = html.parse();
        Elements memberStatus = doc.getElementsByClass("member-status");
        if (memberStatus == null) {
            return true;
        }
        System.err.println("haven't joined memberStatus = " + memberStatus.toString());
        String a = memberStatus.attr("a");
        System.err.println("a = " + a);
        return false;
    }

    /**
     * This only saves login cookie to redis
     *
     * @param username
     * @param password
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void login(String username, String password) throws IOException, ClassNotFoundException {
        String url = "https://accounts.douban.com/login";
        String urlLogin = "https://accounts.douban.com/j/mobile/login/basic"; // unsupported
        Connection connect = Jsoup.connect(url);
        Connection method = connect.method(Connection.Method.POST).timeout(10000);
        Connection.Response response = method.execute();
        Map<String, String> cookies = response.cookies();
        System.err.println("cookies map before entering password = " + cookies);

        Document tempDoc = response.parse();
        Element imgEle = tempDoc.getElementById("captcha_image");
        Elements attribute = tempDoc.select("input[name]");

        Connection.Response loginResponse;
        Map<String, String> logonCookie;

        String captchaId = "";
        String captchaCode = null;

        if (imgEle != null) {
            for (Element element : attribute) {
                if (element.attr("name").toString().equals("captcha-id")) {
                    captchaId = element.attr("value");
                }
            }
            String src = imgEle.attr("src");
            System.out.println("验证码链接：" + src + "\n请输入验证码");
            Scanner sc = new Scanner(System.in);
            captchaCode = sc.next();
            System.out.println(captchaCode);
        }

        Map<String, String> dataMap = new HashMap<String, String>() {{
            put("name", username);
            put("password", password);
            put("ck", "");
            put("ticket", "");
            put("remember", "true");
        }};

        if (captchaCode == null) {
            System.err.println("DouBanUtils.login no captcha yeah!");
            if (calledByJob) {
                logger.info("DouBanUtils.login no captcha yeah!");
            }
        } else {
            System.err.println("DouBanUtils.login calling comment function captcha is not empty");
            if (calledByJob) {
                logger.info("DouBanUtils.login calling comment function captcha is not empty");
            }
            dataMap.put("captcha-id", captchaId);
            dataMap.put("captcha-solution", captchaCode);
        }

        loginResponse = Jsoup
                .connect(urlLogin).ignoreContentType(true)
                .data(dataMap).cookies(cookies)
                .method(Connection.Method.POST).execute();

        // Set-Cookie: dbcl2="64125560:CgzGQ0FFqqM"; path=/;
        // domain=.douban.com; expires=Tue, 02-Jul-2019 15:22:28 GMT; httponly
        logonCookie = loginResponse.cookies();
        // save to redis for later user
        if(logonCookie == null || logonCookie.size() == 0 ){
            logger.info("login cookie size is 0, won't set redis logonCookie");
            return;
        }else{
            ValueOperations valueOperations = redisTemplate.opsForValue();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.putAll(logonCookie);
            String mapStr = linkedHashMap.toString();
            System.err.println("mapStr = " + mapStr);
            valueOperations.set("doubanCookie", mapStr);
        }
    }


    public void callComment(boolean calledByJob, List<String> urls) throws IOException, ClassNotFoundException {
        DouBanService.calledByJob = calledByJob;
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object doubanCookie = valueOperations.get("doubanCookie");
        if (doubanCookie == null) {
            System.err.println("redis no cookie! return!");
            return;
        }

        Map<String, String> doubanCookieMap = null;
        String cookieString = doubanCookie.toString();
        if(cookieString.contains(";")){
            doubanCookieMap = MapConvertFile.string2HashMapColon(cookieString);
        }else{
            doubanCookieMap = MapConvertFile.string2HashMap(cookieString);
        }


        System.err.println("doubanCookieMap = " + doubanCookieMap);
        if (!calledByJob) {
            logger.info("called by test");
            for (String url : urls) {
                try {
                    Thread.sleep(3000);
                    huitie(doubanCookieMap, url, "6点后可看房。联系VX");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            logger.info("called by job");
            int count = 0;
            for (String url : urls) {
                count++;
                int i = random.nextInt(100);
                try {
                    Thread.sleep(3000 + i);
                    if (count > 2) {
                        Thread.sleep(30000 + i);  // 30s each
                    }
                    huitie(doubanCookieMap, url, "up");
                    System.err.println("DouBanUtils.callComment sleeping 1 min!");
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void huitie(Map<String, String> cookies, String url, String rv_comment) throws IOException {
        Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).cookies(cookies).execute();
        Document doc = response.parse();
        Element imgEle = doc.getElementById("captcha_image");
        Elements attribute = doc.select("input[name]");
        String captchaCode = null;
        String captcha = "";
        if (imgEle != null) {
            if (calledByJob) {
                logger.info("captcha is not empty before commenting, cancel this try this time, refresh cookie please");
                return;
            }
            for (Element element : attribute) {
                if (element.attr("name").toString().equals("captcha-id")) {
                    captcha = element.attr("value");
                }
            }
            String src = imgEle.attr("src");
            System.out.println("验证码链接：" + src + "\n Please input captcha: ");
            Scanner sc = new Scanner(System.in);
            captchaCode = sc.next();
            System.out.println(captchaCode);
            if(StringUtils.equals("exit", captchaCode)) {
                logger.info("huitie() exit == captchaCode quitting hui tie ");
                return;
            }
        }
        Elements select = doc.select("input[name]");
        String ck = "";
        for (Element element : select) {
            if (element.attr("name").equals("ck")) {
                ck = element.attr("value");
                System.out.println(ck);
            }
        }
        cookies.put("ps", "y");
        cookies.put("ap", "1");
        cookies.put("as", url);
        Connection.Response commentResponse;
        if (captchaCode != null) {
            logger.info("commenting captcha code not empty");
            commentResponse = Jsoup.connect(url + "/add_comment#last").data(
                    "ck", ck, "rv_comment", rv_comment, "start", "0", "submit_btn", "发送", "captcha-id", captcha, "captcha-solution", captchaCode
            ).method(Connection.Method.POST).cookies(cookies).execute();
        } else {
            logger.info("commenting captcha code is empty");
            commentResponse = Jsoup.connect(url + "/add_comment#last").data(
                    "ck", ck, "rv_comment", rv_comment, "start", "0", "submit_btn", "发送"
            ).method(Connection.Method.POST).cookies(cookies).execute();
        }
        String comment = url.replaceAll("comment", "");
        if (calledByJob) {
            logger.info("huitie() \"comment\": " + comment);
        }
        System.err.println("comment = " + comment + "  finished ");
    }


    public List<String> getTieziKeysRedis() {
        Set<String> redisKeys = redisTemplate.keys("d*");
        // Store the keys in a List
        List<String> keysList = new ArrayList<>();
        Iterator<String> it = redisKeys.iterator();
        while (it.hasNext()) {
            String data = it.next();
            if (!StringUtils.isEmpty(data) && data.length() <= 3)
                keysList.add(data);
        }
        for (String s : keysList) {
            System.err.println("s = " + s);
        }
        return keysList;
    }

    public List<String> getTieziUrlsRedis(List<String> keys) {
        ArrayList<String> list = new ArrayList<>();
        for (String key : keys) {
            ValueOperations valueOperations = redisTemplate.opsForValue();
            Object value = valueOperations.get(key);
            if (value != null)
                list.add(value.toString());
        }
        return list;
    }

    public String getRedis(String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object value = valueOperations.get(key);
        return value == null ? null : value.toString();
    }

    public void setRedis(String key, String value, long timeOut) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, timeOut);
    }

    private void printRangeKeys(ValueOperations valueOperations) {
        for (int i = 1; i <= 5; i++) {
            Object di = valueOperations.get("d" + i);
            if (di == null) {
                try {
                    Thread.sleep(1000);
                    System.err.println("TestJobDetail.timedJob2 is null");
                    return;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.err.println("di = " + di);
        }
    }


    /**
     * 下载这个网页
     *
     * @param url
     * @param cookies
     * @return
     */
    public Connection.Response downloadThisLink(String url, Connection.Method httpMethod, Map<String, String> cookies) {
        try {
            Connection connect = Jsoup.connect(url);
            Connection method = connect.method(httpMethod).timeout(10000);
            Connection.Response response;
            if (null == cookies)
                response = method.execute();
            else
                response = Jsoup.connect(url).method(httpMethod).cookies(cookies).execute();
            return response;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

