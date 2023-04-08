package com.bulbul.ribana.service.implementation;

import com.bulbul.ribana.entity.Role;
import com.bulbul.ribana.repository.RoleRepository;
import com.bulbul.ribana.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

}
