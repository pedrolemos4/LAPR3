/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author pedro
 */
public class EmailDB {

    /**
     * Envia um email recebendo o assunto e a mensagem do mesmo
     * @param emailOrig email do remetente
     * @param emailDest email do destinatário
     * @param assunto assunto do email
     * @param mensagem corpo do email
     * @return
     */
    public boolean sendEmail(String emailOrig, String emailDest, String assunto, String mensagem){
        Properties props = new Properties();
        
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.checkserveridentity", true);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("admlapr123@gmail.com",
                                "LAPR12345678");
                    }
                });

        
        session.setDebug(true);


        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailDest));

            Address[] toUser = InternetAddress.parse(emailDest);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(assunto);
            message.setText(mensagem);
            
            Transport.send(message);

            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
