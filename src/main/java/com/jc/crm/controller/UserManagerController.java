package com.jc.crm.controller;

import com.jc.crm.config.Result;
import com.jc.crm.config.logger.ControllerServiceLog;
import com.jc.crm.form.account.AccountListSubmitForm;
import com.jc.crm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author asuis
 */
@RestController
@RequestMapping("api/manager/user")
public class UserManagerController {
    private final UserMapper userMapper;

    @Autowired
    public UserManagerController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostMapping
    @ControllerServiceLog
    public Result registerUserFromList(@RequestBody @Validated AccountListSubmitForm form) {
        return null;
    }
    @PutMapping
    @ControllerServiceLog
    public Result updateUser() {
        return null;
    }
    @DeleteMapping
    @ControllerServiceLog
    public Result delete() {
        return null;
    }
    @GetMapping
    @ControllerServiceLog
    public Result get() {
        userMapper.getUserList();
        return null;
    }
}
