package com.bulbul.ribana.util;

import com.bulbul.ribana.exception.custom.ResultsAndFieldsNotEqualException;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DatabaseUtil {

    @SuppressWarnings("unused")
    private static final Logger logger = LogManager.getLogger(DatabaseUtil.class);

    @SuppressWarnings("unused")
    @NotNull
    public static <T> List<T> getQueryResultList(final EntityManager entityManager, final Class<T> clazz, final String queryStr) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        final List<?> resultList = entityManager.createNativeQuery(queryStr).getResultList();
        return DatabaseUtil.getCustomClassListWithResults(clazz, resultList);
    }

    private static <T> List<T> getCustomClassListWithResults(final Class<T> clazz, final List<?> resultList) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        if (Objects.isNull(resultList) || resultList.isEmpty()) {
            return Collections.emptyList();
        }

        final Field[] fields = clazz.getDeclaredFields();
        if (fields.length == 0) {
            return Collections.emptyList();
        }

        List<T> tList = new ArrayList<>();

        for (Object result : resultList) {
            final T t = createT(clazz, result, fields);
            tList.add(t);
        }

        return tList;
    }

    private static <T> T createT(final Class<T> clazz, final Object result, final Field[] fields) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<T> tConstructor = clazz.getConstructor();
        T t = tConstructor.newInstance();

        if (result instanceof Object[] results) {
            if (results.length != fields.length)
                throw new ResultsAndFieldsNotEqualException();

            for (int i = 0; i < fields.length; ++i) {
                setField(t, fields[i], results[i]);
            }

        } else {
            if (fields.length == 1)
                throw new ResultsAndFieldsNotEqualException();

            setField(t, fields[0], result);
        }

        return t;
    }

    private static <T> void setField(final T instance, final Field field, final Object result) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        final String setMethodName = Util.getSetMethodName(field.getName());
        final Method method = instance.getClass().getMethod(setMethodName, field.getType());
        method.invoke(instance, result);
    }

}
