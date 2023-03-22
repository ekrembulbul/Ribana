package com.bulbul.ribana.service.implementation;

import com.bulbul.ribana.entity.Userr;
import com.bulbul.ribana.repository.UserrRepository;
import com.bulbul.ribana.service.UserrService;
import org.springframework.stereotype.Service;

@Service
public class UserrServiceImpl extends BaseServiceImpl<Userr, Long> implements UserrService {

    public UserrServiceImpl(UserrRepository userrRepository) {
        super(userrRepository);
    }

}
