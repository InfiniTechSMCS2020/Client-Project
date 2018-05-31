package ClientProj;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class Emailer {
    public static void email(String from, String password, String to, String message, String subject){

        Properties properties = new Properties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","465");

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(from,password);
                }
                });
        try {
            MimeMessage messageEmail = new MimeMessage(session);
            messageEmail.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            messageEmail.setSubject(subject);
            messageEmail.setText(message);
            Transport.send(messageEmail);
            System.out.println("message sent");
        }catch(MessagingException e){
            throw new RuntimeException(e);
        }
    }
}
