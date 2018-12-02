package com.jc.crm.controller;

import com.github.pagehelper.PageInfo;
import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.config.logger.ControllerServiceLog;
import com.jc.crm.form.competitors.CompetitorsDeleteForm;
import com.jc.crm.form.competitors.CompetitorsInsertForm;
import com.jc.crm.form.competitors.CompetitorsSelectVo;
import com.jc.crm.form.competitors.CompetitorsUpdateForm;
import com.jc.crm.form.tag.CompetitorsTagLinkDeleteForm;
import com.jc.crm.form.tag.CompetitorsTagLinkInsertForm;
import com.jc.crm.service.competitors.CompetitorsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 竞争对手业务流程控制层
 * @author currysss 2018-11-18
 * */
@Api
@RestController
@RequestMapping("competitors")
public class CompetitorsController {

    private CompetitorsServiceImpl competitorsService;
    @Autowired
    public CompetitorsController(CompetitorsServiceImpl competitorsService) {
        this.competitorsService = competitorsService;
    }

    @ApiOperation(value = "添加竞争对手", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PostMapping
    @ControllerServiceLog
    public Result addCompetitors(@Validated @RequestBody CompetitorsInsertForm competitorsInsertForm,
                                 BindingResult result, @RequestAttribute Integer uid) {
        String flag;
        String mess1 = "成功";
        String mess2 = "已存在";
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getFieldError().toString());
        }
        try {
            flag = competitorsService.addCompetitors(competitorsInsertForm, uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "添加成功");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.EXISTED, "该条信息已存在");
        } else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }

    @ApiOperation(value = "修改竞争对手", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PutMapping
    @ControllerServiceLog
    public Result updateCompetitors(@Validated @RequestBody CompetitorsUpdateForm competitorsUpdateForm,
                                    BindingResult result, @RequestAttribute Integer uid) {
        String flag;
        String mess1 = "成功";
        String mess2 = "不存在";
        String mess3 = "权限不足";
        String mess4 = "已存在";
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getFieldError().toString());
        }
        try {
            flag = competitorsService.updateCompetitors(competitorsUpdateForm, uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "修改成功");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.NOT_FOUND, "该竞争对手不存在");
        }
        if (flag.equals(mess4)) {
            return Result.fail(ResultStatus.CONFLICT, "该公司名称已存在");
        }
        if (flag.equals(mess3)) {
            return Result.fail(ResultStatus.NO_AUTHORITY, "权限不够");
        } else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }

    @ApiOperation(value = "删除竞争对手", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PutMapping("delete")
    @ControllerServiceLog
    public Result deleteCompetitors(@Validated @RequestBody CompetitorsDeleteForm competitorsDeleteForm, @RequestAttribute Integer uid) {
        String flag;
        String mess1 = "成功";
        String mess2 = "不存在";
        String mess3 = "权限不足";
        String mess4 = "错误数据格式";
        try {
            flag = competitorsService.deleteCompetitors(competitorsDeleteForm, uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "修改成功");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.NOT_FOUND, "该竞争对手不存在");
        }
        if (flag.equals(mess4)) {
            return Result.fail(ResultStatus.ERRO_FORMAT, "传输的数据格式错误");
        }
        if (flag.equals(mess3)) {
            return Result.fail(ResultStatus.NO_AUTHORITY, "权限不够");
        } else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }

    @ApiOperation(value = "为竞争对手附加标签", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PostMapping("add")
    @ControllerServiceLog
    public Result attachCompetitorsTag(@RequestBody CompetitorsTagLinkInsertForm competitorsTagLinkInsertForm,
                                       @RequestAttribute Integer uid) {
        String flag;
        String mess1 = "成功";
        String mess2 = "不存在";
        String mess3 = "权限不足";
        String mess4 = "已存在";
        try {
            flag = competitorsService.addCompetitorsTag(competitorsTagLinkInsertForm,uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "成功给该竞争对手附加标签");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.NOT_FOUND, "该标签Id或该竞争对手Id不存在");
        }
        if (flag.equals(mess3)) {
            return Result.fail(ResultStatus.NO_AUTHORITY, "权限不足");
        }
        if (flag.equals(mess4)) {
            return Result.fail(ResultStatus.EXISTED, "标签已存在");
        }else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }

    @ApiOperation(value = "为竞争对手移除标签", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @DeleteMapping
    @ControllerServiceLog
    public Result removeCompetitorsTag(@RequestBody CompetitorsTagLinkDeleteForm competitorsTagLinkDeleteForm,
                                       @RequestAttribute Integer uid) {
        String flag;
        String mess1 = "成功";
        String mess2 = "不存在";
        String mess3 = "权限不足";

        try {
            flag = competitorsService.deleteCompetitorsTag(competitorsTagLinkDeleteForm, uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "成功给该竞争移除标签");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.NOT_FOUND, "该竞争对手与标签链接Id不存在");
        }if (flag.equals(mess3)) {
            return Result.fail(ResultStatus.NO_AUTHORITY, "权限不足");
        }else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }

    @ApiOperation(value = "根据关键字查询竞争对手信息", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @GetMapping
    @ControllerServiceLog
    public Result searchCompetitors(@RequestParam(value = "keyword", required = false) String keyword,
                                    @RequestAttribute Integer uid,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "7") Integer pageSize) {
        PageInfo<CompetitorsSelectVo> pageInfo;
        try {
            pageInfo = competitorsService.selectListByKeyWord(keyword, uid,pageNum, pageSize);
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

    @ApiOperation(value = "查询自己挖掘的竞争对手信息", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @GetMapping("search")
    @ControllerServiceLog
    public Result searchCompetitorsByMyself(@RequestAttribute Integer uid,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo<CompetitorsSelectVo> pageInfo;
        try {
            pageInfo = competitorsService.selectListByHolder(uid, pageNum, pageSize);
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
