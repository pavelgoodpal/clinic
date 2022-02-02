package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.OutBox;
import com.cshop.cosmeticshop.service.EmailService;
import com.cshop.cosmeticshop.service.OutBoxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Class implements EmailService
 *
 * @author Pave1Pal
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final OutBoxService outBoxService;

    @Override
    public void sendAllEmailMessages() {
        var outBoxList = outBoxService.findAll();
        if (!outBoxList.isEmpty()) {
            outBoxList.forEach(this::sendEmailMessage);
        }
    }

    /**
     * Make SimpleMailMessage using OutBox information than sent it and delete sent outbox info.
     *
     * @param outBox consist message information
     */
    private void sendEmailMessage(OutBox outBox) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Cosmetologist appointment");
        mailMessage.setTo(outBox.getDestination());
        mailMessage.setText(outBox.getPayload());
        emailSender.send(mailMessage);
        outBoxService.delete(outBox);
    }
}
