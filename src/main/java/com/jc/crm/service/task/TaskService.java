package com.jc.crm.service.task;

import com.jc.crm.service.tag.TagService;

/**
 * @author asuis
 */
public interface TaskService extends TagService {
    /**
     * 创建任务
     * */
    int createTaskForUser();
    /**
     * 删除任务
     * */
    int removeTask(int taskId);
    /**
     * 工作移交
     * */
    int changeTaskUser(int taskId, int userId);
    /**
     * 查询任务
     * */
    int getTasks(int userId);
}
