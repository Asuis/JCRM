package com.jc.crm.mapper;

import com.jc.crm.model.CompetitorsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompetitorsMapper {

    @Select("SELECT * FROM competitors")
    List<CompetitorsEntity> getCompetitorsList();

    @Insert("INSERT INTO competitors(competitor_name, description, types, ex_1, status, ctime, utime, holder) VALUES(#{competitorName},#{description},#{types},#{ex1},#{status},#{types},#{ctime},#{utime},#{holder})")
    int insert(CompetitorsEntity competitorsEntity);

    @Update("UPDATE competitors set competitor_name = #{competitorName}, description = #{description}, types = #{description}, ex_1, status) VALUES(#{competitor_name},#{description},#{types},#{ex1},#{status})")
    int update(CompetitorsEntity competitorsEntity);

    @Delete("DELETE FROM competitors where competitor_id = #{competitor_id}")
    int delete(int competitor_id);

    @Select("SELECT * FROM competitors where competitor_name = #{competitor_name}")
    CompetitorsEntity selectByPrimaryKey(String competitor_name)throws Exception;
}
