package spring.douban;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import spring.dao.RedisRepository;
import spring.dto.Comment;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.*;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/3/21<br/>
 * Time: 17:32<br/>
 * To change this template use File | Settings | File Templates.
 * JOIN link demo
 * <
 * href="https://www.douban.com/group/xingzuo/?action=join&amp;redir=http%3A//www.douban.com/group/topic/130126269/&amp;ck=GYMr" class="bn-join">加入小组</a>
 * <p>
 * 未加入的时候
 * <div class="member-status">
 * 加入成功后， 302跳转https://www.douban.com/group/topic/130126269/ 包含
 * <div class="member-info1">我是小组的成员</div>
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring.xml")
@WebAppConfiguration
@PropertySource(value = "classpath:redis.properties")
public class DouBanServiceTest {

    @Autowired
    private DouBanService douBanService;


    @Autowired
    private RedisRepository redisRepository;

    static String doubanLogonCookies = "doubanLogonCookies";

    @Test
    public void downloadThisLink() throws IOException {
        Connection.Method httpMethod = Connection.Method.POST;
        String url = "https://www.douban.com/group/topic/130126269/";
        String doubanCookie = douBanService.getRedis("doubanCookie");
        Map<String, String> cookies = MapConvertFile.string2HashMap(doubanCookie);
        Connection.Response html = douBanService.downloadThisLink(url, httpMethod, cookies);
        Document doc = html.parse();
        Element body = doc.body();
        System.err.println("body = " + body);
        Elements memberStatus = doc.getElementsByClass("member-status");
        Elements memberInfo = doc.getElementsByClass("member-info1");
        if (memberStatus == null && memberInfo == null) {
            System.err.println("DouBanServiceTest.downloadThisLink both elements null, weird");
            return;
        }
        if (memberInfo != null) {
            System.err.println("LOGIN memberInfo = " + memberInfo.toString());
        } else {
            System.err.println("NOT LOGIN memberStatus = " + memberStatus);
        }
    }


    //    @Test
    public void callCommentTest() throws IOException, ClassNotFoundException {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("testKey", "keyValue");
        MapConvertFile.outputFile(map, "testFileYuzhen");
    }

    //    @Test
    public void toStringList() {
        String[] arr = {"form_email", "sail456852@hotmail.com", "form_password", "i1234567",
                "source", "index_nav", "redir", "https://www.douban.com", "source", "None"};
        List<String> dataList = new ArrayList<>(Arrays.asList(arr));
//        dataList.addAll(new ArrayList<String>(){{add("captcha-id"); add("testcaptchaid");}});
        dataList.add("captcha-id");
        dataList.add("testcaptchaid");
        System.err.println("dataList = " + dataList);
    }

    //    @Test
    public void readFromCookies() throws IOException, ClassNotFoundException {
        InputStream resourceAsStream = this.getClass().getClassLoader()
                .getResourceAsStream(doubanLogonCookies);
        ObjectInputStream s = new ObjectInputStream(resourceAsStream);
        HashMap<String, String> dlCookies = (HashMap<String, String>) s.readObject();
        System.err.println("dlCookies = " + dlCookies);
    }

    //    @Test
    public void writeToCookies() throws IOException, ClassNotFoundException {
        InputStream resourceAsStream = this.getClass().getClassLoader()
                .getResourceAsStream("");
        ObjectInputStream s = new ObjectInputStream(resourceAsStream);
        HashMap<String, String> dlCookies = (HashMap<String, String>) s.readObject();
        System.err.println("dlCookies = " + dlCookies);
    }

    @Test
    public void addTopicUrl() {
        douBanService.addTopicUrl("fake");
    }


    /**
     *  won't wired on spring only
     */
    @Test
    public void saveCommentListToRedis() {
        ArrayList<Comment> comments = new ArrayList<>();
        Comment comment = new Comment();
        comment.setContent("Comment Content example");
        comment.setUrl("http://example.com");
        Comment comment2 = new Comment();
        comment2.setContent("Comment Content 2 example");
        comment2.setUrl("http://example2.com");
        comments.add(comment);
        comments.add(comment2);
//        douBanService.saveCommentListToRedis(comments);
//        douBanService.findAllComments();
    }
}