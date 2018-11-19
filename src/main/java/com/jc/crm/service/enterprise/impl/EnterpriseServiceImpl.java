package com.jc.crm.service.enterprise.impl;

import com.jc.crm.dao.AddressMapper;
import com.jc.crm.form.enterprise.EnterpriseForm;
import com.jc.crm.mapper.EnterpriseMapper;
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
    private final AddressMapper addressMapper;
    @Autowired
    public EnterpriseServiceImpl(EnterpriseMapper enterpriseMapper, AddressMapper addressMapper) {
        this.enterpriseMapper = enterpriseMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public int bindEnterpriseForAdmin(EnterpriseForm enterpriseForm) {

        return 0;
    }

    @Override
    public int updateEnterpriseForAdmin(EnterpriseEntity enterpriseEntity) {
        return 0;
    }

    @Override
    public int deleteEnterprise(Integer eid) {
        return 0;
    }
}
