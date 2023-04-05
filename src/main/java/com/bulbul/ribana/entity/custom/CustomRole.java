package com.bulbul.ribana.entity.custom;

import com.bulbul.ribana.entity.Userr;
import lombok.Data;

@Data
public class CustomRole {

    private Long id;

    private Userr userr;

    private String role;

}
