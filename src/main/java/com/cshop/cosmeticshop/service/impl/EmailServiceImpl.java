package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.User;
import com.cshop.cosmeticshop.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Class implements EmailService
 * @author Pave1Pal
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    /**
     * Sent email using order information
     * @param order order data
     */
    public void sendMessage(Order order) {
        SimpleMailMessage message = new SimpleMailMessage();

        var localDate = order.getStartAt();
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

        message.setTo(order.getEmail());
        message.setSubject("Cosmetologist appointment");
        message.setText(
                "We very happy that you use our service!!!\n" +
                order.getUser().getFirstName() +", You have got an appointment to " + date + ".\n" +
                "Please do not late and have a good day!"
        );
        emailSender.send(message);
    }
}
