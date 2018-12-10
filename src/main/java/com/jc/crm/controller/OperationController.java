package com.jc.crm.controller;

import com.jc.crm.config.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author asuis
 * @version: OperationController.java 18-12-9:下午9:30
 */
@Api("操作记录关联相关接口")
@RequestMapping
@RestController
public class OperationController {
    @GetMapping
    @ApiOperation("拉取操作列表")
    public Result get() {
        return null;
    }
    @DeleteMapping
    @ApiOperation("回退操作 admin only")
    public Result send() {
        return null;
    }
    @PutMapping
    @ApiOperation("更新通知状态")
    public Result update() {
        return null;
    }
}
