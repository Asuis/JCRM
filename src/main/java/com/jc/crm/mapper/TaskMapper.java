package com.jc.crm.mapper;

import com.jc.crm.form.task.RepeatSettingForm;
import com.jc.crm.model.TaskEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author asuis
 */
@Repository
public interface TaskMapper {
    /**
     * @param taskEntity tasks实体
     * */
    @Insert("INSERT INTO task(description, holder, deadline, repeat_setting_id, is_repeat," +
            " start_time,is_remind,state,priority) VALUES(#{description}, #{holderId}, #{deadline}, #{repeatSettingId}, " +
            "#{isRepeat},#{startTime},#{isRemind},#{state},#{priority}) ")
    @Options(useGeneratedKeys = true, keyProperty = "taskId", keyColumn = "task_id")
    int insert(TaskEntity taskEntity);
    @Update("UPDATE task SET holder_id = #{holderId} WHERE task_id =#{taskId}")
    int changeHolder(@Param("taskId")Integer taskId, @Param("holderId")Integer holderId);
    @Update("INSERT INFO task(description, holder, deadline, repeat_setting_id, is_repeat," +
            " start_time,is_remind state,priority) VALUES(#{description}, #{holderId}, #{deadline}, #{repeatSettingId}, " +
            "#{isRepeat},#{startTime},#{isRemind},#{state},#{priority}) WHERE task_id = #{task_id}")
    int update(TaskEntity taskEntity);
    @Delete("DELETE FROM task where task_id = #{id}")
    int delete(int id);
    @Insert("INSERT INTO tasks_repeat_setting(interval_time, task_id, start_time, end_time, remind_time, repeat_type) values(#{interval}, #{taskId}, #{startTime}, #{endTime}, #{remindTime}, #{repeatType})")
    int insertRepeatSetting(RepeatSettingForm repeatSettingForm);
    @Insert("INSERT INTO tasks_business_opp_link(task_id, opp_id) values(#{taskId},#{oppId})")
    int insertTaskBusinessOpp(@Param("taskId")Integer taskId, @Param("oppId")Integer oppId);
    @Insert("INSERT INTO tasks_consumer_link(task_id,consumer_id) VALUES(#{taskId},#{consumerId})")
    int insertTaskConsumer(@Param("taskId") Integer taskId, @Param("consumerId") Integer consumerId);
    @Select("SELECT * FROM task,tasks_holder_link WHERE tasks_holder_link.holder_id = #{uid}")
    List<TaskEntity> getRemindTaskForUser(Integer uid);
}
