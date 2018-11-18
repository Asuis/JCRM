package com.jc.crm.service.competitors;

import com.github.pagehelper.PageInfo;
import com.jc.crm.form.competitors.CompetitorsForm;
import com.jc.crm.model.CompetitorsEntity;

/**
 * 竞争对手接口
 * @author klaus 2018-11-16
 * */
public interface CompetitorsService {

    /**
     * @return 是否成功添加竞争对手信息 返回信息 200成功， -1失败， 100信息已存在， 401异常。
     * */
    int addCompetitors(CompetitorsForm competitorsForm, Integer uid)throws Exception;

    /**
     * @return 是否成功删除竞争对手信息 返回信息 200成功， -1失败， 401异常。
     * */
    int deleteCompetitors(CompetitorsEntity competitorsEntity)throws Exception;

    /**
     * @return 是否成功修改竞争对手信息 返回信息 200成功， -1失败， 402信息相同冲突， 401异常。
     * */
    int updateCompetitors(CompetitorsEntity competitorsEntity)throws Exception;

    /**
     * @param keyword 关键字
     * @return 分页后的基本信息
     * */
    PageInfo<CompetitorsEntity> selectByKeyWord(String keyword,Integer pageNum, Integer pageSize)throws Exception;

}
