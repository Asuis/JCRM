package com.jc.crm.mapper;

import java.util.List;
import java.util.Map;

import com.jc.crm.form.consumer.ConsumerForm;
import com.jc.crm.model.Consumer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ConsumerMapper {

    /**
     * 添加竞争对手信息
     * @param consumer 竞争对手表(competitors)实体类
     * @return int类型的变量
     * */
    @Insert("INSERT INTO consumer" +
            "(consumer_name, staff_num, state, ctime, utime) " +
            "VALUES" +
            "(#{consumerName},#{staffNum},#{state},#{ctime},#{utime})")
    int insert(Consumer consumer);

    /**
     * 查询客户列表信息 根据客户名称
     * @param consumerName 客户名称
     * @return Consumer对象
     * */
    @Select("SELECT * FROM consumer " +
            "where consumer_name = #{consumerName}")
    Consumer selectConsumeByName(String consumerName);


    /**
     * 查询客户列表信息 根据用户名称
     * @param keyword 关键字
     * @return Consumer对象
     * */
    List<ConsumerForm> selectByKeyWord(String keyword);

    @Delete("")
    int delete(Integer cid);

    @Update("")
    int updata();
}