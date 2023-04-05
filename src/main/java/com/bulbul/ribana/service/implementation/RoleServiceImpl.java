package com.bulbul.ribana.service.implementation;

import com.bulbul.ribana.entity.Role;
import com.bulbul.ribana.entity.custom.CustomRole;
import com.bulbul.ribana.repository.RoleRepository;
import com.bulbul.ribana.service.RoleService;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    @Override
    public List<CustomRole> findByParams(Map<String, String> params) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        return roleRepository.findByParams(params);
    }

}
