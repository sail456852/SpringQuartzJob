package msc.email;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import javax.mail.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/27<br/>
 * Time: 17:35<br/>
 * To change this template use File | Settings | File Templates.
 */
public class SimpleMailSender {

    static final Log log = LogFactory.getLog(SimpleMailSender.class);

    private static final String FROM_NAME = "YUZHEN";

    /**
     * 以HTML格式发送邮件
     *
     * @param mailInfo
     *            待发送的邮件信息
     */
    public static boolean sendHtmlMail(MailSenderInfo mailInfo) throws MessagingException {

        // 构造一个发送邮件的session
        Session sendMailSession = Session.getInstance(mailInfo.getProperties());
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress(),FROM_NAME);
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 邮件接收者有多个，以逗号隔开
            String[] toAddress = splitStr(mailInfo.getToAddress(), ",");
            int len = toAddress.length;
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address[] to = new InternetAddress[len];
            for (int i = 0; i < len; i++) {
                to[i] = new InternetAddress(toAddress[i]);
            }
            // Message.RecipientType.TO属性表示接收者的类型为TO
            mailMessage.setRecipients(Message.RecipientType.TO, to);


            // 邮件抄送者有多个，以逗号隔开
            if(!StringUtils.isEmpty(mailInfo.getCcAddress())) {
                String[] ccAddress = splitStr(mailInfo.getCcAddress(), ",");
                int cclen = ccAddress.length;
                // 创建邮件的接收者地址，并设置到邮件消息中
                Address[] cc = new InternetAddress[cclen];
                for (int i = 0; i < cclen; i++) {
                    cc[i] = new InternetAddress(ccAddress[i]);

                }
                // Message.RecipientType.CC属性表示抄送者的类型为CC
                mailMessage.setRecipients(Message.RecipientType.CC, cc);
            }



            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());

            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(mailInfo.getContent(), "text/html;   charset=GBK");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            Transport.send(mailMessage);
            log.info(mailMessage.toString());
            return true;
        } catch (MessagingException ex) {
            log.error("MessagingException", ex);
            ex.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 以html发送邮件(带附件)
     *
     * @param mailInfo
     *            待发送的邮件的信息
     */
    public  static boolean sendHtmlAndAffixMail(MailSenderInfo mailInfo) {
        // 判断是否需要身份认证
        MailAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if(mailInfo.isValidateSSL()){
            pro.put("mail.smtp.starttls.enable","true");
            pro.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        // 如果需要身份认证，则创建一个密码验证器
        if (mailInfo.isValidate()) {
            authenticator = new MailAuthenticator(mailInfo.getUserName(),
                    mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session session = Session.getDefaultInstance(pro, authenticator);
        try {
            MimeMessage msg = new MimeMessage(session); // 构造MimeMessage 并设定基本的值
            // MimeMessage msg = new MimeMessage();
            msg.setFrom(new InternetAddress(mailInfo.getFromAddress()));
            // msg.addRecipients(Message.RecipientType.TO, address);
            // 邮件接收者有多个，以逗号隔开
            String[] toAddress = splitStr(mailInfo.getToAddress(), ",");
            int len = toAddress.length;
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address[] to = new InternetAddress[len];
            for (int i = 0; i < len; i++) {
                to[i] = new InternetAddress(toAddress[i]);
            }
            msg.setRecipients(Message.RecipientType.TO,to);
            msg.setSubject(MimeUtility.encodeText(mailInfo.getSubject()));
            Multipart mp = new MimeMultipart();// 构造Multipart
            MimeBodyPart mbpContent = new MimeBodyPart();// 向Multipart添加正文
            mbpContent.setContent(mailInfo.getContent(),
                    "text/html;charset=GB2312");
            mp.addBodyPart(mbpContent);// 向MimeMessage添加（Multipart代表正文）
            Vector file = mailInfo.getAttachFileNames();
            Enumeration efile = file.elements();// 向Multipart添加附件
            while (efile.hasMoreElements()) {
                MimeBodyPart mbpFile = new MimeBodyPart();
                String filename = efile.nextElement().toString();
                System.out.println(filename.toLowerCase());
                FileDataSource fds = new FileDataSource(filename);
                mbpFile.setDataHandler(new DataHandler(fds));
                System.out.println(fds.getName());
                mbpFile.setFileName(MimeUtility.encodeText(fds.getName()));
                // 向MimeMessage添加（Multipart代表附件）
                mp.addBodyPart(mbpFile);
            }
            file.removeAllElements();
            // 向Multipart添加MimeMessage
            msg.setContent(mp);
            msg.setSentDate(new Date());
            msg.saveChanges();
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            transport.connect(mailInfo.getMailServerHost(), mailInfo
                    .getUserName(), mailInfo.getPassword());
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }


    public static String[] splitStr(String str, String delimiter) {

        String[] value = null;
        if (str != null && !"".equals(str) && delimiter != null
                && !"".equals(delimiter)) {
            value = str.split(delimiter);
        }

        return value;
    }
}
