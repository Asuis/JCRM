package com.jc.crm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.config.logger.ControllerServiceLog;
import com.jc.crm.form.account.AccountListSubmitForm;
import com.jc.crm.form.account.RegisterForm;
import com.jc.crm.form.user.UpdateUserForm;
import com.jc.crm.mapper.UserMapper;
import com.jc.crm.model.UserEntity;
import com.jc.crm.service.department.DepartmentService;
import com.jc.crm.service.user.UserManagerService;
import com.jc.crm.service.user.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author asuis
 */
@Api("用户管理相关接口")
@RestController
@RequestMapping("api/manager/user")
public class UserManagerController {
    private final UserMapper userMapper;
    private final UserService userService;
    @Autowired
    private UserManagerService userManagerService;
    @Autowired
    public UserManagerController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping
    @ControllerServiceLog
    public Result registerUserFromList(@RequestBody @Validated AccountListSubmitForm form, @RequestAttribute("user")UserEntity user) {
        if (userService.registerFormList(form, user.getEid())>0) {
            return Result.success(null);
        }
        return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后重试");
    }
    @PutMapping
    @ControllerServiceLog
    public Result updateUser(@RequestBody UpdateUserForm form) {
        //todo
        return null;
    }
    @DeleteMapping("{uid}")
    @ControllerServiceLog
    public Result delete(@PathVariable("uid") Integer uid, @RequestAttribute("user")UserEntity userEntity) {
        //TODO 权限校验
        if (userMapper.delete(uid)>0) {
            return Result.success(null);
        }
        return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后再试");
    }
    @GetMapping
    @ControllerServiceLog
    public Result get(@RequestAttribute("user")UserEntity user, @RequestParam("pageSize")Integer pageSize, @RequestParam("pageNum")Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserEntity> userEntities = userMapper.getUserList(user.getEid(), user.getUid());
        if (userEntities!=null) {
            PageInfo<UserEntity> userEntityPageInfo = new PageInfo<>(userEntities);
            return Result.success(userEntityPageInfo);
        }
        return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后再试");
    }
}
