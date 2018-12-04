package com.jc.crm.controller;

import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.config.logger.ControllerServiceLog;
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

    @ApiOperation(value = "客户地区分析", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @GetMapping("areaConsumer")
    @ControllerServiceLog
    public Result addConsumers(@RequestParam (value = "cid", defaultValue = "")Integer cid,
                               @RequestAttribute Integer uid){
        String list;
        try{
            list = consumerService.selectArea(uid);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (list.length() != 0){
            return Result.success(list);
        }
        if (list.length() == 0){
            return Result.fail(ResultStatus.NOT_INFO, "没有信息");
        }else{
            return Result.fail(ResultStatus.FAIL, "发生异常");
        }
    }
}
