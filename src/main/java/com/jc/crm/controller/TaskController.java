package com.jc.crm.controller;

import com.jc.crm.config.Result;
import com.jc.crm.model.TaskEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {
    @PostMapping
    public Result create() {
        return null;
    }
    @PutMapping
    public Result update() {
        return null;
    }
    @DeleteMapping
    public Result delete() {
        return null;
    }
    @GetMapping("${uid}")
    public Result<List<TaskEntity>> get(@PathVariable int uid) {
        return null;
    }
}
