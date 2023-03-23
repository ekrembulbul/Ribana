package com.bulbul.ribana.repository;

import com.bulbul.ribana.CustomUserr;

import java.util.List;
import java.util.Map;

public interface UserrRepositoryCustom {

    List<CustomUserr> findByParams(Map<String, Object> params);

}
