package com.jc.crm.mapper.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 动态构建多表联合模糊查询的SQL语句
 * @author currysss 2018-11-20
 * */
public class CompetitorSqlProvider {
    private final static Logger logger = LoggerFactory.getLogger(CompetitorSqlProvider.class);
    public String queryList(@Param("keyword") String keyword, @Param("uid") Integer uid) {
        String sql = new SQL(){{
            SELECT("*");
            FROM("competitors c" ) ;
            INNER_JOIN("department_user_link l ON c.holder = l.user_id");
            INNER_JOIN("department d ON d.department_id = l.department_id");
            if(uid != null){
                WHERE("d.department_id = (SELECT department_id FROM department_user_link WHERE user_id =" + uid + ") " +
                        "AND l.is_actived = 1");
            }
            if (keyword != null) {
                AND().WHERE("competitor_name LIKE '%" + keyword + "%'");
            }
        }}.toString();
        logger.info("provider sql:"+sql);
        return sql;
    }
}
