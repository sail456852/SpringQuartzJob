package msc.email;

import java.util.Properties;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/27<br/>
 * Time: 17:39<br/>
 * To change this template use File | Settings | File Templates.
 */
public class MailSenderInfo {

    // 发送邮件的服务器的IP
    private static final String MAILSERVERHOST = "mail.yeahka.com";
    private static final String MAILSERVERHOST_IP = "113.98.254.124";
    // 端口
    private static final String MAILSERVERPORT = "25";
    // 邮件发送者的地址
    private static final String FROMADDRESS = "report@yeahka.com";
    // 邮件接收者的地址
    private String toAddress;
    // 邮件抄送者的地址
    private String ccAddress;
    // 登陆邮件发送服务器的用户名和密码
    private static final String USERNAME = "pos@yeahka.com";
    private static final String PASSWORD = "";
    // 是否需要身份验证
    private boolean validate = true;
    // 邮件主题
    private String subject;
    // 邮件的文本内容
    private String content;

    // 邮件附件的文件名
    @SuppressWarnings("rawtypes")
    private Vector attachFileNames;
    // 是否启用ssl
    private boolean validateSSL = false;

    /**
     * 获得邮件会话属性
     */
    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", MAILSERVERHOST);
        p.put("mail.smtp.localhost", MAILSERVERHOST_IP);
        p.put("mail.smtp.port", MAILSERVERPORT);

        return p;
    }

    public String getMailServerHost() {
        return MAILSERVERHOST;
    }

    public String getMailServerPort() {
        return MAILSERVERPORT;
    }

    public String getFromAddress() {
        return FROMADDRESS;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getCcAddress() {
        return ccAddress;
    }

    public void setCcAddress(String ccAddress) {
        this.ccAddress = ccAddress;
    }

    public String getUserName() {
        return USERNAME;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Vector getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(Vector attachFileNames) {
        this.attachFileNames = attachFileNames;
    }

    public boolean isValidateSSL() {
        return validateSSL;
    }

    public void setValidateSSL(boolean validateSSL) {
        this.validateSSL = validateSSL;
    }
}
