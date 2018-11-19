package com.jc.crm.controller;

import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.form.competitors.CompetitorsInsertForm;
import com.jc.crm.form.competitors.CompetitorsUpdateForm;
import com.jc.crm.service.competitors.CompetitorsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("competitors")
public class CompetitorsController {

    @Autowired
    private CompetitorsServiceImpl competitorsService;

    @ApiOperation(value = "添加竞争对手", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PostMapping
    public Result AddCompetitors(@Validated @RequestBody CompetitorsInsertForm competitorsInsertForm, BindingResult result, @RequestAttribute Integer uid){
        int flag = 0;
        if (result.hasErrors()){
            return Result.fail(ResultStatus.FAIL,result.getFieldError().toString());
        }
        try{
            flag = competitorsService.addCompetitors(competitorsInsertForm,uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION,"发生异常：e.getMessage()");
        }
        if(flag == 1){
            return Result.fail(ResultStatus.SUCCESS,"添加成功");
        }
        if(flag == 2){
            return Result.fail(ResultStatus.EXISTED,"该条信息已存在");
        }
        if (flag == 3) {
            return Result.fail(ResultStatus.NOT_INFO,"数据为空");

        }else{
            return Result.fail(ResultStatus.FAIL,"失败");
        }
    }

    @ApiOperation(value = "修改竞争对手", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PutMapping
    public Result UpdateCompetitors(@Validated @RequestBody CompetitorsUpdateForm competitorsUpdateForm, BindingResult result){
        int flag = 0;
        if (result.hasErrors()){
            return Result.fail(ResultStatus.FAIL,result.getFieldError().toString());
        }
        try{
            flag = competitorsService.updateCompetitors(competitorsUpdateForm);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION,"发生异常：e.getMessage()");
        }
        if(flag == 1){
            return Result.fail(ResultStatus.SUCCESS,"修改成功");
        }
        if(flag == 2){
            return Result.fail(ResultStatus.CONFLICT,"不存在");
        }else{
            return Result.fail(ResultStatus.FAIL,"失败");
        }
    }
}
