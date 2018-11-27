package com.jc.crm.controller;

import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.form.consumer.ConsumerForm;
import com.jc.crm.service.consumer.ConsumerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 客户信息管理控制层
 * @author asuis
 * @version: ConsumerController.java 18-11-25:下午6:22
 */
@Api("客户相关Api")
@RestController
@RequestMapping("api/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerServiceImpl consumerService;

    @ApiOperation(value = "添加客户", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })

    @PostMapping
    public Result addConsumers(@Validated @RequestBody ConsumerForm consumerForm,
                               BindingResult result,
                               @RequestAttribute Integer uid){
        String flag;
        String msg1 = "成功";
        String msg2 = "已存在";
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getFieldError().toString());
        }
        try{
            flag = consumerService.addConsumers(consumerForm,uid);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(msg1)) {
            return Result.fail(ResultStatus.SUCCESS, "添加成功");
        }
        if (flag.equals(msg2)){
            return Result.fail(ResultStatus.EXISTED, "该条信息已存在");
        }else{
            return Result.fail(ResultStatus.FAIL, "操作失败");
        }
    }
}
