package com.example.demo.impl;

import com.example.demo.interfaces.NotificationInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
@Qualifier("emailNotify")
//@ConditionalOnProperty(name = "notify.type", havingValue = "email")
public class EmailNotification implements NotificationInterface {
    @Override
    public void sendNotification() {
        System.out.println("Sending Email Notification");
    }
}
