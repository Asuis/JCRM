package com.jc.crm.controller;

import com.github.pagehelper.PageInfo;
import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.config.logger.ControllerServiceLog;
import com.jc.crm.form.consumer.ConsumerForm;
import com.jc.crm.form.contacts.ContactForm;
import com.jc.crm.service.consumer.impl.ContactsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/12/4 0004.
 */

@Api("客户相关Api")
@RestController
@RequestMapping("api/contacts")
public class ContactsController {
    @Autowired
    private ContactsServiceImpl contactsService;

    @ApiOperation(value = "添加联系人", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PostMapping
    @ControllerServiceLog
    public Result addConsumers(@Validated @RequestBody ContactForm contactForm,
                               BindingResult result,
                               @RequestAttribute Integer uid){
        String flag;
        String msg1 = "成功";
        String msg2 = "已存在";
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getFieldError().toString());
        }
        try{
            flag = contactsService.addContact(contactForm,uid);
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

    @ApiOperation(value = "更新联系人信息", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PutMapping
    @ControllerServiceLog
    public Result updateConsumers(@Validated @RequestBody ContactForm contactForm,
                                  BindingResult result,
                                  @RequestAttribute Integer uid){
        String flag;
        String msg1 = "成功";
        String msg2 = "不存在";
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getFieldError().toString());
        }
        try{
            flag = contactsService.updateContact(contactForm,uid);
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

    @ApiOperation(value = "查询客户联系人", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @GetMapping
    @ControllerServiceLog
    public Result searchContact(@RequestParam (value = "keyword", defaultValue = "")String keyword,
                                @RequestParam (value = "cid", defaultValue = "")Integer cid,
                                @RequestAttribute Integer uid){
        PageInfo<ContactForm> pageInfo;
        try{
            pageInfo = contactsService.selectListByKeyWord(keyword,cid,uid);
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

    @ApiOperation(value="删除联系人", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PutMapping("delConstct")
    @ControllerServiceLog
    public Result delConstct(@Validated @RequestParam (value = "contactId",defaultValue = "")Integer contactId,
                                @RequestAttribute Integer uid){
        String flag;
        try{
            flag = contactsService.deleteContact(contactId,uid);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals("成功")){
            return Result.fail(ResultStatus.SUCCESS,"删除成功");
        }else {
            return  Result.fail(ResultStatus.FAIL,"操作失败");
        }
    }
}
