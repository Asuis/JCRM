package com.jc.crm.service.tag;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jc.crm.form.tag.TagInsertForm;
import com.jc.crm.mapper.TagMapper;
import com.jc.crm.model.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签业务逻辑实现层接口
 * @author currysss 2018-11-18
 * */
@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagMapper tagMapper;

    @Override
    public String addTag(TagInsertForm tagInsertForm){

        TagEntity tagEntity = new TagEntity();
        tagEntity.setTagName(tagInsertForm.getTagName());
        tagEntity.setDescription(tagInsertForm.getDescription());
        tagEntity.setPid(tagInsertForm.getPid());
        System.out.println("开始添加标签信息");

        String flag = "";

        if(tagMapper.selectByTagName(tagEntity.getTagName()) != null ){
            System.out.println("该标签名已存在");
            flag = "已存在";
            return flag;
        }
        if(tagMapper.selectByTagName(tagEntity.getTagName()) == null ){
            tagMapper.insert(tagEntity);
            System.out.println("标签添加成功");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    public PageInfo<TagEntity> selectTagList(Integer pageNum, Integer pageSize){
        System.out.println("获得标签信息列表");
        PageHelper.startPage(pageNum,pageSize);
        List<TagEntity> list = tagMapper.getTagList();
        PageInfo<TagEntity> pageInfo = new PageInfo<>(list);
        System.out.println("查找成功");
        return pageInfo;
    }




}
