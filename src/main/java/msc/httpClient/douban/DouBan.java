package msc.httpClient.douban;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * 描述:
 *
 * @outhor lizhichao
 * @create 2018-05-18 16:33
 */
public class DouBan {

    public static void main(String[] args) throws IOException {
        login();
    }

    public static void login() throws IOException {
        String url = "https://accounts.douban.com/login";
        Connection connect = Jsoup.connect(url);
        Connection method = connect.method(Connection.Method.POST).timeout(10000);
        Connection.Response response = method.execute();
        Map<String, String> cookies = response.cookies();

        System.out.println(cookies);
        Document tempDoc = response.parse();
        Element imgEle = tempDoc.getElementById("captcha_image");
        Elements attribute = tempDoc.select("input[name]");
        String next = null;
        String captcha = "";
        if (imgEle != null) {
            for (Element element : attribute) {
                if (element.attr("name").toString().equals("captcha-id")) {
                    captcha = element.attr("value");
                }
            }
            String src = imgEle.attr("src");
            System.out.println("验证码链接：" + src + "\n请输入验证码");
            Scanner sc = new Scanner(System.in);
            next = sc.next();
            System.out.println(next);
        }

        Connection.Response loginResponse;
        //如果没有验证码
        if (next == null) {
            loginResponse = Jsoup
                    .connect(url)
                    .data("form_email", "13600157170", "form_password", "chaoge123",
                            "source", "index_nav", /*"captcha-id", captcha,"captcha-solution",next,*/
                            "redir", "https://www.douban.com", "source", "None").cookies(cookies)
                    .method(Connection.Method.POST).execute();

            cookies = loginResponse.cookies();
//            System.out.println(loginResponse.cookies());
        } else {
            loginResponse = Jsoup
                    .connect(url)
                    .data("form_email", "13600157170", "form_password", "chaoge123",
                            "source", "index_nav", "captcha-id", captcha, "captcha-solution", next,
                            "redir", "https://www.douban.com", "source", "None").cookies(cookies)
                    .method(Connection.Method.POST).execute();

            cookies = loginResponse.cookies();
        }
        for (int i = 1; i <= 2; i++) {
            huitie(cookies, "https://www.douban.com/group/topic/131119511/", "yuzhen up====" + i);
            System.out.println("回帖第" + i + "次");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void huitie(Map<String, String> cookies, String url, String rv_comment) throws IOException {
        Connection.Response execute1 = Jsoup.connect(url).method(Connection.Method.GET).cookies(cookies).execute();

        Document parse = execute1.parse();
        Element imgEle = parse.getElementById("captcha_image");
        Elements attribute = parse.select("input[name]");

        String next = null;
        String captcha = "";
        if (imgEle != null) {
            for (Element element : attribute) {
                if (element.attr("name").toString().equals("captcha-id")) {
                    captcha = element.attr("value");
                }
            }
            String src = imgEle.attr("src");
            System.out.println("验证码链接：" + src + "\n请输入验证码");
            Scanner sc = new Scanner(System.in);
            next = sc.next();
            System.out.println(next);
        }
        Elements select = parse.select("input[name]");
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
        Connection.Response execute2;
        if (next != null) {
            execute2 = Jsoup.connect(url + "/add_comment#last").data(
                    "ck", ck, "rv_comment", rv_comment, "start", "0", "submit_btn", "发送", "captcha-id", captcha, "captcha-solution", next
            ).method(Connection.Method.POST).cookies(cookies).execute();
        } else {
            execute2 = Jsoup.connect(url + "/add_comment#last").data(
                    "ck", ck, "rv_comment", rv_comment, "start", "0", "submit_btn", "发送"
            ).method(Connection.Method.POST).cookies(cookies).execute();
        }
        /*System.out.println(execute2.body());*/
    }
}
