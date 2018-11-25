package com.jc.crm.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author asuis
 * @version: AuthMapper.java 18-11-25:下午6:17
 */
@Repository
public interface AuthMapper {
    @Select("SELECT\n" +
            "COUNT(`user`.uid)\n" +
            "WHERE\n" +
            "(\n" +
            "`user`.uid = department_user_link.user_id\n" +
            "AND department.department_id = department_user_link.department_id\n" +
            "AND department.struct LIKE CONCAT(#{uid}, '%')\n" +
            "OR (\n" +
            "department.department_id = #{departmentId}" +
            "AND department_user_link.weight <= #{weight}" +
            ")\n" +
            "AND eid = #{eid}\n" +
            ")\n" +
            "AND uid = #{uid2}" +
            "GROUP BY uid")
    int isHaveAuth(@Param("eid") Integer eid, @Param("uid") Integer uid, @Param("departmentId")Integer departmentId,@Param("uid2")Integer uid2,@Param("weight") int weight);
}
