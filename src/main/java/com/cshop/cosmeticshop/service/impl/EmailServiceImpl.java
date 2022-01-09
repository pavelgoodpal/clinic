package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;


/**
 * Class implements EmailService
 * @author Pave1Pal
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Override
    public void sendOrderMessage(Order order) {
        var mailMessage = makeMailMessage(order);
        emailSender.send(mailMessage);
    }

    /**
     * Make mail message form order
     * @param order which transform to message
     * @return SimpleMailMessage
     */
    private SimpleMailMessage makeMailMessage(Order order) {
        var date = order.getStartAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(order.getEmail());
        mailMessage.setSubject("Cosmetologist appointment");
        mailMessage.setText(
                "We very happy that you use our service!!!\n" +
                order.getUser().getFirstName() + ", You have got an appointment to " + date + ".\n" +
                "Please do not late and have a good day!"
        );
        return mailMessage;
    }
}
