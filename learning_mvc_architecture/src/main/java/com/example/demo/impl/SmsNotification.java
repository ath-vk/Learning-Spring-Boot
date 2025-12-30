package com.example.demo.impl;

import com.example.demo.interfaces.NotificationInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Qualifier("smsNotify")
@ConditionalOnProperty(name = "notify.type", havingValue = "sms")
public class SmsNotification implements NotificationInterface {
    @Override
    public void sendNotification() {
        System.out.println("Sending SMS notification");
    }
}
