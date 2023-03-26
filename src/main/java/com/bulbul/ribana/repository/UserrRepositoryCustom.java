package com.bulbul.ribana.repository;

import com.bulbul.ribana.entity.custom.CustomUserr;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface UserrRepositoryCustom {

    List<CustomUserr> findByParams(Map<String, Object> params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException;

}
