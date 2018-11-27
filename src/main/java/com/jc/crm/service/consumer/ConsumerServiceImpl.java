package com.jc.crm.service.consumer;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jc.crm.form.AddressForm;
import com.jc.crm.form.consumer.ConsumerForm;
import com.jc.crm.mapper.ConsumerMapper;
import com.jc.crm.model.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author kkhhdu on 2018/11/26
 */
@Service
public class ConsumerServiceImpl implements ConsumerService{

    @Autowired
    private ConsumerMapper consumerMapper;


    /*添加客户信息*/
    @Override
    public String addConsumers(ConsumerForm consumerForm, Integer uid) {
        Consumer consumer = new Consumer();
        consumer.setConsumerName(consumerForm.getConsumerName());
//        consumer.setAddress(consumerForm.getAddress());
        consumer.setStaffNum(consumerForm.getStaffNum());
        System.out.println("开始添加");

        Date day = new Date();
        String flag = "";

        if (consumerMapper.selectConsumeByName(consumerForm.getConsumerName()) != null){
            System.out.println("客户信息已存在");
            flag = "已存在";
            return flag;
        }
        if (consumerMapper.selectConsumeByName(consumerForm.getConsumerName()) == null){
            consumer.setState(0);
            consumer.setUtime(day);
            consumer.setCtime(day);

            consumerMapper.insert(consumer);
            System.out.println("客户添加成功");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    /*查看 权限内的 客户信息*/
    public PageInfo<ConsumerForm> selectListByKeyWord(String keyword, Integer uid, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ConsumerForm> list = consumerMapper.selectByKeyWord(keyword);
        PageInfo<ConsumerForm> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /*移除客户*/
    public String deleteConsumer(Integer cid,Integer uid){
        return null;
    }

    /*编辑客户资料*/
    public String updataConsumer(ConsumerForm consumerForm,Integer uid){
        return null;
    }
}
