package com.jc.crm.controller;

import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.form.account.RegisterForm;
import com.jc.crm.form.account.UserLoginForm;
import com.jc.crm.service.user.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Api
@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    @ApiResponse(code = 1,message = "hello",response = String.class, responseContainer = "Result.class")
    public Result login(@RequestBody @Validated UserLoginForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, Objects.requireNonNull(result.getFieldError()).toString());
        }
        try {
            String token = userService.login(form.getUsername(), form.getPassword());
            return Result.success(token);
        } catch (UserNotRightPassException e) {
            return Result.fail(ResultStatus.FAIL, "用户名或密码不正确");
        } catch (UserIsLockedException e) {
            return Result.fail(ResultStatus.FAIL, "该账户已被锁定,无法使用,如有疑问可咨询xxxx");
        } catch (UserNotFoundException e) {
            return Result.fail(ResultStatus.FAIL, "该账户不存在");
        }
    }
    @ApiOperation(value = "用户注册", response = Result.class)
    @PostMapping
    public Result register(@RequestBody @Validated RegisterForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, Objects.requireNonNull(result.getFieldError()).toString());
        }
        int code = ResultStatus.FAIL;
        try {
            code = userService.register(form);
            if (code > 0) {
                String token = userService.login(form.getEmail(), form.getPass());
                return Result.success(token);
            } else {
                return Result.fail(code, "未知错误");
            }
        } catch (UserAlreadyRegisterException e) {
            return Result.fail(code, e.getMessage());
        }
    }
    @PutMapping("exit")
    public Result exit(@RequestHeader("Authorization") String token) {
        if (token == null) return Result.fail(ResultStatus.FAIL, "异常访问");
        if (userService.logout(token)>0) {
            return Result.success(null);
        } else {
          return Result.fail(ResultStatus.FAIL, "服务器繁忙请稍后再试");
        }
    }
    @PutMapping("active")
    public Result<String> active() {
        return null;
    }
    @PutMapping
    public Result<String> update() {
        return null;
    }
}
