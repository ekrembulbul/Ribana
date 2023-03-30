package com.bulbul.ribana.util;

import com.bulbul.ribana.constants.CommonConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Util {

    public static String getSetMethodName(String fieldName) {
        String firstLetter = fieldName.substring(0, 1);
        return CommonConstants.SET + firstLetter.toUpperCase() + fieldName.substring(1);
    }

}
