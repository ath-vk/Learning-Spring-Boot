package com.example.demo.impl;


import com.example.demo.interfaces.Syrup;
import org.springframework.stereotype.Component;

@Component
public class ChocolateSyrup implements Syrup {
    @Override
    public int getSyrupType() {
        return 1;
    }
}
