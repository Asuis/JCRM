package com.jc.crm.mapper;

import com.jc.crm.form.contacts.ContactForm;
import com.jc.crm.model.Consumer;
import com.jc.crm.model.ContactsEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author asuis
 */
@Mapper
@Repository
public interface ContactMapper {


    /**
     * 添加联系人信息
     * @param contactsEntity 联系人实体类
     * @return int类型的变量
     * */
    @Insert("INSERT INTO contacts" +
            "(contact_name,phone_number,email,firstname,title,pid,post,address_id,department,holder,consumer_id) " +
            "VALUES" +
            "(#{contactName},#{phoneNumber},#{email},#{firstName},#{title},#{pid},#{post},#{addressId}," +
            "#{department},#{holder},#{consumerId})")
    @Options(useGeneratedKeys = true, keyProperty = "contact_id", keyColumn = "contact_id")
    int insert(ContactsEntity contactsEntity);

    /**
     * 修改联系人信息
     * @param contactsEntity 联系人实体类
     * @return int类型的变量
     * */
    @Update("update contacts set contact_name=#{contactName},phone_number=#{phoneNumber},email=#{email},firstname=#{firstName}," +
            "title=#{title},pid=#{pid},post=#{post},address_id=#{addressId},department=#{department},holder=#{holder},consumer_id=#{consumerId} " +
            "where contact_id=#{contactId}")
    // @Options(useGeneratedKeys = true, keyProperty = "address", keyColumn = "address")
    int update(ContactsEntity contactsEntity);


    /**
     * 查询客户列表信息 根据客户名称
     * @param contactsName 客户名称
     * @return Consumer对象
     * */
    @Select("SELECT * FROM contacts " +
            "where contact_name = #{contactsName}")
    ContactsEntity selectContactsName(String contactsName);

    /**
     * 查询客户列表信息 根据客户名称
     * @param contactId 客户名称
     * @return Consumer对象
     * */
    @Select("SELECT * FROM contacts " +
            "where contact_id = #{contactId}")
    ContactsEntity selectByCid(int contactId);

    /**
     * 查询客户列表信息列表
     *  @param keyword 客户名称
     * if(keyword=空) 查全部
     * @return Consumer对象
     * */
    @Select("<script>" +
            "SELECT * FROM contacts " +
            "WHERE consumer_id = #{cid}\n " +
            "    <if test=\"keyword != ''\">\n" +
            "    AND consumer_name LIKE \"%\"#{keyword}\"%\"\n" +
            "    </if>\n" +
            "    GROUP BY contact_id " +
            "</script>")
    List<ContactForm> selectBelon(@Param(value="keyword")String keyword,@Param(value="cid")Integer cid);

    /*
    * 删除 联系人
    * @param contactId 联系人id
    * 这里以改变状态 代替删除
    * holder 作为联系人状态的变化 存在 1 / 删除 -1
     */
    @Delete("update contacts set holder = -1 " +
            "where consumer_id = #{contactId}")
    int deleteById(Integer contactId);
}
