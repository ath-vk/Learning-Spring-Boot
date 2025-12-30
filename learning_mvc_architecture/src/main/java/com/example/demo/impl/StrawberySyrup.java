package com.example.demo.impl;


import com.example.demo.interfaces.Syrup;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class StrawberySyrup implements Syrup {
    @Override
    public int getSyrupType() {
        return 3;
    }
}
