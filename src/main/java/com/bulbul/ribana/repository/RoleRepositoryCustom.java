package com.bulbul.ribana.repository;

import com.bulbul.ribana.entity.custom.CustomRole;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface RoleRepositoryCustom {

    List<CustomRole> findByParams(Map<String, String> params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException;

}
