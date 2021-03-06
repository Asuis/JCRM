package com.jc.crm.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.config.logger.SystemServiceLog;
import com.jc.crm.form.tag.TagInsertForm;
import com.jc.crm.form.task.RepeatSettingForm;
import com.jc.crm.form.task.TaskForm;
import com.jc.crm.mapper.TaskMapper;
import com.jc.crm.model.TagEntity;
import com.jc.crm.model.TaskEntity;
import com.jc.crm.query.TaskQuery;
import com.jc.crm.service.department.DepartmentService;
import com.jc.crm.service.department.vo.DepartmentMemberVO;
import com.jc.crm.service.task.TaskService;
import com.jc.crm.service.task.vo.RepeatSettingVO;
import com.jc.crm.service.task.vo.TaskDetail;
import com.jc.crm.service.task.vo.TaskSimpleVO;
import com.jc.crm.service.user.vo.UserSimpleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author asuis
 * @version: TagServiceImpl.java 18-11-22:下午9:02
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;

    private final DepartmentService departmentService;

    @Autowired
    public TaskServiceImpl(TaskMapper taskMapper, DepartmentService departmentService) {
        this.taskMapper = taskMapper;
        this.departmentService = departmentService;
    }

    /**
     * 创建任务
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @SystemServiceLog
    public int createTaskForUser(TaskForm form, Integer uid) {
        int code = ResultStatus.FAIL;
        TaskEntity taskEntity = form.toTask();
        Integer taskId = taskMapper.insert(taskEntity);
        if (taskId<0) {
            return code;
        }
        taskId = taskEntity.getTaskId();
        if (form.getHolders()!=null){
            for (Integer holder:
                 form.getHolders()) {
                if (taskMapper.insertTaskHolder(taskId, holder)<0) {
                    throw new RuntimeException("商业机会-holder关联添加失败");
                }
            }
        }
        if (form.getBusinessOppId()!=null) {
            if (taskMapper.insertTaskBusinessOpp(form.getBusinessOppId(), taskId)<0){
                throw new RuntimeException("商业机会-任务关联添加失败");
            }
        }
        if (form.getConsumerId()!=null) {

            if (taskMapper.insertTaskConsumer(taskId, form.getConsumerId())<0) {
                throw new RuntimeException("任务所有者-关联添加失败");
            }
        }
        RepeatSettingForm repeatSettingForm = form.getRepeatSetting();
        if (repeatSettingForm!=null) {
            repeatSettingForm.setTaskId(taskId);

            int settingId = taskMapper.insertRepeatSetting(repeatSettingForm);
            if (settingId<0) {
                throw new RuntimeException("任务重复设置-添加失败");
            }
            if (taskMapper.updateReapetSettingId(repeatSettingForm.getRepeatId(), taskId)<0) {
                throw new RuntimeException("任务重复设置-关联失败");
            }
        }
        code = ResultStatus.SUCCESS;
        return code;
    }

    /**
     * 删除任务
     *
     * @param taskId
     */
    @Override
    @SystemServiceLog
    public int removeTask(int taskId, int uid) {
        /*
         * 判断是否有权限删除
         * */
        return taskMapper.delete(taskId);
    }

    /**
     * 工作移交
     *
     * @param taskId
     * @param userId
     */
    @Override
    @SystemServiceLog
    public int changeTaskUser(int taskId, int userId) {
        return taskMapper.changeHolder(taskId, userId);
    }

    /**
     * 查询任务
     *
     * @param userId
     */
    @Override
    @SystemServiceLog
    public PageInfo<TaskEntity> getTasks(int userId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<TaskEntity> taskEntities = taskMapper.getRemindTaskForUser(userId);
        return new PageInfo<>(taskEntities);
    }

    /**
     * 获取单个任务详细信息
     *
     * @param taskId
     */
    @Override
    @SystemServiceLog
    public TaskDetail getTaskDetail(Integer taskId) {
        TaskDetail taskDetail = taskMapper.getTaskDetailsByTaskId(taskId);
        if (taskDetail!=null) {
            List<UserSimpleVO> holders = taskMapper.getHoldersByTaskId(taskId);
            taskDetail.setHolders(holders);
            RepeatSettingVO repeatSettingVO = taskMapper.getRepeatSettingVOByTaskId(taskId);
            taskDetail.setRepeatSettingDetails(repeatSettingVO);
        }
        return taskDetail;
    }

    @Override
    @SystemServiceLog
    public int updateTask(TaskForm taskForm, int userId) {
        //todo

        return taskMapper.update(taskForm.toTask());
    }

    @Override
    public PageInfo<TaskSimpleVO> queryTasks(TaskQuery query, Integer uid) {
        List<Integer> uids = new ArrayList<>();
        if (query.isMe()) {
            //设置列表大小
            uids.add(uid);
            query.setUids(uids);
        } else {
            List<DepartmentMemberVO> members = departmentService.getIdsByUser(uid);
            for (DepartmentMemberVO member: members) {
                uids.add(member.getUid());
            }
            query.setUids(uids);
        }
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<TaskSimpleVO> list = taskMapper.query(query);
        if (list==null) {
            return null;
        }
        for (TaskSimpleVO taskSimpleVO : list) {
            List<UserSimpleVO> userSimple = taskMapper.getHoldersByTaskId(taskSimpleVO.getTaskId());
            taskSimpleVO.setHolders(userSimple);
        }
        return new PageInfo<>(list);
    }

    /**
     * 传入tagInsertForm对象，自定义标签信息
     *
     * @param tagInsertForm 标签添加实体类对象
     * @return String类型的变量flag 初始化值为空 flag的值为成功 表示添加成功 其他值表示添加失败
     */
    @Override
    @SystemServiceLog
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
    @SystemServiceLog
    public PageInfo<TagEntity> selectTagList(Integer pageNum, Integer pageSize) {
        return null;
    }
}
