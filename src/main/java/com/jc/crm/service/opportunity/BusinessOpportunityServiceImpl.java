package com.jc.crm.service.opportunity;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jc.crm.form.opportunity.*;
import com.jc.crm.mapper.BusinessOpportunityMapper;
import com.jc.crm.model.BusinessOpportunityAccountMoneyEntity;
import com.jc.crm.model.BusinessOpportunityEntity;
import com.jc.crm.model.BusinessOpportunitySourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 商业机会业务逻辑实现层接口
 * @author currysss 2018-11-17
 * */
@Service
public class BusinessOpportunityServiceImpl implements BusinessOpportunityService {

    private final BusinessOpportunityMapper businessOpportunityMapper;
    @Autowired
    public BusinessOpportunityServiceImpl(BusinessOpportunityMapper businessOpportunityMapper) {
        this.businessOpportunityMapper = businessOpportunityMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addBusinessOpportunity(BusinessOpportunityInsertForm businessOpportunityInsertForm, Integer uid) {

        BusinessOpportunityEntity businessOpportunityEntity = new BusinessOpportunityEntity();
        businessOpportunityEntity.setOppName(businessOpportunityInsertForm.getOppName());
        businessOpportunityEntity.setDescription(businessOpportunityInsertForm.getOppDescription());
        businessOpportunityEntity.setBudgetConfirmed(businessOpportunityInsertForm.getBudgetConfirmed());
        businessOpportunityEntity.setRoiAnalysisCompleted(businessOpportunityInsertForm.getRoiAnalysisCompleted());
        businessOpportunityEntity.setIsCompleted(businessOpportunityInsertForm.getIsCompleted());
        businessOpportunityEntity.setImportantLevel(businessOpportunityInsertForm.getImportantLevel());
        businessOpportunityEntity.setPossibility(businessOpportunityInsertForm.getPossibility());
        businessOpportunityEntity.setExecutor(businessOpportunityInsertForm.getExecutor());
        businessOpportunityEntity.setDeadline(businessOpportunityInsertForm.getDeadline());
        businessOpportunityEntity.setNextStep(businessOpportunityInsertForm.getNextStep());
        businessOpportunityEntity.setCid(businessOpportunityInsertForm.getCid());
        businessOpportunityEntity.setOppStageId(businessOpportunityInsertForm.getOppStageId());
        businessOpportunityEntity.setEx1(businessOpportunityInsertForm.getOppEx1());
        businessOpportunityEntity.setOppSourceId(businessOpportunityInsertForm.getOppSourceId());

        BusinessOpportunityAccountMoneyEntity businessOpportunityAccountMoneyEntity = new BusinessOpportunityAccountMoneyEntity();
        businessOpportunityAccountMoneyEntity.setAccountMoney(businessOpportunityInsertForm.getAccountMoney());

        BusinessOpportunitySourceEntity businessOpportunitySourceEntity = new BusinessOpportunitySourceEntity();

        System.out.println("开始添加商业机会");
        Date day = new Date();
        String flag = "";

        if(businessOpportunityMapper.selectBySourceId(businessOpportunityEntity.getOppSourceId()) == null){
            System.out.println("市场来源不存在");
            flag = "不存在";
            return flag;
        }
        if(businessOpportunityMapper.selectByStageId(businessOpportunityEntity.getOppStageId()) == null){
            System.out.println("阶段不存在");
            flag = "不存在";
            return flag;
        }
        if(businessOpportunityMapper.selectByOppName(businessOpportunityEntity.getOppName()) != null){
            System.out.println("机会名已存在");
            flag = "已存在";
            return flag;
        }
        if(businessOpportunityMapper.selectByOppName(businessOpportunityEntity.getOppName()) == null){

            businessOpportunityAccountMoneyEntity.setCtime(day);
            businessOpportunityAccountMoneyEntity.setUtime(day);
            businessOpportunityMapper.insertAccountMoney(businessOpportunityAccountMoneyEntity);

            businessOpportunityEntity.setOppAccountMoneyId(businessOpportunityAccountMoneyEntity.getOppAccountMoneyId());
            businessOpportunityEntity.setHolder(uid);
            businessOpportunityEntity.setCtime(day);
            businessOpportunityEntity.setUtime(day);
            businessOpportunityMapper.insert(businessOpportunityEntity);

            Integer weight = businessOpportunityMapper.selectBySourceId(businessOpportunityInsertForm.getOppSourceId()).getWeight();
            businessOpportunitySourceEntity.setOppSourceId(businessOpportunityInsertForm.getOppSourceId());
            businessOpportunitySourceEntity.setUtime(day);
            businessOpportunitySourceEntity.setWeight(weight + 1);
            businessOpportunityMapper.updateWeight(businessOpportunitySourceEntity);

            System.out.println("成功添加");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    public String updateBusinessOpportunity(BusinessOpportunityUpdateForm businessOpportunityUpdateForm, Integer uid){
        Date day = new Date();
        BusinessOpportunityEntity businessOpportunityEntity = new BusinessOpportunityEntity();
        businessOpportunityEntity.setOppName(businessOpportunityUpdateForm.getOppName());
        businessOpportunityEntity.setDescription(businessOpportunityUpdateForm.getOppDescription());
        businessOpportunityEntity.setBudgetConfirmed(businessOpportunityUpdateForm.getBudgetConfirmed());
        businessOpportunityEntity.setRoiAnalysisCompleted(businessOpportunityUpdateForm.getRoiAnalysisCompleted());
        businessOpportunityEntity.setIsCompleted(businessOpportunityUpdateForm.getIsCompleted());
        businessOpportunityEntity.setImportantLevel(businessOpportunityUpdateForm.getImportantLevel());
        businessOpportunityEntity.setPossibility(businessOpportunityUpdateForm.getPossibility());
        businessOpportunityEntity.setExecutor(businessOpportunityUpdateForm.getExecutor());
        businessOpportunityEntity.setDeadline(businessOpportunityUpdateForm.getDeadline());
        businessOpportunityEntity.setNextStep(businessOpportunityUpdateForm.getNextStep());
        businessOpportunityEntity.setCid(businessOpportunityUpdateForm.getCid());
        businessOpportunityEntity.setOppStageId(businessOpportunityUpdateForm.getOppStageId());
        businessOpportunityEntity.setEx1(businessOpportunityUpdateForm.getOppEx1());
        businessOpportunityEntity.setOppSourceId(businessOpportunityUpdateForm.getOppSourceId());
        businessOpportunityEntity.setBusinessOppId(businessOpportunityUpdateForm.getBusinessOppId());
        businessOpportunityEntity.setOppLossReasonId(businessOpportunityUpdateForm.getOppLossReasonId());

        BusinessOpportunityAccountMoneyEntity businessOpportunityAccountMoneyEntity = new BusinessOpportunityAccountMoneyEntity();
        businessOpportunityAccountMoneyEntity.setAccountMoney(businessOpportunityUpdateForm.getAccountMoney());
        businessOpportunityAccountMoneyEntity.setOppAccountMoneyId(businessOpportunityMapper.selectByBusinessOppId
                (businessOpportunityEntity.getBusinessOppId()).getOppAccountMoneyId());
        BusinessOpportunitySourceEntity businessOpportunitySourceEntity = new BusinessOpportunitySourceEntity();

        String flag = "";
        if(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityEntity.getBusinessOppId()) == null){
            System.out.println("错误的Id,该商业机会不存在");
            flag = "不存在";
            return flag;
        }
        businessOpportunityEntity.setHolder(businessOpportunityMapper.selectByBusinessOppId
                (businessOpportunityEntity.getBusinessOppId()).getHolder());
        System.out.println("开始修改商业机会信息,修改的机会id为" + businessOpportunityEntity.getBusinessOppId()
                + ",该条信息的所有者为" + businessOpportunityEntity.getHolder());

        if(businessOpportunityMapper.selectBySourceId(businessOpportunityEntity.getOppSourceId()) == null){
            System.out.println("市场来源不存在");
            flag = "不存在";
            return flag;
        }
        if(businessOpportunityMapper.selectByStageId(businessOpportunityEntity.getOppStageId()) == null){
            System.out.println("阶段不存在");
            flag = "不存在";
            return flag;
        }
        if(businessOpportunityMapper.selectByLossReasonId(businessOpportunityEntity.getOppLossReasonId()) == null){
            System.out.println("丢失原因不存在");
            flag = "不存在";
            return flag;
        }
        if(!businessOpportunityEntity.getHolder().equals(uid)){
            System.out.println("您不是该条信息的所有者，没有权限修改该条信息");
            flag = "权限不足";
            return flag;
        }
        if(businessOpportunityMapper.selectByOppName(businessOpportunityEntity.getOppName()) != null
                && !(businessOpportunityMapper.selectByOppName(businessOpportunityEntity.getOppName()).getBusinessOppId())
                .equals(businessOpportunityEntity.getBusinessOppId())){
            System.out.println("冲突，该机会已存在");
            flag = "已存在";
            return flag;
        }
        if(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityEntity.getBusinessOppId()) != null){
            businessOpportunityAccountMoneyEntity.setUtime(day);
            businessOpportunityMapper.updateAccountMoney(businessOpportunityAccountMoneyEntity);
            businessOpportunityEntity.setUtime(day);
            businessOpportunityMapper.update(businessOpportunityEntity);
            Integer weight = businessOpportunityMapper.selectBySourceId(businessOpportunityUpdateForm.getOppSourceId()).getWeight();
            businessOpportunitySourceEntity.setOppSourceId(businessOpportunityUpdateForm.getOppSourceId());
            businessOpportunitySourceEntity.setUtime(day);
            businessOpportunitySourceEntity.setWeight(weight + 1);
            businessOpportunityMapper.updateWeight(businessOpportunitySourceEntity);
            System.out.println("成功修改");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    public String updateBusinessOpportunityPartial(BusinessOpportunityUpdatePartialForm businessOpportunityUpdatePartialForm, Integer uid) {
        Date day = new Date();
        BusinessOpportunityEntity businessOpportunityEntity = new BusinessOpportunityEntity();
        businessOpportunityEntity.setBudgetConfirmed(businessOpportunityUpdatePartialForm.getBudgetConfirmed());
        businessOpportunityEntity.setRoiAnalysisCompleted(businessOpportunityUpdatePartialForm.getRoiAnalysisCompleted());
        businessOpportunityEntity.setIsCompleted(businessOpportunityUpdatePartialForm.getIsCompleted());
        businessOpportunityEntity.setPossibility(businessOpportunityUpdatePartialForm.getPossibility());
        businessOpportunityEntity.setNextStep(businessOpportunityUpdatePartialForm.getNextStep());
        businessOpportunityEntity.setOppStageId(businessOpportunityUpdatePartialForm.getOppStageId());
        businessOpportunityEntity.setEx1(businessOpportunityUpdatePartialForm.getEx1());
        businessOpportunityEntity.setBusinessOppId(businessOpportunityUpdatePartialForm.getBusinessOppId());
        businessOpportunityEntity.setOppLossReasonId(businessOpportunityUpdatePartialForm.getOppLossReasonId());

        String flag = "";
        if(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityEntity.getBusinessOppId()) == null){
            System.out.println("错误的Id,该商业机会不存在");
            flag = "不存在";
            return flag;
        }
        businessOpportunityEntity.setExecutor(businessOpportunityMapper.selectByBusinessOppId
                (businessOpportunityEntity.getBusinessOppId()).getExecutor());
        businessOpportunityEntity.setHolder(businessOpportunityMapper.selectByBusinessOppId
                (businessOpportunityEntity.getBusinessOppId()).getHolder());
        System.out.println("开始修改商业机会信息,修改的机会id为" + businessOpportunityEntity.getBusinessOppId()
                + ",该条信息的所有者为" + businessOpportunityEntity.getHolder()
                + ",该条信息的跟进者为" + businessOpportunityEntity.getExecutor());

        if(businessOpportunityMapper.selectByStageId(businessOpportunityEntity.getOppStageId()) == null){
            System.out.println("阶段不存在");
            flag = "不存在";
            return flag;
        }
        if(businessOpportunityMapper.selectByLossReasonId(businessOpportunityEntity.getOppLossReasonId()) == null){
            System.out.println("丢失原因不存在");
            flag = "不存在";
            return flag;
        }
        if(!businessOpportunityEntity.getExecutor().equals(uid) && !businessOpportunityEntity.getHolder().equals(uid)){
            System.out.println("您不是该条信息的跟进者或所有者，没有权限修改该条信息");
            flag = "权限不足";
            return flag;
        }
        if(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityEntity.getBusinessOppId()) != null){
            businessOpportunityEntity.setUtime(day);
            businessOpportunityMapper.updatePartial(businessOpportunityEntity);
            System.out.println("成功修改");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    public String addBusinessOpportunitySource(BusinessOpportunitySourceInsertForm businessOpportunitySourceInsertForm, Integer uid) {

        BusinessOpportunitySourceEntity businessOpportunitySourceEntity = new BusinessOpportunitySourceEntity();
        businessOpportunitySourceEntity.setSourceName(businessOpportunitySourceInsertForm.getSourceName());
        businessOpportunitySourceEntity.setDescription(businessOpportunitySourceInsertForm.getDescription());
        businessOpportunitySourceEntity.setStatusId(businessOpportunitySourceInsertForm.getStatusId());
        businessOpportunitySourceEntity.setTypeId(businessOpportunitySourceInsertForm.getTypeId());
        businessOpportunitySourceEntity.setSendCount(businessOpportunitySourceInsertForm.getSendCount());
        businessOpportunitySourceEntity.setBudgetCost(businessOpportunitySourceInsertForm.getBudgetCost());
        businessOpportunitySourceEntity.setActualCost(businessOpportunitySourceInsertForm.getActualCost());
        businessOpportunitySourceEntity.setExpectedIncome(businessOpportunitySourceInsertForm.getExpectedIncome());
        businessOpportunitySourceEntity.setResponsePercentage(businessOpportunitySourceInsertForm.getResponsePercentage());
        businessOpportunitySourceEntity.setEx1(businessOpportunitySourceInsertForm.getEx1());
        businessOpportunitySourceEntity.setSdate(businessOpportunitySourceInsertForm.getSdate());
        businessOpportunitySourceEntity.setEdate(businessOpportunitySourceInsertForm.getEdate());

        System.out.println("开始添加市场来源");
        Date day = new Date();
        String flag = "";

        if(businessOpportunityMapper.selectBySourceStatusId(businessOpportunitySourceEntity.getStatusId()) == null){
            System.out.println("状态不存在");
            flag = "不存在";
            return flag;
        }
        if(businessOpportunityMapper.selectBySourceTypeId(businessOpportunitySourceEntity.getTypeId()) == null){
            System.out.println("类别不存在");
            flag = "不存在";
            return flag;
        }
        if(businessOpportunityMapper.selectBySourceName(businessOpportunitySourceEntity.getSourceName()) != null){
            System.out.println("市场来源名已存在");
            flag = "已存在";
            return flag;
        }
        if(businessOpportunityMapper.selectBySourceName(businessOpportunitySourceEntity.getSourceName()) == null){
            businessOpportunitySourceEntity.setHolder(uid);
            businessOpportunitySourceEntity.setWeight(10);
            businessOpportunitySourceEntity.setCtime(day);
            businessOpportunitySourceEntity.setUtime(day);

            businessOpportunityMapper.insertSource(businessOpportunitySourceEntity);
            System.out.println("成功添加");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    public String updateBusinessOpportunitySource(BusinessOpportunitySourceUpdateForm businessOpportunitySourceUpdateForm, Integer uid) {
        BusinessOpportunitySourceEntity businessOpportunitySourceEntity = new BusinessOpportunitySourceEntity();
        businessOpportunitySourceEntity.setSourceName(businessOpportunitySourceUpdateForm.getSourceName());
        businessOpportunitySourceEntity.setDescription(businessOpportunitySourceUpdateForm.getDescription());
        businessOpportunitySourceEntity.setStatusId(businessOpportunitySourceUpdateForm.getStatusId());
        businessOpportunitySourceEntity.setTypeId(businessOpportunitySourceUpdateForm.getTypeId());
        businessOpportunitySourceEntity.setSendCount(businessOpportunitySourceUpdateForm.getSendCount());
        businessOpportunitySourceEntity.setBudgetCost(businessOpportunitySourceUpdateForm.getBudgetCost());
        businessOpportunitySourceEntity.setActualCost(businessOpportunitySourceUpdateForm.getActualCost());
        businessOpportunitySourceEntity.setExpectedIncome(businessOpportunitySourceUpdateForm.getExpectedIncome());
        businessOpportunitySourceEntity.setResponsePercentage(businessOpportunitySourceUpdateForm.getResponsePercentage());
        businessOpportunitySourceEntity.setEx1(businessOpportunitySourceUpdateForm.getEx1());
        businessOpportunitySourceEntity.setSdate(businessOpportunitySourceUpdateForm.getSdate());
        businessOpportunitySourceEntity.setEdate(businessOpportunitySourceUpdateForm.getEdate());
        businessOpportunitySourceEntity.setOppSourceId(businessOpportunitySourceUpdateForm.getOppSourceId());

        Date day = new Date();
        String flag = "";

        if(businessOpportunityMapper.selectBySourceId(businessOpportunitySourceEntity.getOppSourceId()) == null){
            System.out.println("市场活动不存在");
            flag = "不存在";
            return flag;
        }
        businessOpportunitySourceEntity.setHolder(businessOpportunityMapper.selectBySourceId
                (businessOpportunitySourceEntity.getOppSourceId()).getHolder());
        System.out.println("开始修改市场来源信息,修改的市场来源id为" + businessOpportunitySourceEntity.getOppSourceId()
                + ",该条信息的所有者为" + businessOpportunitySourceEntity.getHolder());

        if(businessOpportunityMapper.selectBySourceStatusId(businessOpportunitySourceEntity.getStatusId()) == null){
            System.out.println("状态不存在");
            flag = "不存在";
            return flag;
        }
        if(businessOpportunityMapper.selectBySourceTypeId(businessOpportunitySourceEntity.getTypeId()) == null){
            System.out.println("类别不存在");
            flag = "不存在";
            return flag;
        }
        if(!businessOpportunitySourceEntity.getHolder().equals(uid)){
            System.out.println("您不是该条信息的所有者，没有权限修改该条信息");
            flag = "权限不足";
            return flag;
        }
        if(businessOpportunityMapper.selectBySourceName(businessOpportunitySourceEntity.getSourceName()) != null
                && !(businessOpportunityMapper.selectBySourceName(businessOpportunitySourceEntity.getSourceName()).getOppSourceId())
                .equals(businessOpportunitySourceEntity.getOppSourceId())){
            System.out.println("冲突，该市场来源名已存在");
            flag = "已存在";
            return flag;
        }
        if(businessOpportunityMapper.selectBySourceName(businessOpportunitySourceEntity.getSourceName()) == null){
            businessOpportunitySourceEntity.setUtime(day);
            businessOpportunityMapper.updateSource(businessOpportunitySourceEntity);
            System.out.println("成功修改");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    public PageInfo<BusinessOpportunityStageSelectVo> selectStageList(Integer pageNum, Integer pageSize) {
        System.out.println("获得商业机会阶段信息列表");
        PageHelper.startPage(pageNum,pageSize);
        List<BusinessOpportunityStageSelectVo> list = businessOpportunityMapper.getStageList();
        PageInfo<BusinessOpportunityStageSelectVo> pageInfo = new PageInfo<>(list);
        System.out.println("查找成功");
        return pageInfo;
    }

    @Override
    public PageInfo<BusinessOpportunitySourceSelectVo> selectSourceList(Integer pageNum, Integer pageSize) {
        System.out.println("获得商业机会市场来源信息列表");
        PageHelper.startPage(pageNum,pageSize);
        List<BusinessOpportunitySourceSelectVo> list = businessOpportunityMapper.getSourceList();
        PageInfo<BusinessOpportunitySourceSelectVo> pageInfo = new PageInfo<>(list);
        System.out.println("查找成功");
        return pageInfo;
    }

    @Override
    public PageInfo<BusinessOpportunityLossReasonSelectVo> selectLossReasonList(Integer pageNum, Integer pageSize) {
        System.out.println("获得商业机会丢失原因信息列表");
        PageHelper.startPage(pageNum,pageSize);
        List<BusinessOpportunityLossReasonSelectVo> list = businessOpportunityMapper.getLossReasonList();
        PageInfo<BusinessOpportunityLossReasonSelectVo> pageInfo = new PageInfo<>(list);
        System.out.println("查找成功");
        return pageInfo;
    }

    @Override
    public PageInfo<BusinessOpportunitySourceStatusSelectVo> selectSourceStatusList(Integer pageNum, Integer pageSize) {
        System.out.println("获得商业机会市场来源状态信息列表");
        PageHelper.startPage(pageNum,pageSize);
        List<BusinessOpportunitySourceStatusSelectVo> list = businessOpportunityMapper.getSourceStatusList();
        PageInfo<BusinessOpportunitySourceStatusSelectVo> pageInfo = new PageInfo<>(list);
        System.out.println("查找成功");
        return pageInfo;
    }

    @Override
    public PageInfo<BusinessOpportunitySourceTypeSelectVo> selectSourceTypeList(Integer pageNum, Integer pageSize) {
        System.out.println("获得商业机会市场来源类别信息列表");
        PageHelper.startPage(pageNum,pageSize);
        List<BusinessOpportunitySourceTypeSelectVo> list = businessOpportunityMapper.getSourceTypeList();
        PageInfo<BusinessOpportunitySourceTypeSelectVo> pageInfo = new PageInfo<>(list);
        System.out.println("查找成功");
        return pageInfo;
    }
}
