package msc.httpClient;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.impl.client.BasicCookieStore;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/1/8<br/>
 * Time: 17:44<br/>
 * To change this template use File | Settings | File Templates.
 */
public class DoubanTest {


    @Test
    public void javaSoupDouban() throws IOException {
        String url = "https://www.douban.com/doumail/";

        Map<String, String> params = setRequestParam();
        Connection conn = Jsoup.connect(url);
        conn.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
        conn.data(params);
        String cstr = "ll=118282; bid=4Mjyx_dTshw; ps=y; " +
//                "__utmc=30149280; __utmz=30149280.1546919714.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none);" +
                " _ga=GA1.2.499911770.1546919714; _gid=GA1.2.1019472763.1546919793; ck=fnPz; push_noty_num=0; push_doumail_num=0;" +
                " __utmv=30149280.17623; douban-profile-remind=1; _pk_ses.100001.8cb4=*; ap_v=0,6.0; " +
                "__utma=30149280.499911770.1546919714.1546940298.1546997720.3; __utmt=1;" +
                " _pk_id.100001.8cb4=513fab81cc411b78.1546919714.3.1546997869.1546940468.; __utmb=30149280.10.10.1546997720";

        Map<String, String> cookieMap = CookieUtil.cookieStrToMap(cstr);
        Map<String, String> cookieMapNew = new HashMap<>();
        cookieMapNew.putAll(cookieMap);
        cookieMapNew.put("__utmz=", "30149280.1546919714.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)");
        conn.cookies(cookieMapNew);
        Document document = conn.get();
        System.err.println("document = " + document);
    }

    @Test
    public void okHttp() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://www.douban.com/doumail/")
                .get()
                .addHeader("Connection", "keep-alive")
                .addHeader("Pragma", "no-cache")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .addHeader("Referer", "https://www.douban.com/people/176233839/")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Accept-Language", "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7,ja;q=0.6,zh-TW;q=0.5,ru;q=0.4")
                .addHeader("Cookie", "ll=\"118282\"; bid=4Mjyx_dTshw; ps=y; __utmc=30149280; __utmz=30149280.1546919714.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _ga=GA1.2.499911770.1546919714; _gid=GA1.2.1019472763.1546919793; ck=fnPz; push_noty_num=0; push_doumail_num=0; __utmv=30149280.17623; douban-profile-remind=1; _pk_ses.100001.8cb4=*; ap_v=0,6.0; __utma=30149280.499911770.1546919714.1546940298.1546997720.3; __utmt=1; _pk_id.100001.8cb4=513fab81cc411b78.1546919714.3.1546998613.1546940468.; __utmb=30149280.14.10.1546997720")
                .addHeader("cache-control", "no-cache")
                .addHeader("Postman-Token", "509eac00-708c-40c7-a42c-0414b895a023")
                .build();

        Response response = client.newCall(request).execute();
        byte[] bytes = response.body().bytes();
        String info = new String(bytes, "UTF-8");
        System.err.println("info = " + info);
    }

    @NotNull
    private Map<String, String> setRequestParam() {
        Map<String, String> params = new HashMap<>();
        params.put("ck", "fnPz");
        params.put("k", "176233839:6645873e4815c0cf2257c526e58b9a1ddb73928e");
        params.put("from_push", "undefined");
        params.put("callback", "jsonp_j0x74alhmjxly0y");
        return params;
    }

//    @Test
//    public void javaSoup() throws IOException {
//        Document doc = Jsoup.connect("http://example.com")
//                .data("query", "Java")
//                .userAgent("Mozilla")
//                .cookie("auth", "token")
//                .timeout(3000)
//                .post();
//        System.err.println("doc = " + doc);
//    }


    /**
     * https://stackoverflow.com/questions/7139178/jsoup-cookies-for-https-scraping/10533366
     *
     * @throws IOException
     */
    @Test
    public void stackOverFlow() throws IOException {

        //This will get you the response.
        Connection.Response res = Jsoup
                .connect("url")
                .data("loginField", "login@login.com", "passField", "pass1234")
                .method(Connection.Method.POST)
                .execute();
        //This will get you cookies
        Map<String, String> cookies = res.cookies();
        //And this is the easieste way I've found to remain in session
        Document doc = Jsoup.connect("url").cookies(cookies).get();
    }

}
