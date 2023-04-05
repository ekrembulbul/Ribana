package com.bulbul.ribana.controller.implementation;

import com.bulbul.ribana.controller.RoleController;
import com.bulbul.ribana.entity.Role;
import com.bulbul.ribana.entity.custom.CustomRole;
import com.bulbul.ribana.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@RestController
public class RoleControllerImpl extends BaseControllerImpl<Role, Long> implements RoleController {

    private final RoleService userService;

    public RoleControllerImpl(RoleService roleService) {
        super(roleService);
        this.userService = roleService;
    }

    @Override
    public ResponseEntity<List<CustomRole>> findByParams(Map<String, String> params) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        return ResponseEntity.ok(userService.findByParams(params));
    }

}
