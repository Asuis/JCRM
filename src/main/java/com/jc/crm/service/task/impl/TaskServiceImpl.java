package com.jc.crm.service.task.impl;

import com.github.pagehelper.PageInfo;
import com.jc.crm.form.tag.TagInsertForm;
import com.jc.crm.form.task.TaskForm;
import com.jc.crm.model.TagEntity;
import com.jc.crm.service.task.TaskService;
import org.springframework.stereotype.Service;

/**
 * @author asuis
 * @version: TagServiceImpl.java 18-11-22:下午9:02
 */
@Service
public class TaskServiceImpl implements TaskService {
    /**
     * 创建任务
     */
    @Override
    public int createTaskForUser(TaskForm form, Integer uid) {
        return 0;
    }

    /**
     * 删除任务
     *
     * @param taskId
     */
    @Override
    public int removeTask(int taskId) {
        return 0;
    }

    /**
     * 工作移交
     *
     * @param taskId
     * @param userId
     */
    @Override
    public int changeTaskUser(int taskId, int userId) {
        return 0;
    }

    /**
     * 查询任务
     *
     * @param userId
     */
    @Override
    public int getTasks(int userId) {
        return 0;
    }

    /**
     * 传入tagInsertForm对象，自定义标签信息
     *
     * @param tagInsertForm 标签添加实体类对象
     * @return String类型的变量flag 初始化值为空 flag的值为成功 表示添加成功 其他值表示添加失败
     */
    @Override
    public String addTag(TagInsertForm tagInsertForm) {
        return null;
    }

    /**
     * 获得标签列表
     *
     * @param pageNum  页面数
     * @param pageSize 每页含有的数据数量
     * @return PageInfo对象
     */
    @Override
    public PageInfo<TagEntity> selectTagList(Integer pageNum, Integer pageSize) {
        return null;
    }
}
