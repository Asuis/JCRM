package com.jc.crm.mapper;

import com.jc.crm.form.competitors.CompetitorsSelectVo;
import com.jc.crm.form.tag.TagVo;
import com.jc.crm.mapper.provider.CompetitorSqlProvider;
import com.jc.crm.model.CompetitorsEntity;
import com.jc.crm.model.CompetitorsTagLinkEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 竞争对手数据访问层接口
 * @author currysss 2018-11-16
 * */
@Mapper
@Repository
public interface CompetitorMapper {

    /**
     * 添加竞争对手信息
     * @param competitorsEntity 竞争对手表(competitors)实体类
     * @return int类型的变量
     * */
    @Insert("INSERT INTO competitors(competitor_name, description, types, ex_1, status, ctime, utime, holder) VALUES(#{competitorName},#{description},#{types},#{ex1},#{status},#{ctime},#{utime},#{holder})")
    int insert(CompetitorsEntity competitorsEntity);

    /**
     * 为竞争对手添加标签信息
     * @param competitorsTagLinkEntity 竞争对手与标签链接表(competitors_tag_link)实体类
     * @return int类型的变量
     * */
    @Insert("INSERT INTO competitors_tag_link(tag_id, competitor_id, ctime) VALUES(#{tagId},#{competitorId},#{ctime})")
    int attach(CompetitorsTagLinkEntity competitorsTagLinkEntity);

    /**
     * 修改竞争对手信息
     * @param competitorsEntity 竞争对手表(competitors)实体类
     * @return int类型的变量
     * */
    @Update("UPDATE competitors SET competitor_name = #{competitorName}, description = #{description}, types = #{types}, ex_1 = #{ex1}, utime = #{utime} WHERE competitor_id = #{competitorId} AND holder = #{holder}")
    int update(CompetitorsEntity competitorsEntity);

    /**
     * 修改竞争对手状态信息
     * @param competitorsEntity 竞争对手表(competitors)实体类
     * @return int类型的变量
     * */
    @Update("UPDATE competitors SET status = #{status}, utime = #{utime} WHERE competitor_id = #{competitorId} AND holder = #{holder}")
    int updateStatus(CompetitorsEntity competitorsEntity);

    /**
     * 根据竞争对手与标签的链接ID为竞争对手移除标签
     * @param competitorsTagLinkEntity 竞争对手与标签链接表(competitors_tag_link)实体类
     * @return int类型的变量
     * */
    @Delete("DELETE FROM competitors_tag_link WHERE competitors_tag_link_id = #{competitorsTagLinkId}")
    int delete(CompetitorsTagLinkEntity competitorsTagLinkEntity);

    /**
     * 根据竞争对手名称查询竞争对手列表信息
     * @param competitorName 竞争对手名称
     * @return CompetitorsEntity对象
     * */
    @Select("SELECT * FROM competitors where competitor_name = #{competitorName}")
    CompetitorsEntity selectByCompetitorsName(String competitorName);

    /**
     * 根据竞争对手ID查询竞争对手列表信息
     * @param competitorId 竞争对手ID
     * @return CompetitorsEntity对象
     * */
    @Select("SELECT * FROM competitors where competitor_id = #{competitorId}")
    CompetitorsEntity selectByPrimaryKey(int competitorId);

    /**
     * 根据标签ID和竞争对手ID查询竞争对手与标签的链接列表信息
     * @param competitorId 竞争对手ID
     * @param tagId 标签ID
     * @return CompetitorsTagLinkEntity对象
     * */
    @Select("SELECT * FROM competitors_tag_link WHERE competitor_id = #{competitorId} AND tag_id = #{tagId}")
    CompetitorsTagLinkEntity selectByBothId(Integer competitorId,Integer tagId);

    /**
     * 根据竞争对手与标签的链接ID查询竞争对手与标签的链接列表信息
     * @param competitorsTagLinkId 竞争对手与标签的链接ID
     * @return CompetitorsTagLinkEntity对象
     * */
    @Select("SELECT * FROM competitors_tag_link WHERE competitors_tag_link_id = #{competitorsTagLinkId}")
    CompetitorsTagLinkEntity selectByCompetitorsTagLinkId(Integer competitorsTagLinkId);

    /**
     * 根据关键字和登录的用户ID多表关联动态模糊查询竞争对手列表信息
     * @param keyword 关键字
     * @param uid 登录用户ID
     * @return CompetitorsSelectVo类的泛型集合
     * */
    @SelectProvider(type = CompetitorSqlProvider.class, method = "queryList")
    List<CompetitorsSelectVo> selectByKeyWord(@Param("keyword") String keyword, @Param("uid") Integer uid);

    /**
     * 根据登录的用户ID多表关联查询该用户编辑的竞争对手列表信息
     * @param uid 登录用户ID
     * @return CompetitorsSelectVo类的泛型集合
     * */
    @Select("SELECT * FROM competitors c \n" +
            "INNER JOIN department_user_link l \n" +
            "ON c.holder = l.user_id \n" +
            "INNER JOIN department d \n" +
            "ON d.department_id = l.department_id \n" +
            " WHERE c.holder = #{holder} \n" +
            "AND l.is_actived = 1")
    List<CompetitorsSelectVo> selectByHolder(Integer uid);

    /**
     * 根据竞争对手ID查询该竞争对手含有的标签列表信息
     * @param competitorId 竞争对手ID
     * @return TagVo类的泛型集合
     * */
    @Select("SELECT t.tag_name, l.competitors_tag_link_id FROM tag t INNER JOIN competitors_tag_link l \n" +
            "ON t.tag_id = l.tag_id WHERE l.competitor_id = #{competitorId}")
    List<TagVo> selectTagByCompetitorId(Integer competitorId);
}
