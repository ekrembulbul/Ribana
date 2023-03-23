package com.bulbul.ribana.service;

import com.bulbul.ribana.CustomUserr;
import com.bulbul.ribana.entity.Userr;

import java.util.List;
import java.util.Map;

public interface UserrService extends BaseService<Userr, Long> {

    List<CustomUserr> findByParams(Map<String, Object> params);

}
