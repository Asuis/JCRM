package com.jc.crm.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jc.crm.auth.JwtUtils;
import com.jc.crm.config.exception.SystemException;
import com.jc.crm.config.logger.SystemServiceLog;
import com.jc.crm.form.account.RegisterForm;
import com.jc.crm.form.account.UserUpdateForm;
import com.jc.crm.mapper.ContactMapper;
import com.jc.crm.mapper.DepartmentMapper;
import com.jc.crm.mapper.EnterpriseMapper;
import com.jc.crm.mapper.UserMapper;
import com.jc.crm.model.TagEntity;
import com.jc.crm.model.UserEntity;
import com.jc.crm.service.department.vo.UserDepartmentVO;
import com.jc.crm.service.user.*;
import com.jc.crm.service.user.constant.UserRole;
import com.jc.crm.service.user.exception.UserAlreadyRegisterException;
import com.jc.crm.service.user.exception.UserIsLockedException;
import com.jc.crm.service.user.exception.UserNotFoundException;
import com.jc.crm.service.user.exception.UserNotRightPassException;
import com.jc.crm.service.user.vo.GeographicVO;
import com.jc.crm.service.user.vo.UserDetailVO;
import com.jc.crm.utils.Base64Utils;
import com.jc.crm.utils.MD5Utils;
import com.jc.crm.utils.TimeUtils;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserMapper userMapper;

    private final EnterpriseMapper enterpriseMapper;
    private final ContactMapper contactMapper;

    private final DepartmentMapper departmentMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper, EnterpriseMapper enterpriseMapper, ContactMapper contactMapper, DepartmentMapper departmentMapper) {
        this.userMapper = userMapper;
        this.enterpriseMapper = enterpriseMapper;
        this.contactMapper = contactMapper;
        this.departmentMapper = departmentMapper;
    }

    @Override
    @SystemServiceLog(description = "return uid ")
    public int register(RegisterForm registerForm) throws UserAlreadyRegisterException {
        UserEntity user = UserFactory.create(registerForm);

        //判断账号是否已经注册
        if (userMapper.getByEmail(registerForm.getMail()) != null) {
            throw  new UserAlreadyRegisterException("账号已经注册");
        }

        if (userMapper.insert(user)!=1) {
            throw new SystemException("系统繁忙请稍后再试");
        }
        userMapper.insertRoleForUser(user.getUid(), UserRole.USER);
        logger.info("生成User:" + user.toString());
        return user.getUid();
    }
    @Override
    @SystemServiceLog(description = "return jwt:")
    public String login(String account, String pass) throws UserNotFoundException, UserNotRightPassException, UserIsLockedException {
        UserEntity userEntity = userMapper.getByEmail(account);
        //用户校验
        if (userEntity == null) {
            throw new UserNotFoundException("用户名不存在");
        }
        if (!userEntity.getPass().equals(MD5Utils.encode(pass))) {
            throw new UserNotRightPassException("密码不正确");
        }
        if (userEntity.getIsLock()==1) {
            throw new UserIsLockedException("用户被锁定");
        }
        List<String> roles = userMapper.getRoles(userEntity.getUid());
        //jwt 签名
        String token = JwtUtils.addAuthentication(userEntity, roles);

        //更新user 登录信息
        userMapper.updateLoginTime(TimeUtils.getNowTime() ,userEntity.getUid());
        return token;
    }

    @Override
    @SystemServiceLog(description = "return code:")
    public int logout(String token) {
        //通过修改salt使token过期
        String msg = token.split("\\.")[1];
        String data = Base64Utils.decode(msg);
        JSONObject jsonObject = JSON.parseObject(data);
        if (!jsonObject.containsKey("sub")) {
            throw new RuntimeException("token格式不正确");
        }
        String account = (String) jsonObject.get("sub");
        UserEntity userEntity = userMapper.getByEmail(account);
        if (!JwtUtils.compare(token, userEntity.getSalt())) {
            throw new RuntimeException("token 已过期");
        }
        //重新生成salt
        userEntity.setSalt(new String(
                Keys.hmacShaKeyFor((userEntity.getPass()+ new Random().nextLong()).getBytes()).getEncoded(), Charset.defaultCharset()));
        return userMapper.updateSalt(userEntity.getSalt(), userEntity.getUid());
    }

    @Override
    public int updateUserDetail(UserUpdateForm updateForm, int uid) {

        return -1;
    }

    @Override
    public int active(int uid, String verifyCode) {
        return userMapper.setLock(0, uid);
    }

    @Override
    public List<String> getRoles(Integer uid) {

        return null;
    }
    /**
     * {
     *     name: 'Serati Ma',
     *     avatar: 'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',
     *     userid: '00000001',
     *     email: 'antdesign@alipay.com',
     *     signature: '海纳百川，有容乃大',
     *     title: '交互专家',
     *     group: '蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED',
     *     tags: [
     *       {
     *         key: '0',
     *         label: '很有想法的',
     *       },
     *       {
     *         key: '1',
     *         label: '专注设计',
     *       },
     *       {
     *         key: '2',
     *         label: '辣~',
     *       },
     *       {
     *         key: '3',
     *         label: '大长腿',
     *       },
     *       {
     *         key: '4',
     *         label: '川妹子',
     *       },
     *       {
     *         key: '5',
     *         label: '海纳百川',
     *       },
     *     ],
     *     notifyCount: 12,
     *     unreadCount: 11,
     *     country: 'China',
     *     geographic: {
     *       province: {
     *         label: '浙江省',
     *         key: '330000',
     *       },
     *       city: {
     *         label: '杭州市',
     *         key: '330100',
     *       },
     *     },
     *     address: '西湖区工专路 77 号',
     *     phone: '0752-268888888',
     *   }
     * */
    @Override
    public UserDetailVO getCurrentUserDetails(UserEntity user) {
        UserDetailVO userDetailVO = new UserDetailVO();
        userDetailVO.setAvatar(user.getAvatar());
        userDetailVO.setEmail(user.getEmail());
        userDetailVO.setName(user.getUsername());

        userDetailVO.setUserid(String.valueOf(user.getUid()));
        UserDepartmentVO departmentVO = departmentMapper.getDepartmentDetailByUser(user.getUid());
        if (departmentVO!=null) {
            userDetailVO.setGroup(departmentVO.getDepartmentName());
            userDetailVO.setTitle(departmentVO.getPost());
        }

        List<TagEntity> tags = userMapper.queryUserTags(user.getUid());
        userDetailVO.setTags(tags);

        //获取通知信息 todo
        userDetailVO.setNotifyCount(0);
        userDetailVO.setUnreadCount(0);

        //获取address
        userDetailVO.setGeographic(new GeographicVO("浙江省","杭州市"));
        return userDetailVO;
    }
}
