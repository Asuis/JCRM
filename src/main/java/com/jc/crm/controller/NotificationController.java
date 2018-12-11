package com.jc.crm.controller;

import com.jc.crm.config.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author asuis
 * @version: NotificationController.java 18-12-9:下午9:24
 */
@Api("通知相关接口")
@RequestMapping("/api/notify")
@RestController
public class NotificationController {
    @GetMapping
    @ApiOperation("拉取通知")
    public Result get() {
        return null;
    }
    @PostMapping
    @ApiOperation("发送通知")
    public Result send() {
        return null;
    }
    @PutMapping
    @ApiOperation("更新通知状态")
    public Result update() {
        return null;
    }
}
