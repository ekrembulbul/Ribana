package com.bulbul.ribana.repository.implementation;

import com.bulbul.ribana.entity.custom.CustomUserr;
import com.bulbul.ribana.repository.UserrRepositoryCustom;
import com.bulbul.ribana.util.DatabaseUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Repository
public class UserrRepositoryCustomImpl implements UserrRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<CustomUserr> findByParams(Map<String, String> params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        StringBuilder queryStrBuilder = new StringBuilder("""
                SELECT *
                FROM USERR
                """);

        return DatabaseUtil.getQueryResultList(entityManager, CustomUserr.class, queryStrBuilder.toString());
    }

}

