package com.bulbul.ribana.util;

import com.bulbul.ribana.constants.CommonConstants;
import com.bulbul.ribana.exception.custom.WrongPropertiesAndDirectionsException;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControllerUtil {

    public static Sort.Direction getSortDirection(String direction) {
        if (Objects.isNull(direction) ||
            direction.isEmpty() ||
            CommonConstants.WEB_ASC.equals(direction))
            return Sort.Direction.ASC;
        else if (CommonConstants.WEB_DESC.equalsIgnoreCase(direction))
            return Sort.Direction.DESC;
        else
            return null;
    }

    public static Sort.Order[] getOrders(String[] propertiesAndDirections) {
        if (Objects.isNull(propertiesAndDirections) ||
            propertiesAndDirections.length == 0 ||
            propertiesAndDirections.length % 2 == 1)
            throw new WrongPropertiesAndDirectionsException();

        List<Sort.Order> orderList = new ArrayList<>();

        for (int i = 0; i < propertiesAndDirections.length; i += 2) {
            if (CommonConstants.WEB_ASC.equalsIgnoreCase(propertiesAndDirections[i+1]))
                orderList.add(Sort.Order.asc(propertiesAndDirections[i]));
            else if (CommonConstants.WEB_DESC.equalsIgnoreCase(propertiesAndDirections[i+1]))
                orderList.add(Sort.Order.desc(propertiesAndDirections[i]));
            else
                throw new WrongPropertiesAndDirectionsException();
        }

        return orderList.toArray(Sort.Order[]::new);
    }

}
