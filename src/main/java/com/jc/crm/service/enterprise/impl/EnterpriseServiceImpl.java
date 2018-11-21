package com.jc.crm.service.enterprise.impl;

import com.jc.crm.config.ResultStatus;
import com.jc.crm.form.enterprise.EnterpriseForm;
import com.jc.crm.mapper.AddressEntityMapper;
import com.jc.crm.mapper.EnterpriseMapper;
import com.jc.crm.mapper.UserMapper;
import com.jc.crm.model.EnterpriseEntity;
import com.jc.crm.service.enterprise.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asuis
 * @version: EnterpriseServiceImpl.java 18-11-19:下午7:30
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    private final EnterpriseMapper enterpriseMapper;
    private final AddressEntityMapper addressMapper;
    private final UserMapper userMapper;
    @Autowired
    public EnterpriseServiceImpl(EnterpriseMapper enterpriseMapper, AddressEntityMapper addressMapper, UserMapper userMapper) {
        this.enterpriseMapper = enterpriseMapper;
        this.addressMapper = addressMapper;
        this.userMapper = userMapper;
    }

    @Override
    public int bindEnterpriseForAdmin(EnterpriseForm enterpriseForm, Integer uid) {
        Integer addressId = null;
        int result;
        if (enterpriseForm.getAddress()!=null) {
            result = addressMapper.insert(enterpriseForm.getAddress().toAddress());
            if (result> 0) {
                addressId = result;
            }
        }
        result = enterpriseMapper.insert(enterpriseForm.toEnterpriseEntity(addressId));
        if (result > 0) {
            if (userMapper.bindEnterprise(result, uid)>0) {
                return result;
            }
        }
        return ResultStatus.FAIL;
    }

    @Override
    public int updateEnterpriseForAdmin(EnterpriseEntity enterpriseEntity) {
        int result = enterpriseMapper.update(enterpriseEntity);
        if (result > 0) {
            return result;
        }
        return ResultStatus.FAIL;
    }

    @Override
    public int deleteEnterprise(Integer eid) {
        return 0;
    }

    @Override
    public int isExist(String enterpriseName) {
        return enterpriseMapper.isExistByName(enterpriseName);
    }
}
