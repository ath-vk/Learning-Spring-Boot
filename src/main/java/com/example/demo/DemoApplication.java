package com.example.demo;

import com.example.demo.interfaces.NotificationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

//    Below is field dependency injection, not recommended in production
//    @Autowired
//    private PaymentService paymentService;

    // This is constructor DI, this is recommended in production
    // Considered as best practice (not tightly coupled, easy to test, object management is easy)
    private final NotificationInterface notificationService;
    private final CakeBaker cakeBaker;

    @Autowired
    Map<String, NotificationInterface> map = new HashMap<>();

     //Predicted precedence: Conditional(only those considered for creation/non-creation who have conditionals, else created always) > Qualifiers > Primary
    // When finding resolution: Answer basic question that which will be created ?
    // Out of those created which will be given preference first.
//    public DemoApplication(@Qualifier("smsNotify") NotificationInterface notificationService) {
//        this.notificationService = notificationService;
//    }

    public DemoApplication(NotificationInterface notificationService, CakeBaker cakeBaker) {
        this.notificationService = notificationService;
        this.cakeBaker = cakeBaker;
    }

    public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        //paymentService.payment();
        notificationService.sendNotification();

        for(Map.Entry<String, NotificationInterface> entry: map.entrySet()) {
            entry.getValue().sendNotification();
        }

        cakeBaker.bakeCake();
    }
}

// op:
//Sending Email Notification
//Sending Email Notification
//Sending SMS notification
//Baking Cake
//Frosting type: 0
//Syrup type: 3
