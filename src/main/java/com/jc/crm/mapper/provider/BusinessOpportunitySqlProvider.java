package com.jc.crm.mapper.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 动态构建商业机会多表联合模糊查询的SQL语句
 * @author currysss 2018-11-28
 * */
public class BusinessOpportunitySqlProvider {

    private final static Logger logger = LoggerFactory.getLogger(BusinessOpportunitySqlProvider.class);

    public String queryListOne(@Param("keyword") String keyword, @Param("uid") Integer uid) {
        String sql = new SQL(){{
            SELECT("*");
            FROM("business_opp_source s" ) ;
            INNER_JOIN("department_user_link l ON s.holder = l.user_id");
            INNER_JOIN("department d ON d.department_id = l.department_id");
            if(uid != null){
                WHERE("d.department_id = (SELECT department_id FROM department_user_link WHERE user_id =" + uid + ") " +
                        "AND l.is_actived = 1");
            }
            if (keyword != null) {
                AND().WHERE("s.source_name LIKE '%" + keyword + "%'");
            }
        }}.toString();

        logger.info("provider sql:"+sql);

        return sql;
    }

    public String queryListTwo(@Param("keyword") String keyword, @Param("uid") Integer uid) {
        String sql = new SQL(){{
            SELECT("*");
            FROM("business_opp s" ) ;
            INNER_JOIN("department_user_link l ON s.holder = l.user_id");
            INNER_JOIN("department d ON d.department_id = l.department_id");
            if(uid != null){
                WHERE("d.department_id = (SELECT department_id FROM department_user_link WHERE user_id =" + uid + ") " +
                        "AND l.is_actived = 1");
            }
            if (keyword != null) {
                AND().WHERE("s.opp_name LIKE '%" + keyword + "%'");
            }
        }}.toString();

        logger.info("provider sql:"+sql);

        return sql;
    }

    public String queryListThree(@Param("keyword") String keyword, @Param("uid") Integer uid) {
        String sql = new SQL(){{
            SELECT("*");
            FROM("business_record r" ) ;
            INNER_JOIN(" business_opp o ON r.business_opp_id = o.business_opp_id");
            INNER_JOIN("department_user_link l ON o.holder = l.user_id");
            INNER_JOIN("department d ON d.department_id = l.department_id");
            if(uid != null){
                WHERE("d.department_id = (SELECT department_id FROM department_user_link WHERE user_id =" + uid + ") " +
                        "AND l.is_actived = 1");
            }
            if (keyword != null) {
                AND().WHERE("o.opp_name LIKE '%" + keyword + "%'");
            }
        }}.toString();

        logger.info("provider sql:"+sql);

        return sql;
    }
}
