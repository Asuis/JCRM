package com.jc.crm.service.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jc.crm.bean.Address;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.form.AddressForm;
import com.jc.crm.form.consumer.ConsumerForm;
import com.jc.crm.mapper.AddressEntityMapper;
import com.jc.crm.mapper.ConsumerMapper;
import com.jc.crm.model.AddressEntity;
import com.jc.crm.model.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


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

    /* 查看 & 查找 权限内的 客户信息列表
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
    @Override
    public String deleteConsumer(Integer cid,Integer uid){
        String flag = "";
        if (consumerMapper.selectByCid(cid) != null){
            consumerMapper.delete(cid);
            flag = "成功";
            return flag;
        }else{
            flag = "用户不存在";
            return flag;
        }
    }

    /*查看单个客户详细信息
    * @param cid 客户id
    * */
    @Override
    public List<Object> selectDetailed(Integer cid, Integer uid){
        List<Object> cList = new ArrayList<>();
        List<Object> aList = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        cList.add(consumerMapper.selectByCid(cid));
        Integer aid = consumerMapper.selectByCid(cid).getAddressId();
        aList.add(addressMapper.selectAddress(cid));
        list.add(aList);
        list.add(cList);
        return list;
    }

    /*查看客户地区分布
    * */
    @Override
    public String selectArea(Integer uid){
        List<Map<String,Object>> list = consumerMapper.selectArea();
        List<HashMap<String,Object>> returnList = new ArrayList<HashMap<String, Object>>();
        int i = 0;
        String [] areaArr = new String[list.size()];
        String [] dataArr = new String[list.size()];
        Map<String,Object> JSONMap = new HashMap<String,Object>();
        for(Map<String,Object> map : list){
            areaArr[i] = map.get("area").toString();
            dataArr[i] = map.get("sumConsumer").toString();
            i++;
        }
        JSONMap.put("area",areaArr);
        JSONMap.put("sumConsumer",dataArr);
        returnList.add((HashMap<String, Object>) JSONMap);
        JSONArray jsonList = JSONArray.parseArray(JSON.toJSONString(returnList));
        // System.out.println(JSON.toJSONString(jsonList));
        return JSON.toJSONString(jsonList);
    }
}
