package com.bulbul.ribana.controller.implementation;

import com.bulbul.ribana.controller.UserrController;
import com.bulbul.ribana.entity.Userr;
import com.bulbul.ribana.service.UserrService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserrControllerImpl extends BaseControllerImpl<Userr, Long> implements UserrController {

    public UserrControllerImpl(UserrService userrService) {
        super(userrService);
    }

}
