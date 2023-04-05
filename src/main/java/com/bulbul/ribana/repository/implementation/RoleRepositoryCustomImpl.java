package com.bulbul.ribana.repository.implementation;

import com.bulbul.ribana.entity.custom.CustomRole;
import com.bulbul.ribana.repository.RoleRepositoryCustom;
import com.bulbul.ribana.util.DatabaseUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Repository
public class RoleRepositoryCustomImpl implements RoleRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<CustomRole> findByParams(Map<String, String> params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        StringBuilder queryStrBuilder = new StringBuilder("""
                SELECT *
                FROM ROLE
                """);

        return DatabaseUtil.getQueryResultList(entityManager, CustomRole.class, queryStrBuilder.toString());
    }

}

