package com.jonathansoriano.enterprisedevgroupproject.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Utility class providing helper methods for SQL query construction.
 * Supports dynamic SQL condition building for parameterized queries.
 *
 * @author [Author Name]
 * @version [Version]
 * @since [Release Version]
 */
public class SqlUtils {
    public SqlUtils(){
        //default constructor
    }

    public static <T> String andAddCondition(String sql, T value){
        return sql != null && value != null
                ? String.format(" %s", sql)
                : StringUtils.EMPTY;
    }
}
