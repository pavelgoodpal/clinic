package com.cshop.cosmeticshop.job;

import com.cshop.cosmeticshop.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Job for sending email.
 *
 * @author Pave1Pal
 */
@Component
@RequiredArgsConstructor
public class EmailJob {

    private final EmailService emailService;

    /**
     * Job method for sending email.
     */
    @Scheduled(cron = "${cron.expression}")
    public void sendEmail() {
        emailService.sendAllEmailMessages();
    }
}
