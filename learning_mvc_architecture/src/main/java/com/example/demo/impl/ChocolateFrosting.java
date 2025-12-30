package com.example.demo.impl;


import com.example.demo.interfaces.Frosting;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class ChocolateFrosting implements Frosting {
    @Override
    public int getFrostingType() {
        return 0;
    }
}
