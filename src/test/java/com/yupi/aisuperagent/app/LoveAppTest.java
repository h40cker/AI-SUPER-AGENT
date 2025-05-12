package com.yupi.aisuperagent.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LoveAppTest {
    @Resource
    private LoveApp loveApp;
    @Test
    void testchat() {
        String chatId = UUID.randomUUID().toString();
        String message = "Hello World";
        String answer = loveApp.dochat(message,chatId);
        Assertions.assertNotNull(answer);



    }

    @Test
    void dochatwithreport() {
        String chatId = UUID.randomUUID().toString();
        String message = "我想知道如何追求另一半";
        LoveApp.LoveReport loveReport = loveApp.dochatwithreport(message,chatId);
        Assertions.assertNotNull(loveReport);
    }

    @Test
    void dochatwithrag() {
        String chatId = UUID.randomUUID().toString();
        String message = "我想知道如何追求另一半";
        String loveReport = loveApp.dochatwithrag(message,chatId);
        Assertions.assertNotNull(loveReport);
    }
}