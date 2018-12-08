package com.jc.crm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.config.logger.ControllerServiceLog;
import com.jc.crm.form.AddressForm;
import com.jc.crm.form.account.AccountListSubmitForm;
import com.jc.crm.form.user.UpdateUserForm;
import com.jc.crm.mapper.AddressEntityMapper;
import com.jc.crm.mapper.ContactMapper;
import com.jc.crm.mapper.UserMapper;
import com.jc.crm.model.AddressEntity;
import com.jc.crm.model.ContactsEntity;
import com.jc.crm.model.UserEntity;
import com.jc.crm.service.department.DepartmentService;
import com.jc.crm.service.user.UserManagerService;
import com.jc.crm.service.user.UserService;
import com.jc.crm.service.user.vo.UserDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author asuis
 */
@Api("用户管理相关接口")
@RestController
@RequestMapping("api/manager/user")
public class UserManagerController {
    private final UserMapper userMapper;
    private final UserService userService;
    private final UserManagerService userManagerService;
    private final ContactMapper contactMapper;
    private final AddressEntityMapper addressMapper;
    private final DepartmentService departmentService;

    @Autowired
    public UserManagerController(UserMapper userMapper, UserService userService, UserManagerService userManagerService, ContactMapper contactMapper, AddressEntityMapper addressMapper, DepartmentService departmentService) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.userManagerService = userManagerService;
        this.contactMapper = contactMapper;
        this.addressMapper = addressMapper;
        this.departmentService = departmentService;
    }

    @PostMapping
    @ControllerServiceLog
    public Result registerUserFromList(@RequestBody @Validated AccountListSubmitForm form, @RequestAttribute("user")UserEntity user) {
        if (userService.registerFormList(form, user.getEid())>0) {
            return Result.success(null);
        }
        return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后重试");
    }
    @PutMapping
    @ControllerServiceLog
    public Result updateUser(@RequestBody UpdateUserForm form) {
        UserEntity user = userMapper.getByUid(form.getUid());
        if (user!=null) {
            if (userMapper.updateSimpleUser(form)>0) {
                if (form.getAddress()!=null) {
                    if (user.getContactId()!=null) {
                        ContactsEntity contacts =  contactMapper.selectByCid(user.getContactId());
                        if (contacts.getAddressId()!=null) {
                            AddressForm addressForm = form.getAddress();
                            if (addressMapper.update(addressForm.toAddress())<0) {
                                return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后重试");
                            }
                        } else {
                            int aid = addressMapper.insert(form.getAddress().toAddress());
                            contacts.setAddressId(aid);
                            contactMapper.update(contacts);
                        }
                    } else {
                        AddressEntity addressEntity = form.getAddress().toAddress();
                        int aid = addressMapper.insert(addressEntity);
                        if (aid<=0) {
                            return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后重试");
                        }
                        ContactsEntity contactsEntity = new ContactsEntity();
                        contactsEntity.setAddressId(addressEntity.getAddressId());
                        int cid = contactMapper.insert(contactsEntity);
                        if (cid<0) {
                            return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后重试");
                        }
                        int isOk = userMapper.updateContactForUid(contactsEntity.getContactId(), user.getUid());
                        if (isOk<=0) {
                            return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后重试");
                        }
                    }
                }
                if (form.getDepartmentId()!=null) {
                    if (departmentService.insertUserDepartment(form.getUid(), form.getDepartmentId())<0) {
                        return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后重试");
                    }
                }
                return Result.success(null);
            }
        }
        return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后重试");
    }
    @DeleteMapping("{uid}")
    @ControllerServiceLog
    public Result delete(@PathVariable("uid") Integer uid, @RequestAttribute("user")UserEntity userEntity) {
        //TODO 权限校验
        if (userMapper.delete(uid)>0) {
            return Result.success(null);
        }
        return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后再试");
    }
    @GetMapping
    @ControllerServiceLog
    public Result get(@RequestAttribute("user")UserEntity user, @RequestParam("pageSize")Integer pageSize, @RequestParam("pageNum")Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserEntity> userEntities = userMapper.getUserList(user.getEid(), user.getUid());
        if (userEntities!=null) {
            PageInfo<UserEntity> userEntityPageInfo = new PageInfo<>(userEntities);
            return Result.success(userEntityPageInfo);
        }
        return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后再试");
    }

    @GetMapping("detail/{uid}")
    @ApiOperation("用户详情")
    @ControllerServiceLog
    public Result getDetails(@RequestAttribute("user") UserEntity user, @PathVariable Integer uid) {
        //todo 权限判断
        UserDetailVO userDetailVO = userManagerService.getDetailsForUser(uid);
        if (userDetailVO!=null) {
            return Result.success(userDetailVO);
        }
        return Result.fail(ResultStatus.FAIL, "系统繁忙，请稍后重试");
    }
}
