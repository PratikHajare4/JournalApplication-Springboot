package com.pratik.JournalApp.services;


import com.pratik.JournalApp.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail(){
        emailService.sendEmail("hajarepratik10@gmail.com","Testing Java mail sender", "Hi, app Kasise hain? ");
    }
}
