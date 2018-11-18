package com.jc.crm.controller;

import com.jc.crm.config.Result;
import com.jc.crm.form.EnterpriseForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("企业相关Api")
@RestController
@RequestMapping("api/enterprise")
public class EnterpriseController {
    @ApiOperation(value = "提交企业信息")
    @PostMapping
    public Result create(@RequestBody @Validated EnterpriseForm form, BindingResult result) {
        return null;
    }
    @ApiOperation(value = "修改企业信息，仅admin")
    @PutMapping
    public Result update() {
        return null;
    }
    @ApiOperation(value = "注销企业信息")
    @DeleteMapping
    public Result delete() {
        return null;
    }
}
