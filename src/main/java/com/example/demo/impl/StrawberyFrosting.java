package com.example.demo.impl;


import com.example.demo.interfaces.Frosting;
import org.springframework.stereotype.Component;

@Component
public class StrawberyFrosting implements Frosting {
    @Override
    public int getFrostingType() {
        return 2;
    }
}
