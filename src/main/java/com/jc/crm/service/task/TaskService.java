package com.jc.crm.service.task;

import com.github.pagehelper.PageInfo;
import com.jc.crm.form.task.TaskForm;
import com.jc.crm.model.TaskEntity;
import com.jc.crm.query.TaskQuery;
import com.jc.crm.service.tag.TagService;
import com.jc.crm.service.task.vo.TaskDetail;
import com.jc.crm.service.task.vo.TaskSimpleVO;

/**
 * @author asuis
 */
public interface TaskService extends TagService {
    /**
     * 创建任务
     * */
    int createTaskForUser(TaskForm form, Integer uid);
    /**
     * 删除任务
     * */
    int removeTask(int taskId, int uid);
    /**
     * 工作移交
     * */
    int changeTaskUser(int taskId, int userId);
    /**
     * 查询任务
     * */
    PageInfo<TaskEntity> getTasks(int userId, Integer pageSize, Integer pageNum);
    /**
     * 获取单个任务详细信息
     * */
    TaskDetail getTaskDetail(Integer taskId);

    int updateTask(TaskForm taskForm, int userId);

    PageInfo<TaskSimpleVO> queryTasks(TaskQuery query, Integer uid);
}
