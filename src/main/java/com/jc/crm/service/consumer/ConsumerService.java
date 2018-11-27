package com.jc.crm.service.consumer;

import com.github.pagehelper.PageInfo;
import com.jc.crm.form.consumer.ConsumerForm;

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
     * @return PageInfo对象
     * */
    PageInfo<ConsumerForm> selectListByKeyWord(String keyword, Integer uid, Integer pageNum, Integer pageSize);

    String deleteConsumer(Integer cid,Integer uid);

    String updataConsumer(ConsumerForm consumerForm,Integer uid);
}
