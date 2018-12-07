package com.jc.crm.controller;

import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.config.logger.ControllerServiceLog;
import com.jc.crm.form.account.RegisterForm;
import com.jc.crm.form.account.UserLoginForm;
import com.jc.crm.form.account.UserUpdateForm;
import com.jc.crm.model.UserEntity;
import com.jc.crm.service.user.*;
import com.jc.crm.service.user.dto.QiniuUploadToken;
import com.jc.crm.service.user.exception.UserAlreadyRegisterException;
import com.jc.crm.service.user.exception.UserIsLockedException;
import com.jc.crm.service.user.exception.UserNotFoundException;
import com.jc.crm.service.user.exception.UserNotRightPassException;
import com.jc.crm.service.user.vo.UserDetailVO;
import com.jc.crm.utils.TimeUtils;
import com.jc.crm.utils.UUIDUtil;
import com.jc.crm.utils.UploadUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Api
@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    @ControllerServiceLog
    public Result login(@RequestBody @Validated UserLoginForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            String token = userService.login(form.getUsername(), form.getPassword());
            return Result.success(token);
        } catch (UserNotRightPassException e) {
            return Result.fail(ResultStatus.FAIL, "用户名或密码不正确");
        } catch (UserIsLockedException e) {
            return Result.fail(ResultStatus.FAIL, "该账户已被锁定,无法使用,如有疑问可咨询xxxx");
        } catch (UserNotFoundException e) {
            return Result.fail(ResultStatus.FAIL, "该账户不存在");
        }
    }
    @ApiOperation(value = "用户注册", response = Result.class)
    @PostMapping
    @ControllerServiceLog
    public Result register(@RequestBody @Validated RegisterForm form, BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail(ResultStatus.FAIL, result.getAllErrors().get(0).getDefaultMessage());
        }
        int code = ResultStatus.FAIL;
        try {
            code = userService.register(form);
            if (code > 0) {
                String token = userService.login(form.getMail(), form.getPass());
                return Result.success(token);
            } else {
                return Result.fail(code, "未知错误");
            }
        } catch (UserAlreadyRegisterException e) {
            return Result.fail(code, e.getMessage());
        }
    }
    @PutMapping("exit")
    @ControllerServiceLog
    public Result exit(@RequestHeader("Authorization") String token) {
        if (token != null) {
            if (userService.logout(token) > 0) {
                return Result.success(null);
            } else {
                return Result.fail(ResultStatus.FAIL, "服务器繁忙请稍后再试");
            }
        } else {
            return Result.fail(ResultStatus.FAIL, "异常访问");
        }
    }
    @PutMapping("active")
    @ControllerServiceLog
    public Result<String> active() {
        return null;
    }
    @PutMapping
    @ControllerServiceLog
    public Result<String> update(@RequestBody UserUpdateForm form, @RequestAttribute("user")UserEntity user) {
        userService.updateUserDetail(form,user.getUid());
        return null;
    }
    @PostMapping(value = "avatar", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiOperation("个人头像")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header"),
    })
    public Result uploadAvatar(@ApiParam(value = "上传的头像文件", required = true)@RequestParam("avatar")MultipartFile file, @RequestAttribute("uid")Integer uid) throws FileNotFoundException {
        if (file.isEmpty()) {
            return Result.fail(ResultStatus.FAIL, "收到文件为空");
        }
        //todo 判断图片
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        if (!(suffix.equals(".png")||suffix.equals(".jpg")||suffix.equals(".jpeg"))) {
            return Result.fail(ResultStatus.FAIL, "请上传图片格式");
        }
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }
        File upload = new File(path.getAbsolutePath(), "static/images/upload");
        if (!upload.exists()) {
            upload.mkdirs();
        }

        fileName = uid + "-" + TimeUtils.getTimeStamp() + suffix;
        if (userService.updateUserAvatar(uid, fileName)<=0){
            return Result.fail(ResultStatus.FAIL, "系统繁忙,请稍后再试");
        }
        try {
            byte[] bytes = file.getBytes();
            Files.write(new File(upload.getAbsolutePath(), fileName).toPath(), bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.FAIL, "上传失败");
        }
        return Result.success(fileName);
    }
    @GetMapping("currentUser")
    @ApiOperation("获取当前用户个人信息")
    public Result getCurrentUser(@RequestAttribute("user")UserEntity user){
        UserDetailVO userDetailVO = userService.getCurrentUserDetails(user);
        return Result.success(userDetailVO);
    }
    @GetMapping("/logo/token")
    @ApiOperation("logo上传")
    public Result<QiniuUploadToken> getUploadToken(@RequestAttribute("user")UserEntity user, @RequestParam("file_name") String fileName) {
        Result<QiniuUploadToken> result = new Result<>();
        String file = "user_"+user.getUid()+"/"+ UUIDUtil.get8UUID()+fileName.substring(fileName.lastIndexOf('.'));
        String to = UploadUtils.getQiniuUploadToken(file);
        QiniuUploadToken token1 = new QiniuUploadToken();
        token1.setToken(to);
        token1.setKey(file);
        result.setData(token1);
        result.setCode(ResultStatus.SUCCESS);
        return result;
    }
}
