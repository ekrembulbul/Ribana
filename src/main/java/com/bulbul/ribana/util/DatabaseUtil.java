package com.bulbul.ribana.util;

import com.bulbul.ribana.constants.CommonConstants;
import com.bulbul.ribana.data.DbParamsData;
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
        final DbParamsData dbParamsData = createDbParamsData(params);

        final String updatedQueryStr = updateQueryStrWithParams(clazz, dbParamsData, queryStr);

        logger.info("Custom Query: " + updatedQueryStr);

        final List<?> resultList = entityManager.createNativeQuery(updatedQueryStr).getResultList();

        return DatabaseUtil.setResults(clazz, resultList);
    }

    private static DbParamsData createDbParamsData(Map<String, String> params) {
        DbParamsData dbParamsData = new DbParamsData();

        dbParamsData.setConditionDataFromParams(params);
        dbParamsData.setSortDataFromParams(params);

        return dbParamsData;
    }

    private static Map<String, String> createDbParams(Map<String, String> params) {
        Map<String, String> dbParams = new HashMap<>();

        for (Map.Entry<String, String> param : params.entrySet()) {
            if (Objects.isNull(param.getKey()) || Objects.isNull(param.getValue()))
                continue;

            final String paramKeyDbFormat = DatabaseUtil.createParamKeyToDbFormatKey(param.getKey());
            dbParams.put(param.getKey(), paramKeyDbFormat);
        }

        return dbParams;
    }

    private static String createParamKeyToDbFormatKey(String key) {
        if (Objects.isNull(key))
            return null;

        String[] splitStr = Util.splitStrByUpperCase(key);
        StringBuilder upperStrBuilder = new StringBuilder();

        for (String str : splitStr) {
            upperStrBuilder.append(str.toUpperCase()).append(CommonConstants.DB_KEY_SEPARATOR);
        }

        return upperStrBuilder.substring(0, upperStrBuilder.lastIndexOf(CommonConstants.DB_KEY_SEPARATOR));
    }

    private static <T> String updateQueryStrWithParams(final Class<T> clazz, DbParamsData dbParamsData, final String queryStr) throws NoSuchFieldException {
        StringBuilder queryStrBuilder = new StringBuilder(queryStr);

        if (CommonConstants.DB_AND.equals(condition))
            queryStrBuilder.append(CommonConstants.DB_AND_PREFIX);
        else if (CommonConstants.DB_OR.equals(condition))
            queryStrBuilder.append(CommonConstants.DB_OR_PREFIX);

        for (Map.Entry<String, String> param : params.entrySet()) {
            if (Objects.isNull(param.getKey()) || Objects.isNull(param.getValue()))
                continue;

            String[] splitValue = param.getValue().split(CommonConstants.WEB_PARAM_SEPARATOR);
            for (String value : splitValue) {
                Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>(param.getKey(), value);
                createConditionWithParam(clazz, dbParams, condition, queryStrBuilder, entry);
            }
        }

        return queryStrBuilder.toString();
    }

    private static <T> void createConditionWithParam(Class<T> clazz, Map<String, String> dbParams, String condition, StringBuilder queryStrBuilder, Map.Entry<String, String> param) throws NoSuchFieldException {
        queryStrBuilder.append(condition).append(dbParams.get(param.getKey()));

        boolean isThereBetween = param.getValue().contains(CommonConstants.WEB_BETWEEN_OPERATOR);

        if (isThereBetween) {
            String[] betweenSplitStr = param.getValue().split(CommonConstants.WEB_BETWEEN_OPERATOR);
            if (betweenSplitStr.length != 2)
                return;

            queryStrBuilder.append(CommonConstants.DB_BETWEEN).append(betweenSplitStr[0]).append(CommonConstants.DB_AND).append(betweenSplitStr[1]);

        } else {
            final Class<?> fieldType = clazz.getDeclaredField(param.getKey()).getType();

            if (String.class.equals(fieldType))
                queryStrBuilder.append(CommonConstants.DB_LIKE);

            queryStrBuilder.append(param.getValue());
        }
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

}
