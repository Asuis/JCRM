package com.jc.crm.controller;

import com.jc.crm.config.Result;
import com.jc.crm.form.account.AccountListSubmitForm;
import org.apache.ibatis.annotations.Delete;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/manager/user")
public class UserManagerController {
    @PostMapping
    public Result registerUserFromList(@RequestBody @Validated AccountListSubmitForm form) {
        return null;
    }
    @PutMapping
    public Result updateUser() {
        return null;
    }
    @DeleteMapping
    public Result delete() {
        return null;
    }
}
