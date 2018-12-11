package com.jc.crm.mapper.provider;

import com.jc.crm.service.department.vo.DepartmentMemberVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 动态构建商业机会多表联合模糊查询的SQL语句
 * @author currysss 2018-11-28
 * */
public class BusinessOpportunitySqlProvider {

    private final static Logger logger = LoggerFactory.getLogger(BusinessOpportunitySqlProvider.class);

    public String queryListOne(@Param("keyword") String keyword, @Param("uidList") List<DepartmentMemberVO> uidList) {
        String sql = new SQL(){{
            SELECT("*");
            FROM("business_opp_source" ) ;
            if(uidList != null){
                String where = "holder =" + uidList.get(0);
                for (int i=1;i<uidList.size();i++) {
                    where += " OR holder = " + uidList.get(i).getUid();
                }
                WHERE(where);
            }
            if (keyword != null) {
                if(keyword.length() != 0) {
                    AND().WHERE("source_name LIKE '%" + keyword + "%'");
                }
            }
            ORDER_BY("opp_source_id ASC");
        }}.toString();

        logger.info("provider sql:"+sql);

        return sql;
    }

    public String queryListTwo(@Param("keyword") String keyword, @Param("uidList") List<DepartmentMemberVO> uidList) {
        String sql = new SQL(){{
            SELECT("*");
            FROM("business_opp" ) ;
            if(uidList != null){
                String where = "holder =" + uidList.get(0).getUid();
                for (int i=1;i<uidList.size();i++) {
                    where += " OR holder = " + uidList.get(i);
                }
                WHERE(where);
            }
            if (keyword != null) {
                if(keyword.length() != 0) {
                    AND().WHERE("opp_name LIKE '%" + keyword + "%'");
                }
            }
            ORDER_BY("business_opp_id ASC");
        }}.toString();

        logger.info("provider sql:"+sql);

        return sql;
    }

    public String queryListThree(@Param("keyword") String keyword, @Param("uidList") List<DepartmentMemberVO> uidList) {
        String sql = new SQL(){{
            SELECT("*");
            FROM("business_record r" ) ;
            INNER_JOIN(" business_opp o ON r.business_opp_id = o.business_opp_id");
            if(uidList != null){
                String where = "o.holder =" + uidList.get(0).getUid();
                for (int i=1;i<uidList.size();i++) {
                    where += " OR o.holder = " + uidList.get(i).getUid();
                }
                WHERE(where);
            }
            if (keyword != null) {
                if(keyword.length() != 0) {
                    AND().WHERE("o.opp_name LIKE '%" + keyword + "%'");
                }
            }
            ORDER_BY("opp_record_id ASC");
        }}.toString();

        logger.info("provider sql:"+sql);

        return sql;
    }
}
