package com.jc.crm.mapper;

import com.jc.crm.form.task.RepeatSettingForm;
import com.jc.crm.mapper.provider.TaskSqlProvider;
import com.jc.crm.model.TaskEntity;
import com.jc.crm.query.TaskQuery;
import com.jc.crm.service.task.vo.RepeatSettingVO;
import com.jc.crm.service.task.vo.TaskDetail;
import com.jc.crm.service.task.vo.TaskSimpleVO;
import com.jc.crm.service.user.vo.UserSimpleVO;
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
    @Update("UPDATE task SET " +
            "description = #{description}, " +
            "holder = #{holderId}," +
            "deadline = #{deadline}," +
            "repeat_setting_id = #{repeatSettingId}," +
            "is_repeat = #{isRepeat}," +
            "start_time = #{startTime}," +
            "is_remind = #{isRemind}," +
            "state = #{state}," +
            "priority = #{priority} WHERE task_id = #{taskId}")
    int update(TaskEntity taskEntity);

    @Delete("DELETE FROM task where task_id = #{id}")
    int delete(int id);


    @Insert("INSERT INTO tasks_repeat_setting(interval_time, task_id, start_time, end_time, remind_time, repeat_type) values(#{interval}, #{taskId}, #{startTime}, #{endTime}, #{remindTime}, #{repeatType})")
    @Options(useGeneratedKeys = true, keyColumn = "repeat_id", keyProperty = "repeatId")
    int insertRepeatSetting(RepeatSettingForm repeatSettingForm);

    @Update("UPDATE tasks_repeat_setting SET " +
            "interval_time = #{interval}," +
            "start_time = #{startTime}," +
            "end_time = #{endTime}," +
            "remind_time = #{remindTime}," +
            "repeat_type = #{repeatType} WHERE repeat_id = #{repeatId}")
    int updateRepeatSetting(RepeatSettingForm repeatSettingForm);

    @Insert("INSERT INTO tasks_business_opp_link(task_id, opp_id) values(#{taskId},#{oppId})")
    int insertTaskBusinessOpp(@Param("taskId")Integer taskId, @Param("oppId")Integer oppId);

    @Update("UPDATE tasks_business_opp_link SET opp_id = #{oppId} WHERE task_id = #{taskId})")
    int updateTaskBusinessOpp(@Param("taskId")Integer taskId, @Param("oppId")Integer oppId);

    @Insert("INSERT INTO tasks_consumer_link(task_id,consumer_id) VALUES(#{taskId},#{consumerId})")
    int insertTaskConsumer(@Param("taskId") Integer taskId, @Param("consumerId") Integer consumerId);

    @Insert("INSERT INTO tasks_holder_link(holder_id, task_id) VALUES(#{holderId},#{taskId})")
    int insertTaskHolder(@Param("taskId")Integer taskId, @Param("holderId")Integer holderId);

    @Select("SELECT * FROM task,tasks_holder_link WHERE holder_id = #{uid} AND tasks_holder_link.task_id = task.task_id AND tasks_holder_link.state = 1")
    List<TaskEntity> getRemindTaskForUser(Integer uid);

    @Update("UPDATE task SET repeat_setting_id = #{repeatId} WHERE task_id = #{taskId}")
    int updateReapetSettingId(Integer repeatId, Integer taskId);

    @SelectProvider(type = TaskSqlProvider.class, method = "query")
    List<TaskSimpleVO> query(TaskQuery query);
    @Select("SELECT task.task_id, task.avatar, task.theme, task.start_time, task.deadline, task.description, task.state, task.priority, tasks_business_opp_link.opp_id, tasks_consumer_link.consumer_id FROM task LEFT JOIN tasks_business_opp_link ON task.task_id = tasks_business_opp_link.task_id LEFT JOIN tasks_consumer_link ON task.task_id = tasks_consumer_link.task_id WHERE task.task_id = #{taskId}")
    TaskDetail getTaskDetailsByTaskId(Integer taskId);
    @Select("SELECT * FROM tasks_repeat_setting WHERE task_id = #{taskId}")
    RepeatSettingVO getRepeatSettingVOByTaskId(Integer taskId);

    @Select("SELECT `user`.uid, `user`.avatar, `user`.username FROM `user`,task,tasks_holder_link WHERE `user`.uid = tasks_holder_link.holder_id AND tasks_holder_link.task_id = task.task_id AND task.task_id = #{taskId} AND tasks_holder_link.state = 1")
    List<UserSimpleVO> getHoldersByTaskId(Integer taskId);
}
