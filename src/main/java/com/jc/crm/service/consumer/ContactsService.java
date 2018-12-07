package com.jc.crm.service.consumer;

import com.github.pagehelper.PageInfo;
import com.jc.crm.form.contacts.ContactForm;

import java.util.List;

/**
 * 联系人业务逻辑定义层接口
 * Created by Administrator on 2018/12/4 0004.
 */
public interface ContactsService {

    /**
     * 添加联系人信息
     * @param uid 登录的用户ID
     * @param contactForm 添加客户实体类对象
     * @return String类型的变量flag 初始化值为空 flag的值为成功 表示添加成功 其他值表示添加失败
     * */
    String addContact(ContactForm contactForm, Integer uid);

    /**
     * 查询该用户可见的联系人列表信息 根据登录的用户ID
     * @param uid 登录的用户ID
     * @param keyword 关键字  [关键字为空则显示全部]
     * @return PageInfo对象
     * */
    PageInfo<ContactForm> selectListByKeyWord(String keyword, Integer cid,Integer uid);

    /*删除联系人
    * @param contactId 联系人id
    * */
    String deleteContact(Integer contactId,Integer uid);

    /*更新联系人信息
    * @param consumerForm 更新客户实体类对象
    * */
    String updateContact(ContactForm contactForm,Integer uid);

    /*查询单个联系人详细信息
    * @param contactId 联系人id
    * */
//    List<Object> selectDetailed(Integer contactId, Integer uid);

}
