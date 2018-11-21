package com.jc.crm.service.competitors;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.jc.crm.form.competitors.CompetitorsDeleteForm;
import com.jc.crm.form.competitors.CompetitorsInsertForm;
import com.jc.crm.form.competitors.CompetitorsSelectVo;
import com.jc.crm.form.competitors.CompetitorsUpdateForm;
import com.jc.crm.form.tag.CompetitorsTagLinkDeleteForm;
import com.jc.crm.form.tag.CompetitorsTagLinkInsertForm;
import com.jc.crm.form.tag.TagVo;

import com.jc.crm.mapper.TagMapper;
import com.jc.crm.mapper.CompetitorMapper;

import com.jc.crm.model.CompetitorsEntity;
import com.jc.crm.model.CompetitorsTagLinkEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 竞争对手业务逻辑实现层接口
 * @author currysss 2018-11-17
 * */
@Service
public class CompetitorsServiceImpl implements CompetitorsService{

    @Autowired
    private CompetitorMapper competitorsMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public String addCompetitors(CompetitorsInsertForm competitorsInsertForm, Integer uid){

        CompetitorsEntity competitorsEntity = new CompetitorsEntity();
        competitorsEntity.setCompetitorName(competitorsInsertForm.getCompetitorName());
        competitorsEntity.setDescription(competitorsInsertForm.getDescription());
        competitorsEntity.setEx1(competitorsInsertForm.getEx1());
        competitorsEntity.setTypes(competitorsInsertForm.getTypes());
        System.out.println("开始添加竞争对手");
        Date day = new Date();
        String flag = "";

        if(competitorsMapper.selectByCompetitorsName(competitorsInsertForm.getCompetitorName()) != null){
            System.out.println("信息已存在");
            flag = "已存在";
            return flag;
        }
        if(competitorsMapper.selectByCompetitorsName(competitorsInsertForm.getCompetitorName()) == null){
            competitorsEntity.setCtime(day);
            competitorsEntity.setUtime(day);
            competitorsEntity.setHolder(uid);
            competitorsEntity.setStatus(0);

            competitorsMapper.insert(competitorsEntity);
            System.out.println("成功添加");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    public String updateCompetitors(CompetitorsUpdateForm competitorsUpdateForm,Integer uid){
        Date day = new Date();
        CompetitorsEntity competitorsEntity = new CompetitorsEntity();
        competitorsEntity.setCompetitorName(competitorsUpdateForm.getCompetitorName());
        competitorsEntity.setDescription(competitorsUpdateForm.getDescription());
        competitorsEntity.setEx1(competitorsUpdateForm.getEx1());
        competitorsEntity.setTypes(competitorsUpdateForm.getTypes());
        competitorsEntity.setCompetitorId(competitorsUpdateForm.getCompetitorId());
        competitorsEntity.setHolder(competitorsMapper.selectByPrimaryKey(competitorsEntity.getCompetitorId()).getHolder());

        System.out.println("开始修改竞争对手信息,修改的对手id为"+competitorsEntity.getCompetitorId()+",该条信息的编辑者为"+
                competitorsEntity.getHolder());
        String flag = "";

        if(competitorsMapper.selectByPrimaryKey(competitorsEntity.getCompetitorId()) == null){
            System.out.println("错误的Id,该竞争对手不存在");
            flag = "不存在";
            return flag;
        }
        if(competitorsEntity.getHolder() != uid){
            System.out.println("您不是该条信息的编辑者，没有权限修改该条信息");
            flag = "权限不足";
            return flag;
        }
        if(competitorsMapper.selectByCompetitorsName(competitorsEntity.getCompetitorName()) != null &&
                competitorsMapper.selectByCompetitorsName(competitorsEntity.getCompetitorName()).getCompetitorId()
                        != competitorsEntity.getCompetitorId()){
            System.out.println("冲突，该公司已存在");
            flag = "已存在";
            return flag;
        }
        if(competitorsMapper.selectByPrimaryKey(competitorsEntity.getCompetitorId()) != null){
            competitorsEntity.setUtime(day);
            competitorsMapper.update(competitorsEntity);
            System.out.println("成功修改");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    public String deleteCompetitors(CompetitorsDeleteForm competitorsDeleteForm,Integer uid){

        CompetitorsEntity competitorsEntity = new CompetitorsEntity();
        competitorsEntity.setStatus(competitorsDeleteForm.getStatus());
        competitorsEntity.setCompetitorId(competitorsDeleteForm.getCompetitorId());

        System.out.println("开始删除竞争对手");
        Date day = new Date();
        String flag = "";

        if(competitorsMapper.selectByPrimaryKey(competitorsEntity.getCompetitorId()) == null){
            System.out.println("错误的Id,该竞争对手不存在");
            flag = "不存在";
            return flag;
        }
        if(competitorsMapper.selectByPrimaryKey(competitorsEntity.getCompetitorId()).getHolder() != uid){
            System.out.println("您不是该条信息的编辑者，没有权限修改该条信息状态");
            flag = "权限不足";
            return flag;
        }
        if(competitorsEntity.getStatus() !=0 && competitorsEntity.getStatus() != 1){
            System.out.println("传递的数据格式出错，只能为0或1"+competitorsEntity.getStatus());
            flag = "错误数据格式";
            return flag;
        }
        if(competitorsMapper.selectByPrimaryKey(competitorsEntity.getCompetitorId()) != null){
            competitorsEntity.setUtime(day);
            competitorsEntity.setHolder(competitorsMapper.selectByPrimaryKey(competitorsEntity.getCompetitorId()).getHolder());
            competitorsMapper.updateStatus(competitorsEntity);
            System.out.println("成功修改该竞争对手状态为已删除");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    public String addCompetitorsTag(CompetitorsTagLinkInsertForm competitorsTagLinkInsertForm, Integer uid){
        CompetitorsTagLinkEntity competitorsTagLinkEntity = new CompetitorsTagLinkEntity();
        competitorsTagLinkEntity.setTagId(competitorsTagLinkInsertForm.getTagId());
        competitorsTagLinkEntity.setCompetitorId(competitorsTagLinkInsertForm.getCompetitorId());

        System.out.println("开始为竞争对手贴上标签信息");
        Date day = new Date();
        String flag = "";


        if(tagMapper.selectByTagId(competitorsTagLinkEntity.getTagId()) == null ){
            System.out.println("该标签Id不存在");
            flag = "不存在";
            return flag;
        }
        if(competitorsMapper.selectByPrimaryKey(competitorsTagLinkEntity.getCompetitorId()) == null ){
            System.out.println("该竞争对手Id不存在");
            flag = "不存在";
            return flag;
        }

        if(competitorsMapper.selectByPrimaryKey(competitorsTagLinkEntity.getCompetitorId()).getHolder() != uid ){
            System.out.println("不是该条竞争对手信息的编辑者，没有权限添加标签");
            flag = "权限不足";
            return flag;
        }
        if(competitorsMapper.selectByBothId(competitorsTagLinkEntity.getCompetitorId(),competitorsTagLinkEntity.getTagId()) != null){
            System.out.println("该条竞争对手已有该标签");
            flag = "已存在";
            return flag;
        }
        if(tagMapper.selectByTagId(competitorsTagLinkEntity.getTagId()) != null &&
                competitorsMapper.selectByPrimaryKey(competitorsTagLinkEntity.getCompetitorId()) != null
                && competitorsMapper.selectByPrimaryKey(competitorsTagLinkEntity.getCompetitorId()).getHolder() == uid
                && competitorsMapper.selectByBothId(competitorsTagLinkEntity.getCompetitorId(),competitorsTagLinkEntity.getTagId()) == null){
            competitorsTagLinkEntity.setCtime(day);
            competitorsMapper.attach(competitorsTagLinkEntity);
            System.out.println("标签添加成功");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    public String deleteCompetitorsTag(CompetitorsTagLinkDeleteForm competitorsTagLinkDeleteForm, Integer uid) {

        CompetitorsTagLinkEntity competitorsTagLinkEntity = new CompetitorsTagLinkEntity();
        competitorsTagLinkEntity.setCompetitorsTagLinkId(competitorsTagLinkDeleteForm.getCompetitorsTagLinkId());
        System.out.println("开始为竞争对手移除标签信息");
        String flag = "";

        if(competitorsMapper.selectByCompetitorsTagLinkId(competitorsTagLinkEntity.getCompetitorsTagLinkId()) == null ){
            System.out.println("该竞争对手与标签的链接ID不存在" + competitorsMapper.selectByCompetitorsTagLinkId(competitorsTagLinkEntity.getCompetitorsTagLinkId()) +uid);
            flag = "不存在";
            return flag;
        }
        Integer competitorId = competitorsMapper.selectByCompetitorsTagLinkId(competitorsTagLinkEntity.getCompetitorsTagLinkId()).getCompetitorId();

        if(competitorsMapper.selectByPrimaryKey(competitorId).getHolder() !=uid){
            System.out.println("不是该条竞争对手信息的编辑者，没有权限添加标签");
            flag = "权限不足";
            return flag;
        }

        if(competitorsMapper.selectByCompetitorsTagLinkId(competitorsTagLinkEntity.getCompetitorsTagLinkId()) != null
                && competitorsMapper.selectByPrimaryKey(competitorId).getHolder() == uid){
            competitorsMapper.delete(competitorsTagLinkEntity);
            System.out.println("标签移除成功");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    public PageInfo<CompetitorsSelectVo> selectListByKeyWord(String keyword, Integer uid, Integer pageNum, Integer pageSize){
        System.out.println("开始查询竞争对手信息");
        if(keyword == null){
            System.out.println("关键字为空");
        }
        PageHelper.startPage(pageNum,pageSize);
        List<CompetitorsSelectVo> list = competitorsMapper.selectByKeyWord(keyword, uid);

        for (CompetitorsSelectVo competitorsSelectVO : list) {
            List<TagVo> list1 = competitorsMapper.selectTagByCompetitorId(competitorsSelectVO.getCompetitorId());
            competitorsSelectVO.setTagVoList(list1);
        }

        PageInfo<CompetitorsSelectVo> pageInfo = new PageInfo<>(list);
        System.out.println("查询成功");
        return pageInfo;
    }

    @Override
    public PageInfo<CompetitorsSelectVo> selectListByHolder(Integer uid, Integer pageNum, Integer pageSize){
        System.out.println("根据挖掘人id查找竞争对手信息");
        PageHelper.startPage(pageNum,pageSize);
        List<CompetitorsSelectVo> list = competitorsMapper.selectByHolder(uid);

        for (CompetitorsSelectVo competitorsSelectVO : list) {
            List<TagVo> list1 = competitorsMapper.selectTagByCompetitorId(competitorsSelectVO.getCompetitorId());
            competitorsSelectVO.setTagVoList(list1);
        }

        PageInfo<CompetitorsSelectVo> pageInfo = new PageInfo<>(list);
        System.out.println("查找成功");
        return pageInfo;
    }

}
