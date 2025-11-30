package com.example.demo;


import com.example.demo.interfaces.Frosting;
import com.example.demo.interfaces.Syrup;
import org.springframework.stereotype.Component;

@Component
public class CakeBaker {

    private final Frosting frostingType;
    private final Syrup syrupType;

    public CakeBaker(Frosting frostingType, Syrup syrupType) {
        this.frostingType = frostingType;
        this.syrupType = syrupType;
    }

    void bakeCake() {
        System.out.println("Baking Cake");
        System.out.println("Frosting type: " + frostingType.getFrostingType());
        System.out.println("Syrup type: " + syrupType.getSyrupType());
    }
}
