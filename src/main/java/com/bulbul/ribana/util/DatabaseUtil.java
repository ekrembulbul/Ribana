package com.bulbul.ribana.util;

import com.bulbul.ribana.constants.CommonConstants;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class DatabaseUtil {

    static Logger logger = LogManager.getLogger(DatabaseUtil.class);

    @NotNull
    public static <T> List<T> getQueryResult(final EntityManager entityManager, final Class<T> clazz, final Map<String, String> params, final String queryStr) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        final Map<String, String> dbParams = createDbParams(params);

        final String updatedQueryStr = updateQueryStrWithParams(clazz, params, dbParams, queryStr);

//        logger.info("Custom Query: " + updatedQueryStr);

        final List<?> resultList = entityManager.createNativeQuery(updatedQueryStr).getResultList();

        return DatabaseUtil.setResults(clazz, resultList);
    }

    private static Map<String, String> createDbParams(Map<String, String> params) {
        Map<String, String> dbParams = new HashMap<>();

        for (Map.Entry<String, String> param : params.entrySet()) {
            final String paramKeyDbFormat = DatabaseUtil.getParamKeyDbFormat(param.getKey());
            dbParams.put(param.getKey(), paramKeyDbFormat);
        }

        return dbParams;
    }


    private static <T> String updateQueryStrWithParams(final Class<T> clazz, final Map<String, String> params, final Map<String, String> dbParams, final String queryStr) throws NoSuchFieldException {
        StringBuilder queryStrBuilder = new StringBuilder(queryStr);

        for (Map.Entry<String, String> param : params.entrySet()) {
            if (Objects.isNull(param.getKey()) || Objects.isNull(param.getValue()))
                continue;

            queryStrBuilder.append(CommonConstants.DB_AND).append(dbParams.get(param.getKey()));

            final Class<?> fieldType = clazz.getDeclaredField(param.getKey()).getType();

            if (String.class.equals(fieldType))
                queryStrBuilder.append(CommonConstants.DB_LIKE);

            queryStrBuilder.append(param.getValue());
        }

        return queryStrBuilder.toString();
    }

    private static <T> List<T> setResults(final Class<T> clazz, final List<?> resultList) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
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
        Constructor<T> ctor = clazz.getConstructor();
        T t = ctor.newInstance();

        if (result instanceof Object[] results) {
//            if (results.length != fields.length)
                // TODO: exception firlatilacak.

            for (int i = 0; i < fields.length; ++i) {
                if (results.length == i)
                    break;

                setField(t, fields[i], results[i]);
            }

        } else {
//            if (fields.length == 1)
                // TODO: exception firlatilacak.

                setField(t, fields[0], result);
        }

        return t;
    }

    private static <T> void setField(final T instance, final Field field, final Object result) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        final String setMethodName = Util.getSetMethodName(field.getName());
        final Method method = instance.getClass().getMethod(setMethodName, field.getType());
        method.invoke(instance, result);
    }

    private static String getParamKeyDbFormat(String key) {
        String[] splitStr = splitStrByUpperCase(key);
        StringBuilder upperStrBuilder = new StringBuilder();

        for (String str : splitStr) {
            upperStrBuilder.append(str.toUpperCase()).append(CommonConstants.DB_KEY_SEPARATOR);
        }

        return upperStrBuilder.substring(0, upperStrBuilder.lastIndexOf(CommonConstants.DB_KEY_SEPARATOR));
    }

    private static String[] splitStrByUpperCase(String key) {
        List<String> splitStrList = new ArrayList<>();

        int lastIndex = 0;

        for (int i = 0; i <= key.length(); ++i) {
            boolean isUpperCase = false;
            boolean isStrEnd = false;

            if (i == key.length()) {
                isStrEnd = true;
            } else {
                final char letter = key.charAt(i);
                isUpperCase = (int) letter >= 65 && (int) letter <= 90;
            }

            if (isUpperCase || isStrEnd) {
                splitStrList.add(key.substring(lastIndex, i));
                lastIndex = i;
            }
        }

        return splitStrList.toArray(String[]::new);
    }
}
