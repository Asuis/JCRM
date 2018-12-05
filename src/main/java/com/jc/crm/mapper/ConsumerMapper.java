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
            "(pid,consumer_name,website,description,industry,address,contact_id,ctime,utime,state,staff_num,is_official) " +
            "VALUES" +
            "(#{pid},#{consumerName},#{website},#{description},#{industry},#{address}," +
            "#{contactId},#{ctime},#{utime},#{state},#{staffNum},#{isOfficial})")
    @Options(useGeneratedKeys = true, keyProperty = "cid", keyColumn = "cid")
    int insert(Consumer consumer);

    /**
     * 修改客户信息
     * @param consumer 客户实体类
     * @return int类型的变量
     * */
    @Update("update consumer set pid=#{pid},consumer_name=#{consumerName},website=#{website},description=#{description}," +
            "industry=#{industry},utime=#{utime},state=#{state},staff_num=#{staffNum},is_official=#{isOfficial} " +
            "where cid=#{cid}")
    @Options(useGeneratedKeys = true, keyProperty = "address", keyColumn = "address")
    int updata(Consumer consumer);

    /**
     * 发展潜在客户
     * @param cid 潜在客户id
     * */
    @Update("update consumer set is_official= 1 " +
            "where cid=#{cid}")
    @Options(useGeneratedKeys = true, keyProperty = "address", keyColumn = "address")
    int updataOfficial(Integer cid);

    /**
     * 查询客户列表信息 根据客户名称
     * @param consumerName 客户名称
     * @return Consumer对象
     * */
    @Select("SELECT * FROM consumer " +
            "where consumer_name = #{consumerName} AND state >= 0")
    Consumer selectConsumeByName(String consumerName);

    /**
     * 查询客户信息 根据用户名称
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
     * is_official = 1 代表正式客户
     * state >= 0 表示 客户存在 <0
     * @return Consumer对象
     * */
    @Select("<script>" +
            "SELECT * FROM consumer " +
            "WHERE is_official = 1 AND state >= 0\n " +
            "    <if test=\"keyword != ''\">\n" +
            "    AND consumer_name LIKE \"%\"#{keyword}\"%\"\n" +
            "    </if>\n" +
            "    GROUP BY consumer_name " +
            "</script>")
    List<ConsumerForm> selectOfficial(@Param(value="keyword")String keyword);

    /**
     * 查询潜在客户列表信息列表
     *  @param keyword 潜在信息（暂定名称-可优化）
     * if(keyword=空) 查全部
     * is_official = 1 代表潜在客户
     * state >= 0 表示 客户存在 <0
     * @return Consumer对象
     * */
    @Select("<script>" +
            "SELECT * FROM consumer " +
            "WHERE is_official = 0 AND state >= 0\n " +
            "    <if test=\"keyword != ''\">\n" +
            "    AND consumer_name LIKE \"%\"#{keyword}\"%\"\n" +
            "    </if>\n" +
            "    GROUP BY consumer_name " +
            "</script>")
    List<ConsumerForm> selectNofficial(@Param(value="keyword")String keyword);

    /**
     * 查询客户详细信息列表
     *  @param cid 客户id
     * if(keyword=空) 查全部
     * @return Consumer对象
     * */
    @Select("SELECT * FROM consumer WHERE cid = #{cid} LIMIT 1")
    List<Map<String, Object>> selectDetailed(@Param(value="cid")Integer cid);

    @Delete("update consumer set state=-1 " +
            "where cid=#{cid}")
    int delete(Integer cid);

    /**
     * 查询客户地区分布
     * @return Consumer对象
     * */
    @Select("SELECT \n" +
            "COUNT(*) AS 'sumConsumer',\n" +
            "province AS 'area'\n" +
            "FROM\n" +
            "address ad\n" +
            "LEFT OUTER JOIN consumer AS co\n" +
            "ON ad.address_id = co.address\n" +
            "WHERE \n" +
            "state >= 0\n" +
            "GROUP BY province")
    List<Map<String,Object>> selectArea();

}