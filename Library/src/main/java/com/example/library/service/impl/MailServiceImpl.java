//package com.example.library.service.impl;
//
//import com.example.library.service.MailService;
//
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
////import org.springframework.mail.MailSender;
//
//import java.nio.charset.StandardCharsets;
//
////@RequiredArgsConstructor
//@Service
//public class MailServiceImpl implements MailService {
//
//
//    @Autowired
//    JavaMailSender mailSender;
//    @Value("${spring.mail.username}")
//
//    private String email;
//
//
//    @Override
//    public void sendMailTest() {
//        try{
//            MimeMessage message = mailSender.createMimeMessage();
//
//            MimeMessageHelper helper = new MimeMessageHelper(
//                    message,
//                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//                    StandardCharsets.UTF_8.name()
//            );
//            helper.setFrom(email);
//            helper.setTo("tam.nlt.61cntt@gmail.com");
//            helper.setSubject("mail sender test");
//            helper.setText("hdsjfdsjfdsfdsfds");
//
//            mailSender.send(message);
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
