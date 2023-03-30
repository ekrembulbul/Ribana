package com.bulbul.ribana.util;

import com.bulbul.ribana.constants.CommonConstants;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControllerUtil {

    public static Sort.Direction getSortDirection(String direction) {
        if (Objects.isNull(direction) || direction.isEmpty() || CommonConstants.WEB_ASC.equals(direction))
            return Sort.Direction.ASC;
        else if (CommonConstants.WEB_DESC.equalsIgnoreCase(direction))
            return Sort.Direction.DESC;
        else
            return null;
    }

    public static Sort.Order[] getOrders(String[] propertyAndDirections) {
        if (propertyAndDirections.length % 2 == 1)
            return null;

        List<Sort.Order> orderList = new ArrayList<>();

        for (int i = 0; i < propertyAndDirections.length; i += 2) {
            if (CommonConstants.WEB_ASC.equalsIgnoreCase(propertyAndDirections[i+1]))
                orderList.add(Sort.Order.asc(propertyAndDirections[i]));
            else if (CommonConstants.WEB_DESC.equalsIgnoreCase(propertyAndDirections[i+1]))
                orderList.add(Sort.Order.desc(propertyAndDirections[i]));
        }

        return orderList.toArray(Sort.Order[]::new);
    }

}
