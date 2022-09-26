package com.spring.app.allokal.controller;

import com.spring.app.allokal.dto.MessageVO;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {
    DefaultMessageService messageService;
    @Value("${api-key}")
    String KEY;
    @Value("${secret-key}")
    String secretKey;
    @Value("${domain}")
    String domain;

    @PostMapping("/send-one")
    public MessageVO sendOne(MessageVO messageVO) {
        Message message = new Message();
        message.setCountry("84");
        message.setFrom("0262033000");
        message.setTo("0943200128");
        String code = "";
        for (int i = 0; i < 4; i++) {
            int random = (int) ((Math.random()) * 10) + 1;
            code = code + String.valueOf(random);
        }
        System.out.println("KEY : " + KEY + "// SECRET-KEY : " + secretKey);
        message.setText("COOLSMS Verification Code: " + code);
        this.messageService = NurigoApp.INSTANCE.initialize(KEY, secretKey, domain);
        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);
        messageVO.setAuthCode(code);
        return messageVO;
    }
}
