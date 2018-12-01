package com.jc.crm.controller;

import com.github.pagehelper.PageInfo;
import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.form.AddressForm;
import com.jc.crm.form.consumer.ConsumerForm;
import com.jc.crm.model.Consumer;
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

    @ApiOperation(value = "查询客户", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @GetMapping("list")
    public Result searchStage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                              @RequestParam (value = "keyword", defaultValue = "")String keyword,
                              @RequestAttribute Integer uid){
        PageInfo<ConsumerForm> pageInfo;
        try{
            pageInfo = consumerService.selectListByKeyWord(keyword, pageNum, pageSize,uid);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (pageInfo.getTotal() != 0){
            return Result.success(pageInfo);
        }
        if (pageInfo.getTotal() == 0){
            return Result.fail(ResultStatus.NOT_INFO, "NULL");
        }else{
            return Result.fail(ResultStatus.FAIL, "FALSE");
        }

    }

    @ApiOperation(value = "更新客户", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PutMapping
    public Result updateConsumers(@Validated @RequestBody ConsumerForm consumerForm,
                               BindingResult result,
                               @RequestAttribute Integer uid){
        String flag;
        String msg1 = "成功";
        String msg2 = "不存在";
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getFieldError().toString());
        }
        try{
            flag = consumerService.updataConsumer(consumerForm,uid);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(msg1)) {
            return Result.fail(ResultStatus.SUCCESS, "修改成功");
        }
        if (flag.equals(msg2)){
            return Result.fail(ResultStatus.EXISTED, "信息不已存在");
        }else{
            return Result.fail(ResultStatus.FAIL, "操作失败");
        }
    }

    @ApiOperation(value = "删除客户", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PutMapping("delete")
    public Result deleteCompetitors(@Validated @RequestAttribute Integer uid,
                                        @RequestParam (value = "cid")Integer cid) {
        System.out.println(cid+"....................");
        String flag;
        String mess1 = "成功";
        String mess2 = "用户不存在";
        try {
            flag = consumerService.deleteConsumer(cid, uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "删除成功");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.NOT_FOUND, "用户不存在");
        }else {
            return Result.fail(ResultStatus.FAIL, "删除失败");
        }
    }
}