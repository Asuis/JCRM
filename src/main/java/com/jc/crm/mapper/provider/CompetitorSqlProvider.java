package com.jc.crm.mapper.provider;

import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompetitorSqlProvider {
    private final static Logger logger = LoggerFactory.getLogger(CompetitorSqlProvider.class);
    public String selectByKeyWord(String keyword) {
        String sql = new SQL(){{
            SELECT("*");
            FROM("competitors");
            if (keyword!=null) {
                WHERE("competitor_name LIKE '%" + keyword + "%'");
            }
        }}.toString();
        logger.info("provider sql:"+sql);
        return sql;
    }
}
