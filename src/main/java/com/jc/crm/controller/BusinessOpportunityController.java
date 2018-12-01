package com.jc.crm.controller;

import com.github.pagehelper.PageInfo;
import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.form.opportunity.*;
import com.jc.crm.model.BusinessOpportunitySourceEntity;
import com.jc.crm.service.opportunity.BusinessOpportunityServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商业机会业务流程控制层
 * @author currysss 2018-11-23
 * */
@Api
@RestController
@RequestMapping("opportunity")
public class BusinessOpportunityController {

    private final BusinessOpportunityServiceImpl businessOpportunityService;
    @Autowired
    public BusinessOpportunityController(BusinessOpportunityServiceImpl businessOpportunityService) {
        this.businessOpportunityService = businessOpportunityService;
    }

    @ApiOperation(value = "添加商业机会", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PostMapping
    public Result addOpportunity(@Validated @RequestBody BusinessOpportunityInsertForm businessOpportunityInsertForm,
                                 BindingResult result, @RequestAttribute Integer uid) {
        String flag;
        String mess1 = "成功";
        String mess2 = "已存在";
        String mess3 = "不存在";
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getFieldError().toString());
        }
        try {
            flag = businessOpportunityService.addBusinessOpportunity(businessOpportunityInsertForm,uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "添加成功");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.EXISTED, "该条信息已存在");
        }
        if (flag.equals(mess3)) {
            return Result.fail(ResultStatus.NOT_FOUND, "该市场来源或阶段不存在");
        } else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }

    @ApiOperation(value = "机会所有者修改商业机会", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PutMapping
    public Result updateOpportunity(@Validated @RequestBody BusinessOpportunityUpdateForm businessOpportunityUpdateForm,
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
            flag = businessOpportunityService.updateBusinessOpportunity(businessOpportunityUpdateForm, uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "修改成功");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.NOT_FOUND, "该商业机会或市场来源或阶段或丢失原因不存在");
        }
        if (flag.equals(mess3)) {
            return Result.fail(ResultStatus.NO_AUTHORITY, "权限不够");
        }
        if (flag.equals(mess4)) {
            return Result.fail(ResultStatus.CONFLICT, "该商业机会名称已存在");
        } else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }

    @ApiOperation(value = "机会跟进者修改商业机会", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PutMapping("partial")
    public Result updateOpportunityPartial(@Validated @RequestBody BusinessOpportunityUpdatePartialForm businessOpportunityUpdatePartialForm,
                                    BindingResult result, @RequestAttribute Integer uid) {
        String flag;
        String mess1 = "成功";
        String mess2 = "不存在";
        String mess3 = "权限不足";
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getFieldError().toString());
        }
        try {
            flag = businessOpportunityService.updateBusinessOpportunityPartial(businessOpportunityUpdatePartialForm, uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "修改成功");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.NOT_FOUND, "该商业机会或阶段或丢失原因不存在");
        }
        if (flag.equals(mess3)) {
            return Result.fail(ResultStatus.NO_AUTHORITY, "权限不够");
        } else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }

    @ApiOperation(value = "删除商业机会", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PutMapping("delete")
    public Result deleteBusinessOpportunity(@Validated @RequestBody BusinessOpportunityDeleteForm businessOpportunityDeleteForm, @RequestAttribute Integer uid) {
        String flag;
        String mess1 = "成功";
        String mess2 = "不存在";
        String mess3 = "权限不足";
        String mess4 = "错误数据格式";
        try {
            flag = businessOpportunityService.deleteBusinessOpportunity(businessOpportunityDeleteForm, uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "修改成功");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.NOT_FOUND, "该商业机会不存在");
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

    @ApiOperation(value = "添加市场来源", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PostMapping("source")
    public Result addOpportunitySource(@Validated @RequestBody BusinessOpportunitySourceInsertForm businessOpportunitySourceInsertForm,
                                 BindingResult result, @RequestAttribute Integer uid) {
        String flag;
        String mess1 = "成功";
        String mess2 = "已存在";
        String mess3 = "不存在";
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getFieldError().toString());
        }
        try {
            flag = businessOpportunityService.addBusinessOpportunitySource(businessOpportunitySourceInsertForm,uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "添加成功");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.EXISTED, "该条信息已存在");
        }
        if (flag.equals(mess3)) {
            return Result.fail(ResultStatus.NOT_FOUND, "该状态或类别不存在");
        } else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }

    @ApiOperation(value = "修改市场活动来源", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PutMapping("source")
    public Result updateOpportunitySource(@Validated @RequestBody BusinessOpportunitySourceUpdateForm businessOpportunitySourceUpdateForm,
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
            flag = businessOpportunityService.updateBusinessOpportunitySource(businessOpportunitySourceUpdateForm, uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "修改成功");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.NOT_FOUND, "该市场活动或状态或类别不存在");
        }
        if (flag.equals(mess3)) {
            return Result.fail(ResultStatus.NO_AUTHORITY, "权限不够");
        }
        if (flag.equals(mess4)) {
            return Result.fail(ResultStatus.CONFLICT, "该市场活动来源名称已存在");
        }else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }

    @ApiOperation(value = "添加修改申请", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PostMapping("application")
    public Result addOpportunityApplication(@Validated @RequestBody BusinessOpportunityApplicationInsertForm businessOpportunityApplicationInsertForm,
                                 BindingResult result, @RequestAttribute Integer uid) {
        String flag;
        String mess1 = "成功";
        String mess2 = "权限不足";
        String mess3 = "不存在";
        String mess4 = "不需要";
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getFieldError().toString());
        }
        try {
            flag = businessOpportunityService.addBusinessOpportunityApplication(businessOpportunityApplicationInsertForm,uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "添加成功");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.NO_AUTHORITY, "权限不足");
        }
        if (flag.equals(mess3)) {
            return Result.fail(ResultStatus.NOT_FOUND, "该商业机会不存在");
        }
        if (flag.equals(mess4)) {
            return Result.fail(ResultStatus.NO_NEED, "不需要申请，可直接编辑");
        } else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }

    @ApiOperation(value = "同意修改申请", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PutMapping("agree")
    public Result agreeOpportunityApplication(@RequestBody BusinessOpportunityAgreeApplicationForm businessOpportunityAgreeApplicationForm,
                                          @RequestAttribute Integer uid) {
        String flag;
        String mess1 = "成功";
        String mess2 = "不存在";
        String mess3 = "权限不足";

        try {
            flag = businessOpportunityService.agreeBusinessOpportunityApplication(businessOpportunityAgreeApplicationForm, uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "修改成功");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.NOT_FOUND, "该申请或机会不存在");
        }
        if (flag.equals(mess3)) {
            return Result.fail(ResultStatus.NO_AUTHORITY, "权限不够");
        } else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }

    @ApiOperation(value = "拒绝修改申请", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @PutMapping("reject")
    public Result rejectOpportunityApplication(@Validated @RequestBody BusinessOpportunityRejectApplicationForm businessOpportunityRejectApplicationForm,
                                               BindingResult result, @RequestAttribute Integer uid) {
        String flag;
        String mess1 = "成功";
        String mess2 = "不存在";
        String mess3 = "权限不足";

        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getFieldError().toString());
        }
        try {
            flag = businessOpportunityService.rejectBusinessOpportunityApplication(businessOpportunityRejectApplicationForm, uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION, "发生异常：e.getMessage()");
        }
        if (flag.equals(mess1)) {
            return Result.fail(ResultStatus.SUCCESS, "修改成功");
        }
        if (flag.equals(mess2)) {
            return Result.fail(ResultStatus.NOT_FOUND, "该申请或机会不存在");
        }
        if (flag.equals(mess3)) {
            return Result.fail(ResultStatus.NO_AUTHORITY, "权限不够");
        } else {
            return Result.fail(ResultStatus.FAIL, "失败");
        }
    }

    @ApiOperation(value = "获得商业机会阶段列表", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @GetMapping("stage")
    public Result searchStage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {
        PageInfo<BusinessOpportunityStageSelectVo> pageInfo;
        try {
            pageInfo = businessOpportunityService.selectStageList(pageNum, pageSize);
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

    @ApiOperation(value = "获得商业机会市场来源列表", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @GetMapping("source")
    public Result searchSource(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo<BusinessOpportunitySourceSelectVo> pageInfo;
        try {
            pageInfo = businessOpportunityService.selectSourceList(pageNum, pageSize);
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

    @ApiOperation(value = "获得商业机会丢失原因列表", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @GetMapping("reason")
    public Result searchLossReason(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo<BusinessOpportunityLossReasonSelectVo> pageInfo;
        try {
            pageInfo = businessOpportunityService.selectLossReasonList(pageNum, pageSize);
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

    @ApiOperation(value = "获得商业机会市场来源状态列表", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @GetMapping("status")
    public Result searchSourceStatus(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize) {
        PageInfo<BusinessOpportunitySourceStatusSelectVo> pageInfo;
        try {
            pageInfo = businessOpportunityService.selectSourceStatusList(pageNum, pageSize);
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

    @ApiOperation(value = "获得商业机会市场来源类别列表", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @GetMapping("type")
    public Result searchSourceType(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "9") Integer pageSize) {
        PageInfo<BusinessOpportunitySourceTypeSelectVo> pageInfo;
        try {
            pageInfo = businessOpportunityService.selectSourceTypeList(pageNum, pageSize);
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

    @ApiOperation(value = "根据关键字查询市场活动来源信息", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @GetMapping("searchSource")
    public Result searchSourceByKeyWord(@RequestParam(value = "keyword", required = false) String keyword,
                                    @RequestAttribute Integer uid,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "7") Integer pageSize) {
        PageInfo<BusinessOpportunitySourceEntity> pageInfo;
        try {
            pageInfo = businessOpportunityService.selectSourceListByKeyWord(keyword, uid,pageNum, pageSize);
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

    @ApiOperation(value = "根据关键字查询商业机会信息", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @GetMapping("searchOpp")
    public Result searchOppByKeyWord(@RequestParam(value = "keyword", required = false) String keyword,
                                        @RequestAttribute Integer uid,
                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "7") Integer pageSize) {
        PageInfo<BusinessOpportunitySelectVo> pageInfo;
        try {
            pageInfo = businessOpportunityService.selectOppListByKeyWord(keyword, uid,pageNum, pageSize);
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

    @ApiOperation(value = "根据关键字查询机会完成记录信息", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    @GetMapping("searchRecord")
    public Result searchRecordByKeyWord(@RequestParam(value = "keyword", required = false) String keyword,
                                        @RequestAttribute Integer uid,
                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "7") Integer pageSize) {
        PageInfo<BusinessRecordSelectVo> pageInfo;
        try {
            pageInfo = businessOpportunityService.selectRecordListByKeyWord(keyword, uid,pageNum, pageSize);
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
