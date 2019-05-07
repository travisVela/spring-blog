//package com.codeup.blog;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service("mailService")
//public class EmailService {
//    @Autowired
//    public JavaMailSender emailSender;
//
//    @Value("${spring.mail.form}")
//    private String from;
//
//
//
//    public void prepareAndSend(Post post) {
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setFrom(from);
//        msg.setTo(post.getEmail());
//        msg.setText(post.getTitle());
//        msg.setText(post.getBody());
//
//        try {
//            this.emailSender.send(msg);
//
//
//        } catch (MailException e) {
//            System.err.println(e.getMessage());
//        }
//    }
//
//}
