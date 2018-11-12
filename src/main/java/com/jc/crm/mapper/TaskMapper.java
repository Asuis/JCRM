package com.jc.crm.mapper;

import com.jc.crm.model.TaskEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface TaskMapper {
    @Insert("INSERT INFO tasks(description, holder, deadline, repeat_setting_id, is_repeat," +
            " start_time,is_remind state,priority) VALUES(#{description}, #{holder}, #{deadline}, #{repeatSettingId}, " +
            "#{isRepeat},#{startTime},#{isRemind},#{state},#{priority}) ")
    int insert(TaskEntity taskEntity);
    @Update("INSERT INFO tasks(description, holder, deadline, repeat_setting_id, is_repeat," +
            " start_time,is_remind state,priority) VALUES(#{description}, #{holder}, #{deadline}, #{repeatSettingId}, " +
            "#{isRepeat},#{startTime},#{isRemind},#{state},#{priority}) WHERE task_id = #{task_id}")
    int update(TaskEntity taskEntity);
    @Delete("DELETE FROM tasks where task_id = #{id}")
    int delete(int id);
}
