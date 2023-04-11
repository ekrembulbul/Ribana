package com.bulbul.ribana.service.implementation;

import com.bulbul.ribana.entity.Userr;
import com.bulbul.ribana.repository.UserrRepository;
import com.bulbul.ribana.service.UserrService;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public class UserrServiceImpl extends BaseServiceImpl<Userr, Long> implements UserrService {

    @SuppressWarnings("unused")
    final UserrRepository userrRepository;

    public UserrServiceImpl(UserrRepository userrRepository) {
        super(userrRepository);
        this.userrRepository = userrRepository;
    }

}
