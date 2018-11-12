package com.jc.crm.mapper;

import com.jc.crm.model.TagEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface TagMapper {
    @Insert("INSERT INTO tag(tag_name, decription, pid) VALUES(#{tagName},#{decription},#{pid})")
    int insert(TagEntity tagEntity);
    @Delete("DELETE FROM tag WHERE tag_id = #{id}")
    int delete(int id);
}
