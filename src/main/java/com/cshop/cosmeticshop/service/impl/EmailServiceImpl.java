package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.intity.Order;
import com.cshop.cosmeticshop.domain.intity.User;
import com.cshop.cosmeticshop.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendMessage(Order order, User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        var date = order.getStartAt();
        message.setTo(order.getEmail());
        message.setSubject("Cosmetologist appointment");

        message.setText("We very happy that you use our service!!!\n" +
                user.getFirstName() +", You have got an appointment to " + toNormalDate(date) + ".\n" +
                "Please do not late and have a good day!");

        emailSender.send(message);
    }


    private String toNormalDate(LocalDateTime date) {
        if (date.getMinute() < 10) {
            return date.getDayOfMonth() + " of " + date.getMonth().toString().toLowerCase(Locale.ROOT) + " at " +
                    date.getHour() + ":0" + date.getMinute();
        }
        return date.getDayOfMonth() + " of " + date.getMonth().toString().toLowerCase(Locale.ROOT) + " at " +
                date.getHour() + ":" + date.getMinute();
    }
}
