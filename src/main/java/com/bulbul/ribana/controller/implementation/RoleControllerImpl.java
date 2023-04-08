package com.bulbul.ribana.controller.implementation;

import com.bulbul.ribana.controller.RoleController;
import com.bulbul.ribana.entity.Role;
import com.bulbul.ribana.service.RoleService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleControllerImpl extends BaseControllerImpl<Role, Long> implements RoleController {

    private final RoleService roleService;

    public RoleControllerImpl(RoleService roleService) {
        super(roleService);
        this.roleService = roleService;
    }
}
