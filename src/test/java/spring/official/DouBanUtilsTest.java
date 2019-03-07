package spring.official;

import org.junit.Test;
import spring.douban.MapConvertFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/3/7<br/>
 * Time: 19:11<br/>
 * To change this template use File | Settings | File Templates.
 */
public class DouBanUtilsTest {

    static String doubanLogonCookies = "doubanLogonCookies";

    @Test
    public void callCommentTest() throws IOException, ClassNotFoundException {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("testKey", "keyValue");
        MapConvertFile.outputFile(map, "testFileYuzhen");
    }

    @Test
    public void toStringList() {
        String[] arr = {"form_email", "sail456852@hotmail.com", "form_password", "i1234567",
                "source", "index_nav", "redir", "https://www.douban.com", "source", "None"};
        List<String> dataList = new ArrayList<>(Arrays.asList(arr));
//        dataList.addAll(new ArrayList<String>(){{add("captcha-id"); add("testcaptchaid");}});
        dataList.add("captcha-id");
        dataList.add("testcaptchaid");
        System.err.println("dataList = " + dataList);
    }

    @Test
    public void readFromCookies() throws IOException, ClassNotFoundException {
        InputStream resourceAsStream = this.getClass().getClassLoader()
                .getResourceAsStream(doubanLogonCookies);
        ObjectInputStream s = new ObjectInputStream(resourceAsStream);
        HashMap<String, String> dlCookies = (HashMap<String, String>) s.readObject();
        System.err.println("dlCookies = " + dlCookies);
    }

    @Test
    public void writeToCookies() throws IOException, ClassNotFoundException {
        InputStream resourceAsStream = this.getClass().getClassLoader()
                .getResourceAsStream("");
        ObjectInputStream s = new ObjectInputStream(resourceAsStream);
        HashMap<String, String> dlCookies = (HashMap<String, String>) s.readObject();
        System.err.println("dlCookies = " + dlCookies);
    }

}
