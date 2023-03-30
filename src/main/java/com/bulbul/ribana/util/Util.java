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

    public static boolean mapContainsKeyIgnoreCase(Map<String, ?> map, String str) {
        if (Objects.isNull(str) || Objects.isNull(map))
            return false;

        for (Map.Entry<String, ?> entry : map.entrySet()) {
            if (Objects.isNull(entry.getKey()))
                return false;

            if (str.equalsIgnoreCase(entry.getKey()))
                return true;
        }

        return false;
    }

    public static String[] splitStrByUpperCase(String key) {
        List<String> splitStrList = new ArrayList<>();

        int lastIndex = 0;

        for (int i = 0; i <= key.length(); ++i) {
            boolean isUpperCase = false;
            boolean isStrEnd = false;

            if (i == key.length()) {
                isStrEnd = true;
            } else {
                final char letter = key.charAt(i);
                isUpperCase = (int) letter >= 65 && (int) letter <= 90;
            }

            if (isUpperCase || isStrEnd) {
                splitStrList.add(key.substring(lastIndex, i));
                lastIndex = i;
            }
        }

        return splitStrList.toArray(String[]::new);
    }

}
