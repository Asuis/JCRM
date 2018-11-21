package com.jc.crm.controller;

import com.github.pagehelper.PageInfo;
import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.form.tag.CompetitorsTagLinkInsertForm;
import com.jc.crm.form.tag.TagInsertForm;
import com.jc.crm.model.TagEntity;
import com.jc.crm.service.tag.TagServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 标签业务流程控制层
 * @author currysss 2018-11-19
 * */
@Api
@RestController
@RequestMapping("tag")
public class TagController {

    @Autowired
    private TagServiceImpl tagService;


    @ApiOperation(value = "添加标签信息", response = Result.class)
    @PostMapping
    public Result addTag(@Validated @RequestBody TagInsertForm tagInsertForm, BindingResult result) {
        String flag;
        String mess1 = "成功";
        String mess2 = "已存在";
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getFieldError().toString());
        }
        try {
            flag = tagService.addTag(tagInsertForm);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "标签添加成功");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.EXISTED, "该标签信息已存在");
        } else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }

    @ApiOperation(value = "获取标签信息", response = Result.class)
    @GetMapping
    public Result searchCompetitors(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo<TagEntity> pageInfo;
        try {
            pageInfo = tagService.selectTagList(pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (pageInfo.getTotal() != 0) {
            return Result.success(pageInfo);
        }
        if (pageInfo.getTotal() == 0) {
            return Result.fail(ResultStatus.NOT_INFO, "查询信息为空");
        } else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }

}
