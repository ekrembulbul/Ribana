package com.bulbul.ribana.util;

import org.springframework.data.domain.Sort;

import java.util.Objects;

public class ControllerSortUtil {

    public static Sort.Direction getSortDirection(String direction) {
        if (Objects.isNull(direction)) return null;

        if ("asc".equals(direction)) return Sort.Direction.ASC;
        else if ("desc".equals(direction)) return Sort.Direction.DESC;
        else return null;
    }

}
