package com.jc.crm.controller;

import com.jc.crm.config.Result;
import com.jc.crm.model.TaskEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author asuis
 */
@Api
@RestController
@RequestMapping("api/task")
public class TaskController {
    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    public Result create() {
        return null;
    }
    @PutMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    public Result update() {
        return null;
    }
    @DeleteMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    public Result delete() {
        return null;
    }
    @GetMapping("{uid}")
    public Result<List<TaskEntity>> get(@PathVariable int uid) {
        return null;
    }
}
