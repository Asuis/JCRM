package com.jc.crm.controller;

import com.jc.crm.config.Result;
import com.jc.crm.form.UserLoginForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("user")
public class UserController {
    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public Result<String> login(@RequestBody UserLoginForm form) {
        return null;
    }
    @ApiOperation(value = "用户注册", response = Result.class)
    @PostMapping("register")
    public Result<String> register(String email) {
        return null;
    }
    @PutMapping("exit")
    public Result<String> exit() {
        return null;
    }
    @PutMapping("active")
    public Result<String> active() {
        return null;
    }
    @PutMapping("update")
    public Result<String> update() {
        return null;
    }
}
