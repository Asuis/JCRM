package com.jc.crm.competitor;

import com.github.pagehelper.PageInfo;
import com.jc.crm.form.competitors.CompetitorsSelectVo;
import com.jc.crm.service.competitors.CompetitorsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CompetitorTests {
    private final static Logger logger = LoggerFactory.getLogger(CompetitorTests.class);
    @Autowired
    CompetitorsService competitorsService;
    @Test
    public void test() {
        try {
            PageInfo<CompetitorsSelectVo> pageInfo = competitorsService.selectListByKeyWord("", 28, 1,5);
            logger.info(pageInfo.toString());
            logger.info(pageInfo.getTotal()+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
