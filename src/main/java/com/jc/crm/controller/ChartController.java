package com.jc.crm.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.config.logger.ControllerServiceLog;
import com.jc.crm.form.consumer.ConsumerForm;
import com.jc.crm.form.opportunity.BusinessOpportunityLossReasonSelectVo;
import com.jc.crm.service.consumer.impl.ConsumerServiceImpl;
import com.jc.crm.service.opportunity.BusinessOpportunityServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/12/2 0002.
 */

@Api("图表展示Api")
@RestController
@RequestMapping("api/chart")
public class ChartController {

    @Autowired
    private ConsumerServiceImpl consumerService;

    @Autowired
    private BusinessOpportunityServiceImpl businessOpportunityService;

    @ApiOperation(value = "客户地区分析", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @GetMapping("areaConsumer")
    @ControllerServiceLog
    public Result addConsumers(@RequestParam (value = "cid", defaultValue = "")Integer cid,
                               @RequestAttribute Integer uid){
        JSONArray list;
        try{
            list = consumerService.selectArea(uid);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (list.size() != 0){
            return Result.success(list);
        }
        if (list.size() == 0){
            return Result.fail(ResultStatus.NOT_INFO, "没有信息");
        }else{
            return Result.fail(ResultStatus.FAIL, "发生异常");
        }
    }

    @ApiOperation(value = "业绩表", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @GetMapping("sumMoney")
    @ControllerServiceLog
    public Result sumMoney() {
        JSONArray list;
        try {
            list = businessOpportunityService.selectAccountM();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (list.size() != 0) {
            return Result.success(list);
        }
        if (list.size() == 0) {
            return Result.fail(ResultStatus.NOT_INFO, "查询信息为空");
        } else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }
}
