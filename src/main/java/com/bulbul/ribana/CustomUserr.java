package com.bulbul.ribana;

import lombok.Data;


@Data
public class CustomUserr {
    String name;

    public CustomUserr(String name) {
        this.name = name;
    }
}
