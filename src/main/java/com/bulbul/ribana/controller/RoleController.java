package com.bulbul.ribana.controller;

import com.bulbul.ribana.entity.Role;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/role")
public interface RoleController extends BaseController<Role, Long> {
}
