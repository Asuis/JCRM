package com.jc.crm.service.consumer.impl;

import com.github.pagehelper.PageInfo;
import com.jc.crm.form.consumer.ConsumerForm;
import com.jc.crm.form.contacts.ContactForm;
import com.jc.crm.mapper.AddressEntityMapper;
import com.jc.crm.mapper.ConsumerMapper;
import com.jc.crm.mapper.ContactMapper;
import com.jc.crm.model.AddressEntity;
import com.jc.crm.model.Consumer;
import com.jc.crm.service.consumer.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/12/4 0004.
 */
@Service
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private final ContactMapper contactMapper;
    private final ConsumerMapper consumerMapper;
    private final AddressEntityMapper addressMapper;
    public ContactsServiceImpl(ContactMapper contactMapper,ConsumerMapper consumerMapper, AddressEntityMapper addressMapper) {
        this.contactMapper = contactMapper;
        this.consumerMapper = consumerMapper;
        this.addressMapper = addressMapper;
    }

    /*添加 联系人 信息
    * contactForm 包含了公司（客户）cid
    * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addContact(ContactForm contactForm, Integer uid) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(contactForm.getAddress().getCity());
        addressEntity.setCountry(contactForm.getAddress().getCountry());
        addressEntity.setProvince(contactForm.getAddress().getProvince());
        addressEntity.setStreet(contactForm.getAddress().getStreet());
        addressEntity.setZipCode(contactForm.getAddress().getZipCode());
        addressMapper.insert(addressEntity);

        System.out.println(addressEntity.getAddressId()+"...................");
        String flag = "";

        if (contactMapper.selectContactsName(contactForm.getContactName()) != null){
//            System.out.println("客户信息已存在");
            flag = "已存在";
            return flag;
        }
        if (contactMapper.selectContactsName(contactForm.getContactName()) == null){
            contactMapper.insert(contactForm.toContact(addressEntity.getAddressId()));
//            System.out.println("联系人添加成功");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    /*编辑联系人资料*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateContact(ContactForm contactForm,Integer uid){
        String flag = "";
        int contactId = contactForm.getContactId(); // 获取更新的联系人id
        contactMapper.update(contactForm.toContactUpdata(contactId));
        int aid = contactMapper.selectByCid(contactId).getAddressId();
        // System.out.println(aid+"................");
        addressMapper.update(contactForm.getAddress().updateAddress(aid));

        return "成功";
    }

    /* 查看 & 查找 权限内的 联系人信息列表
    * @param keyword 联系人名称
    * if(keyword=空) 查全部
    * */
    @Override
    public PageInfo<ContactForm> selectListByKeyWord(String keyword, Integer cid,Integer uid){
        System.out.println(cid+"..................");
        List<ContactForm> list = contactMapper.selectBelon(keyword,cid);
        PageInfo<ContactForm> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
