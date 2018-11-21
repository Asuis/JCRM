package com.jc.crm.service.competitors;

import com.github.pagehelper.PageInfo;
import com.jc.crm.form.competitors.CompetitorsDeleteForm;
import com.jc.crm.form.competitors.CompetitorsInsertForm;
import com.jc.crm.form.competitors.CompetitorsSelectVo;
import com.jc.crm.form.competitors.CompetitorsUpdateForm;
import com.jc.crm.form.tag.CompetitorsTagLinkDeleteForm;
import com.jc.crm.form.tag.CompetitorsTagLinkInsertForm;

/**
 * 竞争对手业务逻辑定义层接口
 * @author currysss 2018-11-16
 * */
public interface CompetitorsService {

    /**
     * 添加竞争对手信息
     * @param uid 登录的用户ID
     * @param competitorsInsertForm 竞争对手添加实体类对象
     * @return String类型的变量flag 初始化值为空 flag的值为成功 表示添加成功 其他值表示添加失败
     * */
    String addCompetitors(CompetitorsInsertForm competitorsInsertForm, Integer uid);

    /**
     * 修改竞争对手信息
     * @param uid 登录的用户ID
     * @param competitorsUpdateForm 竞争对手修改实体类对象
     * @return String类型的变量flag 初始化值为空 flag的值为成功 表示修改成功 其他值表示修改失败
     * */
    String updateCompetitors(CompetitorsUpdateForm competitorsUpdateForm,Integer uid);

    /**
     * 删除竞争对手信息(修改竞争对手状态信息)
     * @param competitorsDeleteForm 竞争对手删除实体类对象(修改竞争对手状态 0为未删除 1为已删除)
     * @param uid 登录的用户ID
     * @return String类型的变量flag 初始化值为空 flag的值为成功 表示删除成功 其他值表示删除失败
     * */
    String deleteCompetitors(CompetitorsDeleteForm competitorsDeleteForm,Integer uid);

    /**
     * 为竞争对手贴上标签
     * @param uid 登录的用户ID
     * @param competitorsTagLinkInsertForm 竞争对手标签添加的实体类对象
     * @return String类型的变量flag 初始化值为空 flag的值为成功 表示添加成功 其他值表示添加失败
     * */
    String addCompetitorsTag(CompetitorsTagLinkInsertForm competitorsTagLinkInsertForm, Integer uid);

    /**
     * 为竞争对手移除标签
     * @param uid 登录的用户ID
     * @param competitorsTagLinkDeleteForm 竞争对手标签添加的实体类对象
     * @return String类型的变量flag 初始化值为空 flag的值为成功 表示移除成功 其他值表示移除失败
     * */
    String deleteCompetitorsTag(CompetitorsTagLinkDeleteForm competitorsTagLinkDeleteForm, Integer uid);

    /**
     * 根据关键字和登录的用户ID多表关联动态模糊查询竞争对手列表信息
     * @param keyword 关键字
     * @param uid 登录的用户ID
     * @param pageNum 页面数
     * @param pageSize 每页含有的数据数量
     * @return PageInfo对象
     * */
    PageInfo<CompetitorsSelectVo> selectListByKeyWord(String keyword, Integer uid, Integer pageNum, Integer pageSize);

    /**
     * 根据登录的用户ID查询该用户编辑的竞争对手列表信息
     * @param uid 登录的用户ID
     * @param pageNum 页面数
     * @param pageSize 每页含有的数据数量
     * @return PageInfo对象
     * */
    PageInfo<CompetitorsSelectVo> selectListByHolder(Integer uid, Integer pageNum, Integer pageSize);

}
