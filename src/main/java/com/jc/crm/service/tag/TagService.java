package com.jc.crm.service.tag;

import com.github.pagehelper.PageInfo;
import com.jc.crm.form.tag.TagInsertForm;
import com.jc.crm.model.TagEntity;

/**
 * 标签业务逻辑定义层接口
 * @author currysss 2018-11-18
 * */
public interface TagService {

    /**
     * 传入tagInsertForm对象，自定义标签信息
     * @param tagInsertForm 标签添加实体类对象
     * @return String类型的变量flag 初始化值为空 flag的值为成功 表示添加成功 其他值表示添加失败
     * */
    String addTag(TagInsertForm tagInsertForm);

    /**
     * 获得标签列表
     * @param pageNum 页面数
     * @param pageSize 每页含有的数据数量
     * @return PageInfo对象
     * */
    PageInfo<TagEntity> selectTagList(Integer pageNum, Integer pageSize);


}
