package com.jc.crm.service.opportunity;

import com.github.pagehelper.PageInfo;
import com.jc.crm.form.opportunity.*;

/**
 * 商业机会业务逻辑定义层接口
 * @author currysss 2018-11-23
 * */
public interface BusinessOpportunityService {

    /**
     * 添加商业机会信息
     * @param uid 登录的用户ID
     * @param businessOpportunityInsertForm 商业机会添加实体类对象
     * @return String类型的变量flag 初始化值为空 flag的值为成功 表示添加成功 其他值表示添加失败
     * */
    String addBusinessOpportunity(BusinessOpportunityInsertForm businessOpportunityInsertForm, Integer uid);

    /**
     * 机会所有者修改商业机会信息
     * @param uid 登录的用户ID
     * @param businessOpportunityUpdateForm 商业机会修改实体类对象
     * @return String类型的变量flag 初始化值为空 flag的值为成功 表示修改成功 其他值表示修改失败
     * */
    String updateBusinessOpportunity(BusinessOpportunityUpdateForm businessOpportunityUpdateForm,Integer uid);

    /**
     * 机会跟进者修改商业机会信息
     * @param uid 登录的用户ID
     * @param businessOpportunityUpdatePartialForm 商业机会修改实体类对象
     * @return String类型的变量flag 初始化值为空 flag的值为成功 表示修改成功 其他值表示修改失败
     * */
    String updateBusinessOpportunityPartial(BusinessOpportunityUpdatePartialForm businessOpportunityUpdatePartialForm,Integer uid);

    /**
     * 添加商业机会的市场来源信息
     * @param uid 登录的用户ID
     * @param businessOpportunitySourceInsertForm 市场来源添加实体类对象
     * @return String类型的变量flag 初始化值为空 flag的值为成功 表示添加成功 其他值表示添加失败
     * */
    String addBusinessOpportunitySource(BusinessOpportunitySourceInsertForm businessOpportunitySourceInsertForm, Integer uid);

    /**
     * 修改商业机会的市场来源信息
     * @param uid 登录的用户ID
     * @param businessOpportunitySourceUpdateForm 市场来源添加实体类对象
     * @return String类型的变量flag 初始化值为空 flag的值为成功 表示添加成功 其他值表示添加失败
     * */
    String updateBusinessOpportunitySource(BusinessOpportunitySourceUpdateForm businessOpportunitySourceUpdateForm, Integer uid);

    /**
     * 获得商业机会阶段列表
     * @param pageNum 页面数
     * @param pageSize 每页含有的数据数量
     * @return PageInfo对象
     * */
    PageInfo<BusinessOpportunityStageSelectVo> selectStageList(Integer pageNum, Integer pageSize);

    /**
     * 获得商业机会市场来源列表
     * @param pageNum 页面数
     * @param pageSize 每页含有的数据数量
     * @return PageInfo对象
     * */
    PageInfo<BusinessOpportunitySourceSelectVo> selectSourceList(Integer pageNum, Integer pageSize);

    /**
     * 获得商业机会丢失原因列表
     * @param pageNum 页面数
     * @param pageSize 每页含有的数据数量
     * @return PageInfo对象
     * */
    PageInfo<BusinessOpportunityLossReasonSelectVo> selectLossReasonList(Integer pageNum, Integer pageSize);

    /**
     * 获得商业机会市场来源状态列表
     * @param pageNum 页面数
     * @param pageSize 每页含有的数据数量
     * @return PageInfo对象
     * */
    PageInfo<BusinessOpportunitySourceStatusSelectVo> selectSourceStatusList(Integer pageNum, Integer pageSize);

    /**
     * 获得商业机会市场来源类别列表
     * @param pageNum 页面数
     * @param pageSize 每页含有的数据数量
     * @return PageInfo对象
     * */
    PageInfo<BusinessOpportunitySourceTypeSelectVo> selectSourceTypeList(Integer pageNum, Integer pageSize);
}
