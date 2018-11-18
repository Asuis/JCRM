package com.jc.crm.service.competitors;

import com.github.pagehelper.PageInfo;
import com.jc.crm.form.competitors.CompetitorsForm;
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
    public int addCompetitors(CompetitorsForm competitorsForm, Integer uid) throws Exception {

        CompetitorsEntity competitorsEntity = new CompetitorsEntity();
        competitorsEntity.setCompetitorName(competitorsForm.getCompetitorName());
        competitorsEntity.setDescription(competitorsForm.getDescription());
        competitorsEntity.setEx1(competitorsForm.getEx1());
        competitorsEntity.setTypes(competitorsForm.getTypes());
        System.out.println("开始添加竞争对手");
        Date day = new Date();
        int flag = 0;
        if(competitorsForm.getCompetitorName() == "" || competitorsForm.getDescription() == ""){
            System.out.println("空的数据");
            flag = 3;
            return flag;
        }
        if(competitorsMapper.selectByPrimaryKey(competitorsForm.getCompetitorName()) != null){
            System.out.println("信息已存在");
            flag = 2;
            return flag;

        }
        if(competitorsMapper.selectByPrimaryKey(competitorsForm.getCompetitorName()) == null){
            competitorsEntity.setCtime(day);
            competitorsEntity.setUtime(day);
            competitorsEntity.setHolder(uid);

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
    public int updateCompetitors(CompetitorsEntity competitorsEntity) throws Exception {
        return 0;
    }

    @Override
    public PageInfo<CompetitorsEntity> selectByKeyWord(String keyword, Integer pageNum, Integer pageSize) throws Exception {
        return null;
    }
}
