package com.jc.crm.mapper;

import com.jc.crm.model.CompetitorsTagLinkEntity;
import com.jc.crm.model.TagEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 标签数据访问层接口
 * @author currysss 2018-11-18
 * */
@Mapper
@Repository
public interface TagMapper {

    /**
     * 查询标签列表信息
     * @return TagEntity类的泛型集合
     * */
    @Select("SELECT * FROM tag")
    List<TagEntity> getTagList();

    /**
     * 自定义标签信息
     * @param tagEntity 标签表(tag)实体类
     * @return int类型的变量
     * */
    @Insert("INSERT INTO tag(tag_name, description, pid) VALUES(#{tagName},#{description},#{pid})")
    int insert(TagEntity tagEntity);

    /**
     * 根据标签名称查询标签列表信息
     * @param tagName 标签名称
     * @return TagEntity对象
     * */
    @Select("SELECT * FROM tag WHERE tag_name = #{tagName}")
    TagEntity selectByTagName(String tagName);


    /**
     * 根据标签ID查询标签列表信息
     * @param tagId 标签ID
     * @return TagEntity对象
     * */
    @Select("SELECT * FROM tag WHERE tag_id = #{tagId}")
    TagEntity selectByTagId(Integer tagId);

}
