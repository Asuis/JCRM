package com.jc.crm.service.competitors;

import com.github.pagehelper.PageInfo;
import com.jc.crm.form.competitors.CompetitorsInsertForm;
import com.jc.crm.form.competitors.CompetitorsUpdateForm;
import com.jc.crm.mapper.CompetitorsMapper;
import com.jc.crm.model.CompetitorsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        if(competitorsInsertForm.getCompetitorName() == "" || competitorsInsertForm.getDescription() == ""){
            System.out.println("空的数据");
            flag = 3;
            return flag;
        }
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
    public int deleteCompetitors(CompetitorsEntity competitorsEntity) throws Exception {
        return 0;
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

        if(competitorsMapper.selectByPrimaryKey(competitorsEntity.getCompetitorId()) == null){
            System.out.println("不存在");
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
    public PageInfo<CompetitorsEntity> selectByKeyWord(String keyword, Integer pageNum, Integer pageSize) throws Exception {
        return null;
    }
}
