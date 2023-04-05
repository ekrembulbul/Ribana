package com.bulbul.ribana.service;

import com.bulbul.ribana.entity.Role;
import com.bulbul.ribana.entity.custom.CustomRole;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface RoleService extends BaseService<Role, Long> {

    List<CustomRole> findByParams(Map<String, String> params) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException;

}
