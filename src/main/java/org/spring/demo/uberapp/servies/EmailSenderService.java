package org.spring.demo.uberapp.servies;

public interface EmailSenderService {

    public void sendEmail(String to, String subject, String body);

}
