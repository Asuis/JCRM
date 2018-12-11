package com.jc.crm.mapper.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 动态构建竞争对手多表联合模糊查询的SQL语句
 * @author currysss 2018-11-20
 * */
public class CompetitorSqlProvider {

    private final static Logger logger = LoggerFactory.getLogger(CompetitorSqlProvider.class);

    public String queryList(@Param("keyword") String keyword, @Param("uidList") List<Integer> uidList) {
        String sql = new SQL(){{
            SELECT("*");
            FROM("competitors" ) ;
            if(uidList != null){
                String where = "holder =" + uidList.get(0);
                for (int i=1;i<uidList.size();i++) {
                    where +=" OR holder = "+uidList.get(i);
                }
                WHERE(where);
            }
            AND().WHERE("status = 1");
            if (keyword != null) {
                if(keyword.length() != 0) {
                    AND().WHERE("competitor_name LIKE '%" + keyword + "%'");
                }
            }
            ORDER_BY("competitor_id ASC");
        }}.toString();

        logger.info("provider sql:"+sql);

        return sql;
    }
}
