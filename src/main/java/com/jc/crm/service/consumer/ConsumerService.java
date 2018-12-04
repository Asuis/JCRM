package com.jc.crm.service.consumer;

import com.github.pagehelper.PageInfo;
import com.jc.crm.form.AddressForm;
import com.jc.crm.form.consumer.ConsumerForm;
import com.jc.crm.mapper.AddressEntityMapper;
import com.jc.crm.model.Consumer;

import java.util.List;

/**
 * 客户管理业务逻辑定义层接口
 * @author kkhud  2018/11/26 0026.
 */
public interface ConsumerService {

    /**
     * 添加客户信息
     * @param uid 登录的用户ID
     * @param consumerForm 添加客户实体类对象
     * @return String类型的变量flag 初始化值为空 flag的值为成功 表示添加成功 其他值表示添加失败
     * */
    String addConsumers(ConsumerForm consumerForm, Integer uid);

    /**
     * 查询该用户可见的客户列表信息 根据登录的用户ID
     * @param uid 登录的用户ID
     * @param pageNum 页面数
     * @param pageSize 每页含有的数据数量
     * @param keyword 关键字  [关键字为空则显示全部]
     * @return PageInfo对象
     * */
    PageInfo<ConsumerForm> selectListByKeyWord(String keyword, Integer uid, Integer pageNum, Integer pageSize);
    /* 潜在客户 区别客户 方便查看 */
    PageInfo<ConsumerForm> selectListByKeyWord2(String keyword, Integer uid, Integer pageNum, Integer pageSize);

    /*删除客户
    * @param cid 客户id
    * */
    String deleteConsumer(Integer cid,Integer uid);

    /*更新客户信息
    * @param consumerForm 更新客户实体类对象
    * */
    String updataConsumer(ConsumerForm consumerForm,Integer uid);

    /*发展潜在客户 -> 正式客户 */
    String updataOfficial(Integer cid,Integer uid);

    /*查询单个客户详细信息
    * @param cid 客户id
    * */
    List<Object> selectDetailed(Integer cid, Integer uid);

     /*查询客户地区分布
    * */
     String selectArea(Integer uid);
}
