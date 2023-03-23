package com.bulbul.ribana.controller.implementation;

import com.bulbul.ribana.CustomUserr;
import com.bulbul.ribana.controller.UserrController;
import com.bulbul.ribana.entity.Userr;
import com.bulbul.ribana.service.UserrService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserrControllerImpl extends BaseControllerImpl<Userr, Long> implements UserrController {

    private final UserrService userService;

    public UserrControllerImpl(UserrService userrService) {
        super(userrService);
        this.userService = userrService;
    }

    @Override
    public ResponseEntity<List<CustomUserr>> findByParams(Map<String, Object> params) {
        return ResponseEntity.ok(userService.findByParams(params));
    }

}
