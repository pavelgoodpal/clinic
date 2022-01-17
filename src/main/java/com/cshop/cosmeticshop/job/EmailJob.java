package com.cshop.cosmeticshop.job;

import com.cshop.cosmeticshop.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailJob {

    private final EmailService emailService;

    @Scheduled(cron = "30 * * * * ?")
    public void sendEmail() {
        log.info("job");
        emailService.sendAllMessages();
    }
}
