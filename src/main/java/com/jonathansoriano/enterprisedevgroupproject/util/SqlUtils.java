package com.jonathansoriano.enterprisedevgroupproject.util;

import org.apache.commons.lang3.StringUtils;

public class SqlUtils {
    public SqlUtils(){
        //default constructor
    }

    public static <T> String andAddCondition(String sql, T value){
        return sql != null && value != null
                ? String.format(" %s", sql)
                : StringUtils.EMPTY;
    }

//    public static <T> String AddJoinCondition(String sql, T value){
//        return
//    }
}
