package com.jc.crm.mapper;

import com.jc.crm.mapper.provider.CompetitorSqlProvider;
import com.jc.crm.model.CompetitorsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompetitorsMapper {

    @Select("SELECT * FROM competitors")
    List<CompetitorsEntity> getCompetitorsList();

    @Insert("INSERT INTO competitors(competitor_name, description, types, ex_1, status, ctime, utime, holder) VALUES(#{competitorName},#{description},#{types},#{ex1},#{status},#{ctime},#{utime},#{holder})")
    int insert(CompetitorsEntity competitorsEntity);

    @Update("UPDATE competitors SET competitor_name = #{competitorName}, description = #{description}, types = #{types}, ex_1 = #{ex1}, utime = #{utime} WHERE competitor_id = #{competitorId}")
    int update(CompetitorsEntity competitorsEntity);

    @Update("UPDATE competitors SET status = #{status}, utime = #{utime} WHERE competitor_id = #{competitorId}")
    int updateStatus(CompetitorsEntity competitorsEntity);

    @Delete("DELETE FROM competitors where competitor_id = #{competitor_id}")
    int delete(int competitorId);

    @Select("SELECT * FROM competitors where competitor_name = #{competitorName}")
    CompetitorsEntity selectByCompetitorsName(String competitorName)throws Exception;

    @Select("SELECT * FROM competitors where competitor_id = #{competitorId}")
    CompetitorsEntity selectByPrimaryKey(int competitorId)throws Exception;

    @SelectProvider(type = CompetitorSqlProvider.class, method = "selectByKeyWord")
    List<CompetitorsEntity> selectByKeyWord(String keyword)throws Exception;

    @Select("SELECT * FROM competitors where holder = #{holder}")
    List<CompetitorsEntity> selectByHolder(int holder)throws Exception;
}
