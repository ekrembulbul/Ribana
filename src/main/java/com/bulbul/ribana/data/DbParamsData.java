package com.bulbul.ribana.data;

import com.bulbul.ribana.constants.CommonConstants;
import com.bulbul.ribana.util.Util;
import lombok.Data;

import java.util.*;

@Data
public class DbParamsData {

    private ConditionParams conditionParams;

    private String condition;

    private SortParam[] sortData;

    private PageData pageData;

    private String group;

    @Data
    private static class ConditionParams {

        private Map<String, String[]> params;

        private Map<String, String> dbFormatParams;

        public ConditionParams() {
            params = new HashMap<>();
            dbFormatParams = new HashMap<>();
        }

    }

    @Data
    private class SortParam {

        private String property;

        private String direction;

    }

    @Data
    private static class PageData {

        private Integer page;

        private Integer size;

    }

    public DbParamsData() {
        conditionParams = new ConditionParams();
        sortData = new SortData();
        pageData = new PageData();
    }

    public void putConditionParam(String key, String dbFormat, String[] value) {
        conditionParams.params.put(key, value);
        conditionParams.dbFormatParams.put(key, dbFormat);
    }

    public void putConditionParam(String key, String[] value) {
        conditionParams.params.put(key, value);
    }

    public void putConditionParam(String key, String dbFormat) {
        conditionParams.dbFormatParams.put(key, dbFormat);
    }

    public String[] getConditionParam(String key) {
        return conditionParams.params.get(key);
    }

    public String getConditionDbFormatParam(String key) {
        return conditionParams.dbFormatParams.get(key);
    }

    public void setSortData(String direction, String[] properties) {
        sortData.direction = direction;
        sortData.properties = properties;
    }

    public void setSortDataDirection(String direction) {
        sortData.direction = direction;
    }

    public String getSortDataDirection() {
        return sortData.direction;
    }

    public String[] getSortDataProperties() {
        return sortData.properties;
    }

    public void setSortDataProperties(String[] properties) {
        sortData.properties = properties;
    }

    public void setPageData(Integer page, Integer size) {
        pageData.page = page;
        pageData.size = size;
    }

    public void setPageDataPage(Integer page) {
        pageData.page = page;
    }

    public void setPageDataSize(Integer size) {
        pageData.size = size;
    }

    public Integer getPageDataPage() {
        return pageData.page;
    }

    public Integer getPageDataSize() {
        return pageData.size;
    }

    public void setConditionDataFromParams(Map<String, String> params) {
        this.condition = CommonConstants.DB_AND;

        String conditionKey = CommonConstants.WEB_KEYWORD_OPERATOR + CommonConstants.WEB_CONDITION;

        if (!Util.mapContainsKeyIgnoreCase(params, conditionKey))
            return;

        String paramCondition = params.get(conditionKey);
        if (Objects.isNull(paramCondition))
            return;

        if (CommonConstants.WEB_OR.equalsIgnoreCase(paramCondition))
            condition = CommonConstants.DB_OR;
    }

    public void setSortDataFromParams(Map<String, String> params) {
        String sortKey = CommonConstants.WEB_KEYWORD_OPERATOR + CommonConstants.WEB_SORT;

        if (!Util.mapContainsKeyIgnoreCase(params, sortKey))
            return;

        String paramSort = params.get(sortKey);
        if (Objects.isNull(paramSort))
            return;

        String[] sortSplit = paramSort.split(CommonConstants.WEB_PARAM_SEPARATOR);

        List<String> sortList = Arrays.stream(sortSplit).toList();

        List<String> propertyList = new ArrayList<>();

        for (String str : sortList) {
            if (CommonConstants.WEB_ASC.equals(str))
                sortData.direction = CommonConstants.DB_ASC;
            else if (CommonConstants.WEB_DESC.equals(str))
                sortData.direction = CommonConstants.DB_DESC;
            else
                propertyList.add(str);
        }

        sortData.properties = propertyList.toArray(String[]::new);
    }

}
