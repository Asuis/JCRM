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
     * 添加客户信息
     * @param consumer 客户实体类
     * @return int类型的变量
     * */
    @Insert("INSERT INTO consumer" +
            "(pid,consumer_name,website,description,industry,address,contact_id,ctime,utime,state,staff_num) " +
            "VALUES" +
            "(#{pid},#{consumerName},#{website},#{description},#{industry},#{address}," +
            "#{contactId},#{ctime},#{utime},#{state},#{staffNum})")
    @Options(useGeneratedKeys = true, keyProperty = "cid", keyColumn = "cid")
    int insert(Consumer consumer);

    /**
     * 修改客户信息
     * @param consumer 客户实体类
     * @return int类型的变量
     * */
    @Update("update consumer set pid=#{pid},consumer_name=#{consumerName},website=#{website},description=#{description}," +
            "industry=#{industry},utime=#{utime},state=#{state},staff_num=#{staffNum} " +
            "where cid=#{cid}")
    @Options(useGeneratedKeys = true, keyProperty = "address", keyColumn = "address")
    int updata(Consumer consumer);

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
     * @param cid 关键字
     * @return Consumer对象
     * */
    @Select("SELECT * FROM consumer " +
            "where cid = #{cid}")
    Consumer selectByCid(int cid);

    /**
     * 查询客户列表信息列表
     *  @param keyword 客户名称
     * if(keyword=空) 查全部
     * @return Consumer对象
     * */
    @Select("<script>" +
            "SELECT * FROM consumer\n" +
            "    <if test=\"keyword != ''\">\n" +
            "    WHERE consumer_name LIKE \"%\"#{keyword}\"%\"\n" +
            "    </if>\n" +
            "    GROUP BY consumer_name " +
            "</script>")
    List<ConsumerForm> selectAll(@Param(value="keyword")String keyword);

    @Delete("update consumer set state=-1 " +
            "where cid=#{cid}")
    int delete(Integer cid);


}