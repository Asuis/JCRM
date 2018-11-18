package com.jc.crm.service.competitors;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jc.crm.form.competitors.CompetitorsDeleteForm;
import com.jc.crm.form.competitors.CompetitorsInsertForm;
import com.jc.crm.form.competitors.CompetitorsUpdateForm;
import com.jc.crm.mapper.CompetitorsMapper;
import com.jc.crm.model.CompetitorsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompetitorsServiceImpl implements CompetitorsService{

    @Autowired
    private CompetitorsMapper competitorsMapper;


    @Override
    public int addCompetitors(CompetitorsInsertForm competitorsInsertForm, Integer uid) throws Exception {

        CompetitorsEntity competitorsEntity = new CompetitorsEntity();
        competitorsEntity.setCompetitorName(competitorsInsertForm.getCompetitorName());
        competitorsEntity.setDescription(competitorsInsertForm.getDescription());
        competitorsEntity.setEx1(competitorsInsertForm.getEx1());
        competitorsEntity.setTypes(competitorsInsertForm.getTypes());
        System.out.println("开始添加竞争对手");
        Date day = new Date();
        int flag = 0;

        if(competitorsMapper.selectByCompetitorsName(competitorsInsertForm.getCompetitorName()) != null){
            System.out.println("信息已存在");
            flag = 2;
            return flag;
        }
        if(competitorsMapper.selectByCompetitorsName(competitorsInsertForm.getCompetitorName()) == null){
            competitorsEntity.setCtime(day);
            competitorsEntity.setUtime(day);
            competitorsEntity.setHolder(uid);
            competitorsEntity.setStatus(0);

            competitorsMapper.insert(competitorsEntity);
            System.out.println("成功添加");
            flag = 1;
            return flag;
        }
        return flag;
    }

    @Override
    public int updateCompetitors(CompetitorsUpdateForm competitorsUpdateForm) throws Exception {
        Date day = new Date();
        CompetitorsEntity competitorsEntity = new CompetitorsEntity();
        competitorsEntity.setCompetitorName(competitorsUpdateForm.getCompetitorName());
        competitorsEntity.setDescription(competitorsUpdateForm.getDescription());
        competitorsEntity.setEx1(competitorsUpdateForm.getEx1());
        competitorsEntity.setTypes(competitorsUpdateForm.getTypes());
        competitorsEntity.setCompetitorId(competitorsUpdateForm.getCompetitorId());

        System.out.println("开始修改竞争对手信息,修改的对手id为"+competitorsEntity.getCompetitorId());
        int flag = 0;

        if(competitorsMapper.selectByCompetitorsName(competitorsEntity.getCompetitorName()) != null &&
                competitorsMapper.selectByCompetitorsName(competitorsEntity.getCompetitorName()).getCompetitorId()
                        != competitorsEntity.getCompetitorId()){
            System.out.println("冲突，该公司已存在");
            flag = 3;
            return flag;
        }
        if(competitorsMapper.selectByPrimaryKey(competitorsEntity.getCompetitorId()) == null){
            System.out.println("错误的Id,该竞争对手不存在");
            flag = 2;
            return flag;
        }
        if(competitorsMapper.selectByPrimaryKey(competitorsEntity.getCompetitorId()) != null){
            competitorsEntity.setUtime(day);
            competitorsMapper.update(competitorsEntity);
            System.out.println("成功修改");
            flag = 1;
            return flag;
        }
        return flag;
    }

    @Override
    public int deleteCompetitors(CompetitorsDeleteForm competitorsDeleteForm) throws Exception {

        CompetitorsEntity competitorsEntity = new CompetitorsEntity();
        competitorsEntity.setStatus(competitorsDeleteForm.getStatus());
        competitorsEntity.setCompetitorId(competitorsDeleteForm.getCompetitorId());
        System.out.println("开始删除竞争对手");
        Date day = new Date();
        int flag = 0;

        if(competitorsEntity.getStatus() !=0 && competitorsEntity.getStatus() != 1){
            System.out.println("传递的数据格式出错，只能为0或1"+competitorsEntity.getStatus());
            flag = 3;
            return flag;
        }
        if(competitorsMapper.selectByPrimaryKey(competitorsEntity.getCompetitorId()) == null){
            System.out.println("错误的Id,该竞争对手不存在");
            flag = 2;
            return flag;
        }
        if(competitorsMapper.selectByPrimaryKey(competitorsEntity.getCompetitorId()) != null){
            competitorsEntity.setUtime(day);
            competitorsMapper.updateStatus(competitorsEntity);
            System.out.println("成功修改该竞争对手状态为已删除");
            flag = 1;
            return flag;
        }
        return flag;
    }

    @Override
    public PageInfo<CompetitorsEntity> selectListByKeyWord(String keyword, Integer pageNum, Integer pageSize) throws Exception {
        System.out.println("开始查询竞争对手信息");
        if(keyword == ""){
            System.out.println("关键字为空");
        }
        PageHelper.startPage(pageNum,pageSize);
        List<CompetitorsEntity> list = competitorsMapper.selectByKeyWord(keyword);
        PageInfo<CompetitorsEntity> pageInfo = new PageInfo<CompetitorsEntity>(list);
        System.out.println("执行成功");
        return pageInfo;
    }

    @Override
    public PageInfo<CompetitorsEntity> selectListByHolder(int holder, Integer pageNum, Integer pageSize) throws Exception {
        System.out.println("根据挖掘人id查找竞争对手信息");
        PageHelper.startPage(pageNum,pageSize);
        List<CompetitorsEntity> list = competitorsMapper.selectByHolder(holder);
        PageInfo<CompetitorsEntity> pageInfo = new PageInfo<CompetitorsEntity>(list);
        System.out.println("执行成功");
        return pageInfo;
    }

}
