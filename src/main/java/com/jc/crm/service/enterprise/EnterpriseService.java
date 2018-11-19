package com.jc.crm.service.enterprise;

import com.jc.crm.form.enterprise.EnterpriseForm;
import com.jc.crm.model.EnterpriseEntity;

/**
 * @author asuis
 * @version: EnterpriseService.java
 */
public interface EnterpriseService {
    /**
     * @param enterpriseForm 企业实体
     * @return code 1 表示成功 <0表示失败
     * 为管理员绑定 绑定企业
     * */
    int bindEnterpriseForAdmin(EnterpriseForm enterpriseForm);
    /**
     * 更新enterprise
     * */
    int updateEnterpriseForAdmin(EnterpriseEntity enterpriseEntity);
    /**
     * 注销企业
     * @param eid 企业id
     * @return 删除
     * */
    int deleteEnterprise(Integer eid);
}
