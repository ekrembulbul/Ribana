package com.bulbul.ribana.repository.implementation;

import com.bulbul.ribana.CustomUserr;
import com.bulbul.ribana.repository.UserrRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class UserrRepositoryCustomImpl implements UserrRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<CustomUserr> findByParams(Map<String, Object> params) {
        System.out.println(Arrays.toString(params.entrySet().toArray()));

        final List<CustomUserr> result = entityManager.createNativeQuery("select u.name as name from userr u where 1=1 and u.name = 'ekrem'", "CustomUserrMapping").getResultList();

        return result;
    }


}
