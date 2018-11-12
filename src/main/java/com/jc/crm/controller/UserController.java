package com.jc.crm.controller;

import com.jc.crm.config.Result;
import com.jc.crm.form.UserLoginForm;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("user")
public class UserController {
    @PostMapping("login")
    public Result<String> login(@RequestBody UserLoginForm form) {
        return null;
    }
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
