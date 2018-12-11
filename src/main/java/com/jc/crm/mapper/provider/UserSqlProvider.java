package com.jc.crm.mapper.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author asuis
 * @version: UserSqlProvider.java 18-12-9:下午2:02
 */
public class UserSqlProvider {
    private final static Logger logger = LoggerFactory.getLogger(UserSqlProvider.class);
    public String queryUsers(@Param("keyword") String keyword, @Param("eid") Integer eid, @Param("uid") Integer uid) {
        String sql = new SQL(){{
            SELECT("*");
            FROM("`user`");
            if (keyword!=null) {
                if (!"".equals(keyword.trim())) {
                    WHERE("`user`.username LIKE CONCAT('%',#{keyword},'%')");
                    AND();
                }
            }
            WHERE("eid = #{eid} AND uid != #{uid}");
        }}.toString();
        logger.info("执行Sql:" + sql);
        return sql;
    }
}
