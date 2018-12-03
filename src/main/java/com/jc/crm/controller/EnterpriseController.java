package com.jc.crm.controller;

import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.config.logger.ControllerServiceLog;
import com.jc.crm.form.enterprise.EnterpriseForm;
import com.jc.crm.service.enterprise.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author asuis
 */
@Api("企业相关Api")
@RestController
@RequestMapping("api/enterprise")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @ApiOperation(value = "提交企业信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PostMapping
    @ControllerServiceLog
    public Result create(@RequestBody @Validated EnterpriseForm form, BindingResult result, @RequestAttribute("uid")Integer uid) {
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getAllErrors().get(0).getDefaultMessage());
        }
        if (enterpriseService.bindEnterpriseForAdmin(form, uid)>0) {
            return Result.success(null);
        } else {
            return Result.fail(ResultStatus.FAIL, "绑定失败");
        }
    }
    @ApiOperation(value = "修改企业信息，仅admin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PutMapping
    @ControllerServiceLog
    public Result update() {
        return null;
    }
    @ApiOperation(value = "注销企业信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @DeleteMapping
    @ControllerServiceLog
    public Result delete() {
        return null;
    }
}
