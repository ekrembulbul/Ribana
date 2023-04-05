package com.bulbul.ribana.service;

import com.bulbul.ribana.entity.Userr;
import com.bulbul.ribana.entity.custom.CustomUserr;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface UserrService extends BaseService<Userr, Long> {

    List<CustomUserr> findByParams(Map<String, String> params) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException;

}
