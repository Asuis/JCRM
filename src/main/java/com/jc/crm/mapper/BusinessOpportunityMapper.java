package com.jc.crm.mapper;

import com.jc.crm.form.opportunity.*;
import com.jc.crm.mapper.provider.BusinessOpportunitySqlProvider;
import com.jc.crm.model.*;
import com.jc.crm.service.department.vo.DepartmentMemberVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商业机会数据访问层接口
 * @author currysss 2018-11-23
 * */
@Repository
public interface BusinessOpportunityMapper {

    /**
     * 添加商业机会信息
     * @param businessOpportunityEntity 商业机会表(business_opp)实体类
     * @return int类型的变量
     * */
    @Insert("INSERT INTO business_opp(opp_name, description, opp_stage_id, opp_source_id, " +
            "holder, executor, important_level, roi_analysis_completed, budget_confirmed, is_completed, is_deleted, possibility, next_step, " +
            "ex_1, deadline, ctime, utime, cid) VALUES(#{oppName},#{description},#{oppStageId},#{oppSourceId}," +
            "#{holder},#{executor},#{importantLevel},#{roiAnalysisCompleted},#{budgetConfirmed},#{isCompleted},#{isDeleted},#{possibility},#{nextStep}," +
            "#{ex1},#{deadline},#{ctime},#{utime},#{cid})")
    @Options(useGeneratedKeys = true, keyProperty = "businessOppId")
    int insert(BusinessOpportunityEntity businessOpportunityEntity);

    /**
     * 添加商业机会来源信息
     * @param businessOpportunitySourceEntity 商业机会来源表(business_opp_source)实体类
     * */
    @Insert("INSERT INTO business_opp_source(source_name, description, weight, ex_1, ctime, utime, status_id, " +
            "type_id, sdate, edate, holder, send_count, response_percentage, budget_cost, actual_cost, expected_income) " +
            "VALUES(#{sourceName},#{description},#{weight},#{ex1},#{ctime},#{utime},#{statusId},#{typeId}," +
            "#{sdate},#{edate},#{holder},#{sendCount},#{responsePercentage},#{budgetCost},#{actualCost},#{expectedIncome})")
    void insertSource(BusinessOpportunitySourceEntity businessOpportunitySourceEntity);

    /**
     * 添加商业机会价值信息
     * @param businessOpportunityAccountMoneyEntity 商业机会价值表(business_opp_account_money)实体类
     * */
    @Insert("INSERT INTO business_opp_account_money(business_opp_id, account_money, description, ex_1, ctime, utime) " +
            "VALUES(#{businessOppId},#{accountMoney},#{description},#{ex1},#{ctime},#{utime})")
    @Options(useGeneratedKeys = true, keyProperty = "oppAccountMoneyId")
    void insertAccountMoney(BusinessOpportunityAccountMoneyEntity businessOpportunityAccountMoneyEntity);

    /**
     * 添加商业机会修改申请信息
     * @param businessOpportunityApplicationEntity 商业机会修改申请表(business_opp_application)实体类
     * */
    @Insert("INSERT INTO business_opp_application(business_opp_id, deadline, application_reason, " +
            "account_money, status, ex_1, ctime, utime) VALUES(#{businessOppId},#{deadline}," +
            "#{applicationReason},#{accountMoney},#{status},#{ex1},#{ctime},#{utime})")
    void insertApplication(BusinessOpportunityApplicationEntity businessOpportunityApplicationEntity);

    /**
     * 添加阶段为已赢得的商业机会记录信息
     * @param businessRecordEntity 商业机会完成记录表(business_record)实体类
     * */
    @Insert("INSERT INTO business_record(business_opp_id, description, ex_1, ctime) " +
            "VALUES(#{businessOppId},#{description},#{ex1},#{ctime})")
    void insertRecord(BusinessRecordEntity businessRecordEntity);

    /**
     * 机会所有人修改商业机会信息
     * @param businessOpportunityEntity 市场来源表(business_opp_source)实体类
     * @return int类型的变量
     * */
    @Update("UPDATE business_opp SET opp_name = #{oppName}, description = #{description}, cid = #{cid}, " +
            "opp_source_id = #{oppSourceId}, opp_loss_reason_id = #{oppLossReasonId}, opp_stage_id = #{oppStageId}, " +
            "executor = #{executor}, important_level = #{importantLevel}, roi_analysis_completed = #{roiAnalysisCompleted}, " +
            "budget_confirmed = #{budgetConfirmed}, is_completed = #{isCompleted}, possibility = #{possibility}, " +
            "next_step = #{nextStep}, ex_1 = #{ex1}, deadline = #{deadline}, utime = #{utime} " +
            "WHERE business_opp_id = #{businessOppId} AND holder = #{holder}")
    int update(BusinessOpportunityEntity businessOpportunityEntity);

    /**
     * 机会跟进人修改商业机会信息
     * @param businessOpportunityEntity 市场来源表(business_opp_source)实体类
     * */
    @Update("UPDATE business_opp SET opp_loss_reason_id = #{oppLossReasonId}, opp_stage_id = #{oppStageId}, " +
            "roi_analysis_completed = #{roiAnalysisCompleted}, budget_confirmed = #{budgetConfirmed}, " +
            "is_completed = #{isCompleted}, possibility = #{possibility}, " +
            "next_step = #{nextStep}, ex_1 = #{ex1}, utime = #{utime} " +
            "WHERE business_opp_id = #{businessOppId} AND executor = #{executor} OR holder = #{holder}")
    void updatePartial(BusinessOpportunityEntity businessOpportunityEntity);

    /**
     * 修改商业机会机会截止日期信息
     * @param businessOpportunityEntity 市场来源表(business_opp_source)实体类
     * */
    @Update("UPDATE business_opp SET deadline = #{deadline}, utime = #{utime} WHERE business_opp_id = #{businessOppId}")
    void updateDeadline(BusinessOpportunityEntity businessOpportunityEntity);

    /**
     * 修改市场来源信息
     * @param businessOpportunitySourceEntity 市场来源表(business_opp_source)实体类
     * */
    @Update("UPDATE business_opp_source SET source_name = #{sourceName}, description = #{description}, " +
            "status_id = #{statusId}, type_id = #{typeId}, sdate = #{sdate}, edate = #{edate}, " +
            "send_count = #{sendCount}, response_percentage = #{responsePercentage}, " +
            "budget_cost = #{budgetCost}, actual_cost = #{actualCost}, expected_income = #{expectedIncome}, " +
            "ex_1 = #{ex1}, ex_1 = #{ex1}, utime = #{utime} WHERE opp_source_id = #{oppSourceId} AND holder = #{holder}")
    void updateSource(BusinessOpportunitySourceEntity businessOpportunitySourceEntity);

    /**
     * 修改市场来源权重信息
     * @param businessOpportunitySourceEntity 市场来源表(business_opp_source)实体类
     * */
    @Update("UPDATE business_opp_source SET weight = #{weight}, utime = #{utime} WHERE opp_source_id = #{oppSourceId}")
    void updateWeight(BusinessOpportunitySourceEntity businessOpportunitySourceEntity);

    /**
     * 修改商业机会机会价格信息
     * @param businessOpportunityAccountMoneyEntity 商业机会价值表(business_opp_account_money)实体类

    @Update("UPDATE business_opp_account_money SET account_money = #{accountMoney}, utime = #{utime} WHERE opp_account_money_id = #{oppAccountMoneyId}")
    void updateAccountMoney(BusinessOpportunityAccountMoneyEntity businessOpportunityAccountMoneyEntity);
    */

    /**
     * 修改商业机会状态信息(0为未删除，1为已删除)
     * @param businessOpportunityEntity 商业机会表(business_opp)实体类
     * @return int类型的变量
     * */
    @Update("UPDATE business_opp SET is_deleted = #{isDeleted}, utime = #{utime} WHERE business_opp_id = #{businessOppId} AND holder = #{holder}")
    int delete(BusinessOpportunityEntity businessOpportunityEntity);

    /**
     * 修改商业机会修改申请信息(同意申请)
     * @param businessOpportunityApplicationEntity 商业机会修改申请表(business_opp_application)实体类
     * */
    @Update("UPDATE business_opp_application SET status = #{status}, utime = #{utime} WHERE opp_application_id = #{oppApplicationId}")
    void agree(BusinessOpportunityApplicationEntity businessOpportunityApplicationEntity);

    /**
     * 修改商业机会修改申请信息(拒绝申请)
     * @param businessOpportunityApplicationEntity 商业机会修改申请表(business_opp_application)实体类
     * */
    @Update("UPDATE business_opp_application SET status = #{status}, rejection_reason = #{rejectionReason}, utime = #{utime} WHERE opp_application_id = #{oppApplicationId}")
    void reject(BusinessOpportunityApplicationEntity businessOpportunityApplicationEntity);

    /**
     * 删除状态为赢得的商业机会的重复信息
     * @param businessRecordEntity 商业机会赢得记录表(business_record)实体类
     * */
    @Delete("DELETE FROM business_record WHERE business_opp_id = #{businessOppId}")
    void deleteRecord(BusinessRecordEntity businessRecordEntity);

    /**
     * 根据商业机会ID查询商业机会列表信息
     * @param businessOppId 商业机会ID
     * @return BusinessOpportunityEntity对象
     * */
    @Select("SELECT * FROM business_opp WHERE business_opp_id = #{businessOppId}")
    BusinessOpportunityEntity selectByBusinessOppId(Integer businessOppId);

    /**
     * 根据商业机会名称查询商业机会列表信息
     * @param oppName 商业机会名称
     * @return BusinessOpportunityEntity对象
     * */
    @Select("SELECT * FROM business_opp WHERE opp_name = #{oppName}")
    BusinessOpportunityEntity selectByOppName(String oppName);

    /**
     * 根据市场来源ID查询市场来源列表信息
     * @param oppSourceId 市场来源ID
     * @return BusinessOpportunitySourceEntity对象
     * */
    @Select("SELECT * FROM business_opp_source WHERE opp_source_id = #{oppSourceId}")
    BusinessOpportunitySourceEntity selectBySourceId(Integer oppSourceId);

    /**
     * 根据市场来源名称查询市场来源列表信息
     * @param sourceName 市场来源名称
     * @return BusinessOpportunitySourceEntity对象
     * */
    @Select("SELECT * FROM business_opp_source WHERE source_name = #{sourceName}")
    BusinessOpportunitySourceEntity selectBySourceName(String sourceName);

    /**
     * 根据机会阶段ID查询机会阶段列表信息
     * @param oppStageId 阶段ID
     * @return BusinessOpportunityStageEntity对象
     * */
    @Select("SELECT * FROM business_opp_stage WHERE opp_stage_id = #{oppStageId}")
    BusinessOpportunityStageEntity selectByStageId(Integer oppStageId);

    /**
     * 根据机会丢失原因ID查询丢失原因列表信息
     * @param oppLossReasonId 丢失原因ID
     * @return BusinessOpportunityLossReasonEntity对象
     * */
    @Select("SELECT * FROM business_opp_loss_reason WHERE opp_loss_reason_id = #{oppLossReasonId}")
    BusinessOpportunityLossReasonEntity selectByLossReasonId(Integer oppLossReasonId);

    /**
     * 根据市场来源的状态ID查询状态列表信息
     * @param statusId 状态ID
     * @return BusinessOpportunitySourceStatusEntity对象
     * */
    @Select("SELECT * FROM business_opp_source_status WHERE status_id = #{statusId}")
    BusinessOpportunitySourceStatusEntity selectBySourceStatusId(Integer statusId);

    /**
     * 根据市场来源的类别ID查询类别列表信息
     * @param typeId 类别ID
     * @return BusinessOpportunitySourceTypeEntity对象
     * */
    @Select("SELECT * FROM business_opp_source_type WHERE type_id = #{typeId}")
    BusinessOpportunitySourceTypeEntity selectBySourceTypeId(Integer typeId);

    /**
     * 根据修改申请ID查询修改申请列表信息
     * @param oppApplicationId 申请ID
     * @return BusinessOpportunitySourceTypeEntity对象
     * */
    @Select("SELECT * FROM business_opp_application WHERE opp_application_id = #{oppApplicationId}")
    BusinessOpportunityApplicationEntity selectByApplicationId(Integer oppApplicationId);

    /**
     * 根据修改申请ID和机会ID查询修改申请列表信息
     * @param oppApplicationId 申请ID
     * @param businessOppId 机会ID
     * @return BusinessOpportunitySourceTypeEntity对象
     * */
    @Select("SELECT * FROM business_opp_application WHERE opp_application_id = #{oppApplicationId} AND business_opp_id = #{businessOppId}")
    BusinessOpportunityApplicationEntity selectByBothId(Integer oppApplicationId, Integer businessOppId);

    /**
     * 根据机会ID查询阶段为赢得的记录列表信息
     * @param businessOppId 机会ID
     * @return BusinessRecordEntity对象
     * */
    @Select("SELECT * FROM business_record WHERE business_opp_id = #{businessOppId}")
    BusinessRecordEntity selectRecordByOppId(Integer businessOppId);

    /**
     * 根据商业机会ID查询商业机会价值列表信息
     * @param businessOppId 商业机会ID
     * @param accountMoney 商业机会价值
     * @return BusinessOpportunityAccountMoneyEntity对象
     * */
    @Select("SELECT * FROM business_opp_account_money WHERE business_opp_id = #{businessOppId} AND account_money = #{accountMoney}")
    BusinessOpportunityAccountMoneyEntity selectAccountMoneyByBusinessOppId(Integer businessOppId, String accountMoney);

    /**
     * 查询商业机会阶段列表信息
     * @return BusinessOpportunityStageSelectVo类的泛型集合
     * */
    @Select("SELECT * FROM business_opp_stage")
    List<BusinessOpportunityStageSelectVo> getStageList();

    /**
     * 查询商业机会市场来源列表信息
     * @return BusinessOpportunitySourceSelectVo类的泛型集合
     * */
    @Select("SELECT * FROM business_opp_source ORDER BY weight DESC")
    List<BusinessOpportunitySourceSelectVo> getSourceList();

    /**
     * 查询商业机会丢失原因列表信息
     * @return BusinessOpportunityLossReasonSelectVo类的泛型集合
     * */
    @Select("SELECT * FROM business_opp_loss_reason")
    List<BusinessOpportunityLossReasonSelectVo> getLossReasonList();

    /**
     * 查询商业机会市场来源状态列表信息
     * @return BusinessOpportunitySourceStatusSelectVo类的泛型集合
     * */
    @Select("SELECT * FROM business_opp_source_status")
    List<BusinessOpportunitySourceStatusSelectVo> getSourceStatusList();

    /**
     * 查询商业机会市场来源类别列表信息
     * @return BusinessOpportunitySourceTypeSelectVo类的泛型集合
     * */
    @Select("SELECT * FROM business_opp_source_type")
    List<BusinessOpportunitySourceTypeSelectVo> getSourceTypeList();

    /**
     * 根据商业机会ID查询该商业机会含有的申请列表信息
     * @param businessOppId 商业机会ID
     * @return BusinessOpportunityApplicationEntity类的泛型集合
     * */
    @Select("SELECT * FROM business_opp_application WHERE business_opp_id = #{businessOppId}")
    List<BusinessOpportunityApplicationEntity> selectApplicationByOppId(Integer businessOppId);

    /**
     * 根据关键字和登录的用户ID多表关联动态模糊查询市场来源列表信息
     * @param keyword 关键字
     * @param uidList 有权限的用户ID列表
     * @return BusinessOpportunitySourceEntity类的泛型集合
     * */
    @SelectProvider(type = BusinessOpportunitySqlProvider.class, method = "queryListOne")
    List<BusinessOpportunitySourceEntity> selectSourceByKeyWord(@Param("keyword") String keyword, @Param("uidList")List<DepartmentMemberVO> uidList);

    /**
     * 根据关键字和登录的用户ID多表关联动态模糊查询喊申请信息的商业机会列表信息
     * @param keyword 关键字
     * @param uidList 有权限的用户ID列表
     * @return BusinessOpportunitySelectVo类的泛型集合
     * */
    @SelectProvider(type = BusinessOpportunitySqlProvider.class, method = "queryListTwo")
    List<BusinessOpportunitySelectVo> selectOppByKeyWord(@Param("keyword") String keyword, @Param("uidList")List<DepartmentMemberVO> uidList);

    /**
     * 根据关键字和登录的用户ID多表关联动态模糊查询阶段为赢得的的商业机会列表信息
     * @param keyword 关键字
     * @param uidList 有权限的用户ID列表
     * @return BusinessRecordEntity类的泛型集合
     * */
    @SelectProvider(type = BusinessOpportunitySqlProvider.class, method = "queryListThree")
    List<BusinessRecordSelectVo> selectRecordByKeyWord(@Param("keyword") String keyword, @Param("uidList")List<DepartmentMemberVO> uidList);

    /**
     * 根据商业机会ID和输入的时间段获得机会价值变化图表
     * @param startTime 开始时间
     * @param endTime 截止时间
     * @param businessOppId 商业机会ID
     * @return BusinessRecordEntity类的泛型集合
     * */

    @Select("SELECT * FROM business_opp_account_money WHERE (LEFT(ctime,10) BETWEEN  #{startTime} AND  #{endTime}) AND business_opp_id = #{businessOppId}")
    List<BusinessOpportunityAccountMoneyVo> selectAccountMoneyByTime(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("businessOppId")Integer businessOppId);


}
