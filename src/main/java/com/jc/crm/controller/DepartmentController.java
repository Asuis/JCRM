package com.jc.crm.controller;

import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.form.department.DepartmentCreateForm;
import com.jc.crm.form.department.DepartmentUpdateForm;
import com.jc.crm.model.DepartmentEntity;
import com.jc.crm.model.UserEntity;
import com.jc.crm.service.department.DepartmentService;
import com.jc.crm.service.department.vo.DepartmentMemberVO;
import com.jc.crm.service.department.vo.UserDepartmentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author asuis
 * @version: DepartmentController.java 18-12-8:下午1:38
 */
@Api("部门相关接口")
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{departmentId}")
    @ApiOperation("获取部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    public Result get(@RequestAttribute("user") UserEntity user, @PathVariable Integer departmentId) {
        List<DepartmentEntity> departmentEntities;
        if (departmentId==0) {
            departmentEntities = departmentService.findAllDepartment(user.getEid());
        } else {
            departmentEntities = departmentService.findByPid(departmentId, user.getEid());
        }
        if (departmentEntities!=null) {
            return Result.success(departmentEntities);
        }
        return Result.fail(ResultStatus.FAIL, "系统繁忙请稍后再试");
    }
    @PostMapping
    @ApiOperation("新建部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    public Result create(@Validated @RequestBody DepartmentCreateForm form, BindingResult result,@RequestAttribute("user")UserEntity user) {
        if (result.hasErrors()) {
            Result.fail(ResultStatus.FAIL, result.getAllErrors().get(0).toString());
        }
        if (departmentService.createDepartment(form, user.getEid())>0) {
            return Result.success(null);
        }
        return Result.fail(ResultStatus.FAIL, "系统繁忙请稍后再试");
    }
    @PutMapping
    @ApiOperation("修改部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    public Result update(@Validated @RequestBody DepartmentUpdateForm form, @RequestAttribute("user")UserEntity user, BindingResult result) {
        if (result.hasErrors()) {
            Result.fail(ResultStatus.FAIL, result.getAllErrors().get(0).toString());
        }
        if (departmentService.updateDepartment(form)>0) {
            return Result.success(null);
        }
        return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后再试");
    }
    @DeleteMapping("{departmentId}")
    @ApiOperation("删除部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header")
    })
    public Result delete(@PathVariable Integer departmentId, @RequestAttribute("user")UserEntity user) {
        if (departmentService.deleteUserDepartment(user.getUid(), departmentId)>0) {
            return Result.success(null);
        }
        return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后再试");
    }
    @GetMapping("user")
    @ApiOperation("获取用户所在部门")
    public Result getUserDepartment(@RequestAttribute("user")UserEntity userEntity) {
        List<UserDepartmentVO> departmentVOList = departmentService.getUserDepartment(userEntity.getUid(),userEntity.getEid());
        if (departmentVOList!=null) {
            return Result.success(departmentVOList);
        }
        return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后再试");
    }
    @GetMapping("/users/{departmentId}")
    public Result getDepartmentUsers(@RequestAttribute("user") UserEntity user, @PathVariable Integer departmentId) {
        List<DepartmentMemberVO> list = departmentService.getIdsByUser(user.getUid());
        if (list!=null) {
            return Result.success(list);
        }
        return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后再试");
    }
    @GetMapping("/detail/{departmentId}")
    @ApiOperation("获取部门详细信息")
    public Result getDetail(@RequestAttribute("user") UserEntity user, @PathVariable Integer departmentId) {
        //todo 权限设置
        DepartmentEntity departmentEntity = departmentService.getDepartmentDetail(departmentId);
        if (departmentEntity!=null) {
            return Result.success(departmentEntity);
        } else {
            return Result.fail(ResultStatus.NOT_FOUND, "该部门不存在");
        }
    }
}
