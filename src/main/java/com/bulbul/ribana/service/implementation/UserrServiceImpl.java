package com.bulbul.ribana.service.implementation;

import com.bulbul.ribana.CustomUserr;
import com.bulbul.ribana.entity.Userr;
import com.bulbul.ribana.repository.UserrRepository;
import com.bulbul.ribana.service.UserrService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserrServiceImpl extends BaseServiceImpl<Userr, Long> implements UserrService {

    UserrRepository userrRepository;

    public UserrServiceImpl(UserrRepository userrRepository) {
        super(userrRepository);
        this.userrRepository = userrRepository;
    }

    @Override
    public List<CustomUserr> findByParams(Map<String, Object> params) {
        return userrRepository.findByParams(params);
    }

}
