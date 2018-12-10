package com.jc.crm.controller;

import com.github.pagehelper.PageInfo;
import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.config.logger.ControllerServiceLog;
import com.jc.crm.form.task.TaskForm;
import com.jc.crm.model.TaskEntity;
import com.jc.crm.model.UserEntity;
import com.jc.crm.query.TaskQuery;
import com.jc.crm.service.task.TaskService;
import com.jc.crm.service.task.vo.TaskDetail;
import com.jc.crm.service.task.vo.TaskSimpleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author asuis
 */
@Api
@RestController
@RequestMapping("api/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @ControllerServiceLog
    public Result create(@Validated @RequestBody TaskForm taskForm, BindingResult result, @RequestAttribute("uid")Integer uid) {
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getAllErrors().get(0).getDefaultMessage());
        }
        if (taskService.createTaskForUser(taskForm, uid)> 0) {
            return Result.success(null);
        } else {
            return Result.fail(ResultStatus.FAIL, "系统繁忙,请稍后再试");
        }
    }
    @PutMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header"),
            @ApiImplicitParam(name = "taskForm", dataTypeClass = TaskForm.class)
    })
    @ControllerServiceLog
    public Result update(@Validated @RequestBody TaskForm form, BindingResult result, @RequestAttribute("uid") Integer uid) {
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getAllErrors().get(0).getDefaultMessage());
        }
        if (taskService.updateTask(form, uid) > 0) {
            return Result.success(null);
        } else {
            return Result.fail(ResultStatus.FAIL, "系统繁忙,请稍后再试");
        }
    }
    @DeleteMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @ControllerServiceLog
    public Result delete(@RequestParam("taskId")Integer taskId, @RequestAttribute("uid") Integer uid) {
        if (taskService.removeTask(taskId, uid)>0){
            return Result.success(null);
        } else {
            return Result.fail(ResultStatus.FAIL, "系统繁忙请稍后再试");
        }
    }
    @GetMapping("{uid}/{pageNum}/{pageSize}")
    @ControllerServiceLog
    public Result<PageInfo<TaskEntity>> get(@PathVariable("uid") int uid, @PathVariable("pageNum")Integer pageNum,@PathVariable("pageSize")Integer pageSize) {
        PageInfo<TaskEntity> taskEntities = taskService.getTasks(uid,pageSize,pageNum);
        if (taskEntities!=null) {
            return Result.success(taskEntities);
        } else {
            return Result.fail(ResultStatus.FAIL, "系统繁忙,请稍后再试");
        }
    }
    @PostMapping("query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @ControllerServiceLog
    public Result<PageInfo<TaskSimpleVO>> query(@RequestBody TaskQuery query, @RequestAttribute("uid")Integer uid) {
        PageInfo<TaskSimpleVO> pageInfo = taskService.queryTasks(query, uid);
        if (pageInfo!=null) {
            return Result.success(pageInfo);
        }
        return Result.fail(ResultStatus.FAIL, "系统繁忙,请稍后再试");
    }
    @GetMapping("detail/{taskId}")
    @ApiOperation("获取任务详情")
    public Result getTaskDetails(@PathVariable Integer taskId, @RequestAttribute("user")UserEntity user) {
        TaskDetail taskDetail = taskService.getTaskDetail(taskId);
        if (taskDetail!=null) {
            return Result.success(taskDetail);
        }
        return Result.fail(ResultStatus.FAIL, "error or task not found");
    }
}
