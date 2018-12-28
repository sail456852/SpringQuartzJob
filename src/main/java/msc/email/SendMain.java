package msc.email;

import javax.mail.MessagingException;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/27<br/>
 * Time: 17:50<br/>
 * To change this template use File | Settings | File Templates.
 * it's not working
 */
public class SendMain {
    public static void main(String[] args) throws MessagingException {
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setSubject("Dragon's mother message");
        mailInfo.setContent("Who are you summoning? Let's do this! ");
        mailInfo.setToAddress("eugene@yeahka.com");
        boolean b = SimpleMailSender.sendHtmlMail(mailInfo);
        if(b){
            System.err.println("SendMain.main succeeded");
        }
    }
}
