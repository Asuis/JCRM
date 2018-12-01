package com.jc.crm.service.consumer;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.form.consumer.ConsumerForm;
import com.jc.crm.mapper.AddressEntityMapper;
import com.jc.crm.mapper.ConsumerMapper;
import com.jc.crm.model.AddressEntity;
import com.jc.crm.model.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @author kkhhdu on 2018/11/26
 */
@Service
public class ConsumerServiceImpl implements ConsumerService{

    @Autowired
    private final ConsumerMapper consumerMapper;
    private final AddressEntityMapper addressMapper;
    public ConsumerServiceImpl(ConsumerMapper consumerMapper, AddressEntityMapper addressMapper) {
        this.consumerMapper = consumerMapper;
        this.addressMapper = addressMapper;
    }


    /*添加客户信息*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addConsumers(ConsumerForm consumerForm, Integer uid) {
        Consumer consumer = new Consumer();
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(consumerForm.getAddress().getCity());
        addressEntity.setCountry(consumerForm.getAddress().getCountry());
        addressEntity.setProvince(consumerForm.getAddress().getProvince());
        addressEntity.setStreet(consumerForm.getAddress().getStreet());
        addressEntity.setZipCode(consumerForm.getAddress().getZipCode());
        addressMapper.insert(addressEntity);
//        addressMapper.insert(consumerForm.getAddress().toAddress());
        String flag = "";

        if (consumerMapper.selectConsumeByName(consumerForm.getConsumerName()) != null){
//            System.out.println("客户信息已存在");
            flag = "已存在";
            return flag;
        }
        if (consumerMapper.selectConsumeByName(consumerForm.getConsumerName()) == null){
            consumerMapper.insert(consumerForm.toConsumer(addressEntity.getAddressId()));
//            System.out.println("客户添加成功");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    /*编辑客户资料*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updataConsumer(ConsumerForm consumerForm,Integer uid){
        String flag = "";
        Consumer consumer = new Consumer();
        int cid = consumerForm.getCid(); // 获取更新的客户id
        consumerMapper.updata(consumerForm.toConsumerUpdata(cid));
        int aid = consumerMapper.selectByCid(cid).getAddressId();
//        int aid = consumer.getAddressId();  // 通过返回的方式获取地址id
        addressMapper.update(consumerForm.getAddress().updateAddress(aid));

        return "成功";
    }

    /* 查看 & 查找 权限内的 客户信息
    * @param keyword 客户名称
    * if(keyword=空) 查全部
    * */
    @Override
    public PageInfo<ConsumerForm> selectListByKeyWord(String keyword, Integer uid, Integer pageNum, Integer pageSize){
//        PageHelper.startPage(pageNum,pageSize);
        List<ConsumerForm> list = consumerMapper.selectAll(keyword);
        PageInfo<ConsumerForm> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /*移除客户*/
    public String deleteConsumer(Integer cid,Integer uid){
        String flag = "";
        System.out.println(cid+"....................");
        if (consumerMapper.selectByCid(cid) != null){
            consumerMapper.delete(cid);
            flag = "成功";
            return flag;
        }else{
            flag = "用户不存在";
            return flag;
        }
    }


}
