package com.jc.crm.controller;

import com.github.pagehelper.PageInfo;
import com.jc.crm.config.Result;
import com.jc.crm.config.ResultStatus;
import com.jc.crm.form.competitors.CompetitorsDeleteForm;
import com.jc.crm.form.competitors.CompetitorsInsertForm;
import com.jc.crm.form.competitors.CompetitorsUpdateForm;
import com.jc.crm.model.CompetitorsEntity;
import com.jc.crm.service.competitors.CompetitorsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("competitors")
public class CompetitorsController {

    @Autowired
    private CompetitorsServiceImpl competitorsService;

    @ApiOperation(value = "添加竞争对手", response = Result.class)
    @PostMapping
    public Result AddCompetitors(@Validated @RequestBody CompetitorsInsertForm competitorsInsertForm,
                                 BindingResult result, @RequestAttribute Integer uid){
        int flag = 0;
        if (result.hasErrors()){
            return Result.fail(ResultStatus.FAIL,result.getFieldError().toString());
        }
        try{
            flag = competitorsService.addCompetitors(competitorsInsertForm,uid);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION,"发生异常：e.getMessage()");
        }
        if(flag == 1){
            return Result.fail(ResultStatus.SUCCESS,"添加成功");
        }
        if(flag == 2){
            return Result.fail(ResultStatus.EXISTED,"该条信息已存在");
        }else{
            return Result.fail(ResultStatus.FAIL,"失败");
        }
    }

    @ApiOperation(value = "修改竞争对手", response = Result.class)
    @PutMapping
    public Result UpdateCompetitors(@Validated @RequestBody CompetitorsUpdateForm competitorsUpdateForm,
                                    BindingResult result){
        int flag = 0;
        if (result.hasErrors()){
            return Result.fail(ResultStatus.FAIL,result.getFieldError().toString());
        }
        try{
            flag = competitorsService.updateCompetitors(competitorsUpdateForm);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION,"发生异常：e.getMessage()");
        }
        if(flag == 1){
            return Result.fail(ResultStatus.SUCCESS,"修改成功");
        }
        if(flag == 2){
            return Result.fail(ResultStatus.NOT_FOUND,"该竞争对手不存在");
        }if(flag == 3){
            return Result.fail(ResultStatus.CONFLICT,"该公司名称已存在");
        }else{
            return Result.fail(ResultStatus.FAIL,"失败");
        }
    }

    @ApiOperation(value = "删除竞争对手", response = Result.class)
    @PutMapping("delete")
    public Result DeleteCompetitors(@Validated @RequestBody CompetitorsDeleteForm competitorsDeleteForm){
        int flag = 0;
        try{
            flag = competitorsService.deleteCompetitors(competitorsDeleteForm);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION,"发生异常：e.getMessage()");
        }
        if(flag == 1){
            return Result.fail(ResultStatus.SUCCESS,"修改成功");
        }
        if(flag == 2){
            return Result.fail(ResultStatus.NOT_FOUND,"该竞争对手不存在");
        }if(flag == 3){
            return Result.fail(ResultStatus.ERRO_FORMAT,"传输的数据格式错误");
        }else{
            return Result.fail(ResultStatus.FAIL,"失败");
        }
    }

    @ApiOperation(value = "根据关键字查询竞争对手信息", response = Result.class)
    @GetMapping
    public Result SearchCompetitors(@RequestParam(value="keyword",required = false)String keyword ,
                                    @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        PageInfo<CompetitorsEntity> pageInfo = new PageInfo<CompetitorsEntity>();
        try{
            pageInfo = competitorsService.selectListByKeyWord(keyword, pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION,"发生异常：e.getMessage()");
        }
        if(pageInfo != null){
            return Result.fail(ResultStatus.SUCCESS,"成功获取查询列表");
        }
        if(pageInfo == null){
            return Result.fail(ResultStatus.NOT_INFO,"查询信息为空");
        }else{
            return Result.fail(ResultStatus.FAIL,"失败");
        }
    }

    /*@ApiOperation(value = "查询自己挖掘的竞争对手信息", response = Result.class)
    @GetMapping
    public Result SearchCompetitors(@RequestParam(value="holder",required = false)String keyword ,
                                    @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        PageInfo<CompetitorsEntity> pageInfo = new PageInfo<CompetitorsEntity>();
        try{
            pageInfo = competitorsService.selectListByKeyWord(keyword, pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION,"发生异常：e.getMessage()");
        }
        if(pageInfo != null){
            return Result.fail(ResultStatus.SUCCESS,"成功获取查询列表");
        }
        if(pageInfo == null){
            return Result.fail(ResultStatus.NOT_INFO,"查询信息为空");
        }else{
            return Result.fail(ResultStatus.FAIL,"失败");
        }
    }

    @ApiOperation(value = "根据关键字查询竞争对手信息", response = Result.class)
    @GetMapping
    public Result SearchCompetitors(@RequestParam(value="keyword",required = false)String keyword ,
                                    @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        PageInfo<CompetitorsEntity> pageInfo = new PageInfo<CompetitorsEntity>();
        try{
            pageInfo = competitorsService.selectListByKeyWord(keyword, pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResultStatus.EXCEPTION,"发生异常：e.getMessage()");
        }
        if(pageInfo != null){
            return Result.fail(ResultStatus.SUCCESS,"成功获取查询列表");
        }
        if(pageInfo == null){
            return Result.fail(ResultStatus.NOT_INFO,"查询信息为空");
        }else{
            return Result.fail(ResultStatus.FAIL,"失败");
        }
    }*/
}
