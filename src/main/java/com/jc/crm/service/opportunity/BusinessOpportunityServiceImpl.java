package com.jc.crm.service.opportunity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jc.crm.config.logger.SystemServiceLog;
import com.jc.crm.form.opportunity.*;
import com.jc.crm.mapper.BusinessOpportunityMapper;
import com.jc.crm.mapper.DepartmentMapper;
import com.jc.crm.model.*;
import com.jc.crm.service.department.DepartmentService;
import com.jc.crm.service.department.vo.DepartmentMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 商业机会业务逻辑实现层接口
 * @author currysss 2018-11-17
 * */
@Service
public class BusinessOpportunityServiceImpl implements BusinessOpportunityService {

    private final BusinessOpportunityMapper businessOpportunityMapper;

    private final DepartmentService departmentService;

    private final DepartmentMapper departmentMapper;

    @Autowired
    public BusinessOpportunityServiceImpl(BusinessOpportunityMapper businessOpportunityMapper,DepartmentService departmentService,DepartmentMapper departmentMapper) {
        this.businessOpportunityMapper = businessOpportunityMapper;
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @SystemServiceLog
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
        BusinessRecordEntity businessRecordEntity = new BusinessRecordEntity();

        System.out.println("开始添加商业机会");
        Date day = new Date();
        String flag = "";
        Integer stage = 5;
        Integer we = 10;
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
        if(departmentMapper.selectByUid(uid).getWeight().equals(we)){
            System.out.println("没有添加机会的权限");
            flag = "权限不足";
            return flag;
        }
        if(businessOpportunityMapper.selectByOppName(businessOpportunityEntity.getOppName()) != null){
            System.out.println("机会名已存在");
            flag = "已存在";
            return flag;
        }
        if(businessOpportunityMapper.selectByOppName(businessOpportunityEntity.getOppName()) == null){

            businessOpportunityEntity.setHolder(uid);
            businessOpportunityEntity.setCtime(day);
            businessOpportunityEntity.setUtime(day);
            businessOpportunityEntity.setIsDeleted(0);
            businessOpportunityMapper.insert(businessOpportunityEntity);

            businessOpportunityAccountMoneyEntity.setBusinessOppId(businessOpportunityEntity.getBusinessOppId());
            businessOpportunityAccountMoneyEntity.setCtime(day);
            businessOpportunityAccountMoneyEntity.setUtime(day);
            businessOpportunityMapper.insertAccountMoney(businessOpportunityAccountMoneyEntity);

            if(businessOpportunityEntity.getOppStageId().equals(stage)){
                businessRecordEntity.setCtime(day);
                businessRecordEntity.setBusinessOppId(businessOpportunityEntity.getBusinessOppId());
                businessRecordEntity.setDescription(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityEntity.getBusinessOppId()).getDescription());
                businessOpportunityMapper.insertRecord(businessRecordEntity);
            }

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
    @Transactional(rollbackFor = Exception.class)
    @SystemServiceLog
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
        BusinessOpportunitySourceEntity businessOpportunitySourceEntity = new BusinessOpportunitySourceEntity();
        BusinessRecordEntity businessRecordEntity = new BusinessRecordEntity();
        String flag = "";
        Integer stage = 5;
        if(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityEntity.getBusinessOppId()) == null){
            flag = "不存在";
            return flag;
        }
        businessOpportunityEntity.setHolder(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityEntity.getBusinessOppId()).getHolder());
        if(businessOpportunityMapper.selectBySourceId(businessOpportunityEntity.getOppSourceId()) == null){
            flag = "不存在";
            return flag;
        }
        if(businessOpportunityMapper.selectByStageId(businessOpportunityEntity.getOppStageId()) == null){
            flag = "不存在";
            return flag;
        }
        if(businessOpportunityMapper.selectByLossReasonId(businessOpportunityEntity.getOppLossReasonId()) == null){
            flag = "不存在";
            return flag;
        }
        if(!businessOpportunityEntity.getHolder().equals(uid)){
            flag = "权限不足";
            return flag;
        }
        if(businessOpportunityMapper.selectByOppName(businessOpportunityEntity.getOppName()) != null && !(businessOpportunityMapper.selectByOppName(businessOpportunityEntity.getOppName()).getBusinessOppId()).equals(businessOpportunityEntity.getBusinessOppId())){
            flag = "已存在";
            return flag;
        }
        if(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityEntity.getBusinessOppId()) != null){
            businessOpportunityEntity.setUtime(day);
            businessOpportunityMapper.update(businessOpportunityEntity);
            if(businessOpportunityMapper.selectAccountMoneyByBusinessOppId(businessOpportunityEntity.getBusinessOppId(),businessOpportunityAccountMoneyEntity.getAccountMoney()) == null){
                businessOpportunityAccountMoneyEntity.setBusinessOppId(businessOpportunityEntity.getBusinessOppId());
                businessOpportunityAccountMoneyEntity.setCtime(day);
                businessOpportunityAccountMoneyEntity.setUtime(day);
                businessOpportunityMapper.insertAccountMoney(businessOpportunityAccountMoneyEntity);
            }
            if(businessOpportunityEntity.getOppStageId().equals(stage)){
                businessRecordEntity.setBusinessOppId(businessOpportunityEntity.getBusinessOppId());
                if(businessOpportunityMapper.selectRecordByOppId(businessRecordEntity.getBusinessOppId()) != null){
                    businessOpportunityMapper.deleteRecord(businessRecordEntity);
                }
                businessRecordEntity.setDescription(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityEntity.getBusinessOppId()).getDescription());
                businessRecordEntity.setCtime(day);
                businessOpportunityMapper.insertRecord(businessRecordEntity);
            }
            Integer weight = businessOpportunityMapper.selectBySourceId(businessOpportunityUpdateForm.getOppSourceId()).getWeight();
            businessOpportunitySourceEntity.setOppSourceId(businessOpportunityUpdateForm.getOppSourceId());
            businessOpportunitySourceEntity.setUtime(day);
            businessOpportunitySourceEntity.setWeight(weight + 1);
            businessOpportunityMapper.updateWeight(businessOpportunitySourceEntity);
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @SystemServiceLog
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
        BusinessRecordEntity businessRecordEntity = new BusinessRecordEntity();
        String flag = "";
        Integer stage = 5;
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
            if(businessOpportunityEntity.getOppStageId().equals(stage)){
                businessRecordEntity.setBusinessOppId(businessOpportunityEntity.getBusinessOppId());
                if(businessOpportunityMapper.selectRecordByOppId(businessRecordEntity.getBusinessOppId()) != null){
                    businessOpportunityMapper.deleteRecord(businessRecordEntity);
                }
                businessRecordEntity.setCtime(day);
                businessRecordEntity.setDescription(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityEntity.getBusinessOppId()).getDescription());
                businessOpportunityMapper.insertRecord(businessRecordEntity);
            }
            System.out.println("成功修改");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    @SystemServiceLog
    public String deleteBusinessOpportunity(BusinessOpportunityDeleteForm businessOpportunityDeleteForm, Integer uid) {
        BusinessOpportunityEntity businessOpportunityEntity = new BusinessOpportunityEntity();
        businessOpportunityEntity.setIsDeleted(businessOpportunityDeleteForm.getIsDeleted());
        businessOpportunityEntity.setBusinessOppId(businessOpportunityDeleteForm.getBusinessOppId());

        System.out.println("开始删除商业机会");
        Date day = new Date();
        String flag = "";

        if(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityEntity.getBusinessOppId()) == null){
            System.out.println("错误的Id,该商业机会不存在");
            flag = "不存在";
            return flag;
        }
        businessOpportunityEntity.setHolder(businessOpportunityMapper.selectByBusinessOppId
                (businessOpportunityEntity.getBusinessOppId()).getHolder());
        if(!businessOpportunityMapper.selectByBusinessOppId(businessOpportunityEntity.getBusinessOppId()).getHolder().equals(uid)){
            System.out.println("您不是该商业机会的所有者，没有权限修改该条信息状态");
            flag = "权限不足";
            return flag;
        }
        if(businessOpportunityEntity.getIsDeleted() !=0 && businessOpportunityEntity.getIsDeleted() != 1){
            System.out.println("传递的数据格式出错，只能为0或1" + businessOpportunityEntity.getIsDeleted());
            flag = "错误数据格式";
            return flag;
        }
        if(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityEntity.getBusinessOppId()) != null){
            businessOpportunityEntity.setUtime(day);
            businessOpportunityMapper.delete(businessOpportunityEntity);
            System.out.println("成功修改该该商业机会状态为已删除/未删除");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    @SystemServiceLog
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
    @SystemServiceLog
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
    @SystemServiceLog
    public String addBusinessOpportunityApplication(BusinessOpportunityApplicationInsertForm businessOpportunityApplicationInsertForm, Integer uid) {
        BusinessOpportunityApplicationEntity businessOpportunityApplicationEntity = new BusinessOpportunityApplicationEntity();
        businessOpportunityApplicationEntity.setAccountMoney(businessOpportunityApplicationInsertForm.getAccountMoney());
        businessOpportunityApplicationEntity.setDeadline(businessOpportunityApplicationInsertForm.getDeadline());
        businessOpportunityApplicationEntity.setApplicationReason(businessOpportunityApplicationInsertForm.getApplicationReason());
        businessOpportunityApplicationEntity.setBusinessOppId(businessOpportunityApplicationInsertForm.getBusinessOppId());
        businessOpportunityApplicationEntity.setEx1(businessOpportunityApplicationInsertForm.getEx1());

        BusinessOpportunityEntity businessOpportunityEntity = new BusinessOpportunityEntity();

        Date day = new Date();
        String flag = "";
        System.out.println("开始添加修改申请信息");

        if(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityApplicationEntity.getBusinessOppId()) == null){
            System.out.println("商业机会不存在");
            flag = "不存在";
            return flag;
        }
        businessOpportunityEntity.setExecutor(businessOpportunityMapper.selectByBusinessOppId
                (businessOpportunityApplicationEntity.getBusinessOppId()).getExecutor());
        businessOpportunityEntity.setHolder(businessOpportunityMapper.selectByBusinessOppId
                (businessOpportunityApplicationEntity.getBusinessOppId()).getHolder());
        if(businessOpportunityEntity.getHolder().equals(uid)){
            System.out.println("您是该条信息的所有者，无需申请修改，可直接编辑该条信息");
            flag = "不需要";
            return flag;
        }
        if(!businessOpportunityEntity.getExecutor().equals(uid)){
            System.out.println("您不是该条信息的跟进者，没有权限申请修改该条信息");
            flag = "权限不足";
            return flag;
        }
        if(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityApplicationEntity.getBusinessOppId()) != null){
            businessOpportunityApplicationEntity.setStatus("待审批");
            businessOpportunityApplicationEntity.setCtime(day);
            businessOpportunityApplicationEntity.setUtime(day);
            businessOpportunityMapper.insertApplication(businessOpportunityApplicationEntity);
            System.out.println("成功添加");
            flag = "成功";
            return flag;
        }
        return flag;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @SystemServiceLog
    public String agreeBusinessOpportunityApplication(BusinessOpportunityAgreeApplicationForm businessOpportunityAgreeApplicationForm, Integer uid) {
        BusinessOpportunityApplicationEntity businessOpportunityApplicationEntity = new BusinessOpportunityApplicationEntity();
        businessOpportunityApplicationEntity.setOppApplicationId(businessOpportunityAgreeApplicationForm.getOppApplicationId());
        businessOpportunityApplicationEntity.setBusinessOppId(businessOpportunityAgreeApplicationForm.getBusinessOppId());

        BusinessOpportunityEntity businessOpportunityEntity = new BusinessOpportunityEntity();

        BusinessOpportunityAccountMoneyEntity businessOpportunityAccountMoneyEntity = new BusinessOpportunityAccountMoneyEntity();

        Date day = new Date();
        String flag = "";
        System.out.println("正在同意修改申请");

        if(businessOpportunityMapper.selectByApplicationId(businessOpportunityApplicationEntity.getOppApplicationId()) == null){
            System.out.println("该申请不存在");
            flag = "不存在";
            return flag;
        }
        if(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityApplicationEntity.getBusinessOppId()) == null){
            System.out.println("该商业机会不存在");
            flag = "不存在";
            return flag;
        }
        if(businessOpportunityMapper.selectByBothId(businessOpportunityApplicationEntity.getOppApplicationId(), businessOpportunityApplicationEntity.getBusinessOppId()) == null){
            System.out.println("匹配的两者条件的申请不存在");
            flag = "不存在";
            return flag;
        }
        businessOpportunityEntity.setHolder(businessOpportunityMapper.selectByBusinessOppId
                (businessOpportunityApplicationEntity.getBusinessOppId()).getHolder());
        if(!businessOpportunityEntity.getHolder().equals(uid)){
            System.out.println("您不是该条信息的所有者，没有权限同意该请求");
            flag = "权限不足";
            return flag;
        }
        if(businessOpportunityMapper.selectByApplicationId(businessOpportunityApplicationEntity.getOppApplicationId()) != null
                && businessOpportunityEntity.getHolder().equals(uid)){
            businessOpportunityApplicationEntity.setStatus("同意");
            businessOpportunityApplicationEntity.setUtime(day);
            businessOpportunityMapper.agree(businessOpportunityApplicationEntity);

            String accountMoney = businessOpportunityMapper.selectByApplicationId(businessOpportunityApplicationEntity.getOppApplicationId()).getAccountMoney();

            businessOpportunityAccountMoneyEntity.setAccountMoney(accountMoney);
            businessOpportunityAccountMoneyEntity.setBusinessOppId(businessOpportunityApplicationEntity.getBusinessOppId());
            businessOpportunityAccountMoneyEntity.setCtime(day);
            businessOpportunityAccountMoneyEntity.setUtime(day);
            businessOpportunityMapper.insertAccountMoney(businessOpportunityAccountMoneyEntity);

            businessOpportunityEntity.setBusinessOppId(businessOpportunityMapper.selectByApplicationId(businessOpportunityApplicationEntity.getOppApplicationId()).getBusinessOppId());
            businessOpportunityEntity.setDeadline(businessOpportunityMapper.selectByApplicationId(businessOpportunityApplicationEntity.getOppApplicationId()).getDeadline());
            businessOpportunityEntity.setUtime(day);
            businessOpportunityMapper.updateDeadline(businessOpportunityEntity);
            System.out.println("已同意");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    @SystemServiceLog
    public String rejectBusinessOpportunityApplication(BusinessOpportunityRejectApplicationForm businessOpportunityRejectApplicationForm, Integer uid) {
        BusinessOpportunityApplicationEntity businessOpportunityApplicationEntity = new BusinessOpportunityApplicationEntity();
        businessOpportunityApplicationEntity.setOppApplicationId(businessOpportunityRejectApplicationForm.getOppApplicationId());
        businessOpportunityApplicationEntity.setBusinessOppId(businessOpportunityRejectApplicationForm.getBusinessOppId());

        BusinessOpportunityEntity businessOpportunityEntity = new BusinessOpportunityEntity();

        Date day = new Date();
        String flag = "";
        System.out.println("正在拒绝修改申请");

        if(businessOpportunityMapper.selectByApplicationId(businessOpportunityApplicationEntity.getOppApplicationId()) == null){
            System.out.println("该申请不存在");
            flag = "不存在";
            return flag;
        }
        if(businessOpportunityMapper.selectByBusinessOppId(businessOpportunityApplicationEntity.getBusinessOppId()) == null){
            System.out.println("该商业机会不存在");
            flag = "不存在";
            return flag;
        }
        if(businessOpportunityMapper.selectByBothId(businessOpportunityApplicationEntity.getOppApplicationId(), businessOpportunityApplicationEntity.getBusinessOppId()) == null){
            System.out.println("匹配的两者条件的申请不存在");
            flag = "不存在";
            return flag;
        }
        businessOpportunityEntity.setHolder(businessOpportunityMapper.selectByBusinessOppId
                (businessOpportunityApplicationEntity.getBusinessOppId()).getHolder());
        if(!businessOpportunityEntity.getHolder().equals(uid)){
            System.out.println("您不是该条信息的所有者，没有权限拒绝该请求");
            flag = "权限不足";
            return flag;
        }
        if(businessOpportunityMapper.selectByApplicationId(businessOpportunityApplicationEntity.getOppApplicationId()) != null
                && businessOpportunityEntity.getHolder().equals(uid)){
            businessOpportunityApplicationEntity.setStatus("拒绝");
            businessOpportunityApplicationEntity.setRejectionReason(businessOpportunityRejectApplicationForm.getRejectionReason());
            businessOpportunityApplicationEntity.setUtime(day);
            businessOpportunityMapper.reject(businessOpportunityApplicationEntity);

            System.out.println("已拒绝");
            flag = "成功";
            return flag;
        }
        return flag;
    }

    @Override
    @SystemServiceLog
    public PageInfo<BusinessOpportunityStageSelectVo> selectStageList(Integer pageNum, Integer pageSize) {
        System.out.println("获得商业机会阶段信息列表");
        PageHelper.startPage(pageNum,pageSize);
        List<BusinessOpportunityStageSelectVo> list = businessOpportunityMapper.getStageList();
        PageInfo<BusinessOpportunityStageSelectVo> pageInfo = new PageInfo<>(list);
        System.out.println("查找成功");
        return pageInfo;
    }

    @Override
    @SystemServiceLog
    public PageInfo<BusinessOpportunitySourceSelectVo> selectSourceList(Integer pageNum, Integer pageSize) {
        System.out.println("获得商业机会市场来源信息列表");
        PageHelper.startPage(pageNum,pageSize);
        List<BusinessOpportunitySourceSelectVo> list = businessOpportunityMapper.getSourceList();
        PageInfo<BusinessOpportunitySourceSelectVo> pageInfo = new PageInfo<>(list);
        System.out.println("查找成功");
        return pageInfo;
    }

    @Override
    @SystemServiceLog
    public PageInfo<BusinessOpportunityLossReasonSelectVo> selectLossReasonList(Integer pageNum, Integer pageSize) {
        System.out.println("获得商业机会丢失原因信息列表");
        PageHelper.startPage(pageNum,pageSize);
        List<BusinessOpportunityLossReasonSelectVo> list = businessOpportunityMapper.getLossReasonList();
        PageInfo<BusinessOpportunityLossReasonSelectVo> pageInfo = new PageInfo<>(list);
        System.out.println("查找成功");
        return pageInfo;
    }

    @Override
    @SystemServiceLog
    public PageInfo<BusinessOpportunitySourceStatusSelectVo> selectSourceStatusList(Integer pageNum, Integer pageSize) {
        System.out.println("获得商业机会市场来源状态信息列表");
        PageHelper.startPage(pageNum,pageSize);
        List<BusinessOpportunitySourceStatusSelectVo> list = businessOpportunityMapper.getSourceStatusList();
        PageInfo<BusinessOpportunitySourceStatusSelectVo> pageInfo = new PageInfo<>(list);
        System.out.println("查找成功");
        return pageInfo;
    }

    @Override
    @SystemServiceLog
    public PageInfo<BusinessOpportunitySourceTypeSelectVo> selectSourceTypeList(Integer pageNum, Integer pageSize) {
        System.out.println("获得商业机会市场来源类别信息列表");
        PageHelper.startPage(pageNum,pageSize);
        List<BusinessOpportunitySourceTypeSelectVo> list = businessOpportunityMapper.getSourceTypeList();
        PageInfo<BusinessOpportunitySourceTypeSelectVo> pageInfo = new PageInfo<>(list);
        System.out.println("查找成功");
        return pageInfo;
    }

    @Override
    @SystemServiceLog
    public PageInfo<BusinessOpportunitySourceEntity> selectSourceListByKeyWord(String keyword, Integer uid, Integer pageNum, Integer pageSize) {
        System.out.println("开始查询市场来源信息");
        List<DepartmentMemberVO> uidList = departmentService.getIdsByUser(uid);
        if(keyword == null){
            System.out.println("关键字为空");
        }
        PageHelper.startPage(pageNum,pageSize);
        List<BusinessOpportunitySourceEntity> list = businessOpportunityMapper.selectSourceByKeyWord(keyword, uidList);
        PageInfo<BusinessOpportunitySourceEntity> pageInfo = new PageInfo<>(list);
        System.out.println("查询成功");
        return pageInfo;
    }

    @Override
    @SystemServiceLog
    public PageInfo<BusinessOpportunitySelectVo> selectOppListByKeyWord(String keyword, Integer uid, Integer pageNum, Integer pageSize) {
        System.out.println("开始查询含申请信息的商业机会信息");
        List<DepartmentMemberVO> uidList = departmentService.getIdsByUser(uid);
        if(keyword == null){
            System.out.println("关键字为空");
        }
        PageHelper.startPage(pageNum,pageSize);
        List<BusinessOpportunitySelectVo> list = businessOpportunityMapper.selectOppByKeyWord(keyword, uidList);

        for (BusinessOpportunitySelectVo businessOpportunitySelectVo : list) {
            List<BusinessOpportunityApplicationEntity> list1 = businessOpportunityMapper.selectApplicationByOppId(businessOpportunitySelectVo.getBusinessOppId());
            businessOpportunitySelectVo.setApplicationVoList(list1);
        }

        PageInfo<BusinessOpportunitySelectVo> pageInfo = new PageInfo<>(list);
        System.out.println("查询成功");
        return pageInfo;
    }

    @Override
    @SystemServiceLog
    public PageInfo<BusinessRecordSelectVo> selectRecordListByKeyWord(String keyword, Integer uid, Integer pageNum, Integer pageSize) {
        System.out.println("开始查询机会完成记录信息");
        List<DepartmentMemberVO> uidList = departmentService.getIdsByUser(uid);
        if(keyword == null){
            System.out.println("关键字为空");
        }
        PageHelper.startPage(pageNum,pageSize);
        List<BusinessRecordSelectVo> list = businessOpportunityMapper.selectRecordByKeyWord(keyword, uidList);
        PageInfo<BusinessRecordSelectVo> pageInfo = new PageInfo<>(list);
        System.out.println("查询成功");
        return pageInfo;
    }

    @Override
    @SystemServiceLog
    public PageInfo<BusinessOpportunityAccountMoneyVo> selectAccountMoneyListByTime(String startTime, String endTime, Integer businessOppId, Integer pageNum, Integer pageSize) {
        System.out.println("开始查询该商业机会的价格变化");
        PageHelper.startPage(pageNum,pageSize);
        List<BusinessOpportunityAccountMoneyVo> list = businessOpportunityMapper.selectAccountMoneyByTime(startTime, endTime, businessOppId);
        PageInfo<BusinessOpportunityAccountMoneyVo> pageInfo = new PageInfo<>(list);
        System.out.println("查询成功");
        return pageInfo;
    }

//    商业机会金额 #业绩# 图标展示
    @Override
    @SystemServiceLog
    public JSONArray selectAccountM() {
        List<Map<String,Object>> list = businessOpportunityMapper.selectAccountM();
        List<HashMap<String,Object>> returnList = new ArrayList<HashMap<String, Object>>();
        for(Map<String,Object> map : list) {
            Map<String, Object> JSONMap = new HashMap<String, Object>();
            Double mo = ((Double) map.get("sum"))/100000;
            JSONMap.put("money", mo);
            JSONMap.put("month", map.get("month"));
            returnList.add((HashMap<String, Object>) JSONMap);
        }
        JSONArray jsonList = JSONArray.parseArray(JSON.toJSONString(returnList));
//        System.out.println(jsonList);
        return jsonList;
    }
}
