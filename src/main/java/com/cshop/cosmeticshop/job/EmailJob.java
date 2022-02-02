package com.cshop.cosmeticshop.job;

import com.cshop.cosmeticshop.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailJob {

    private final EmailService emailService;

    @Scheduled(cron = "${cron.expression}")
    public void sendEmail() {
        emailService.sendAllEmailMessages();
    }
}
