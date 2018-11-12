package com.jc.crm.service.task;

import com.jc.crm.service.tag.TagService;

public interface TaskService extends TagService {
    int createTask();
    int removeTask(int taskId);
    int changeTaskUser(int taskId, int userId);
    int getTasks(int userId);
}
