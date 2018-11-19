package com.jc.crm.competitor;

import com.github.pagehelper.PageInfo;
import com.jc.crm.model.CompetitorsEntity;
import com.jc.crm.service.competitors.CompetitorsService;
import lombok.extern.java.Log;
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
            PageInfo<CompetitorsEntity> pageInfo = competitorsService.selectListByKeyWord("华", 1, 5);
            logger.info(pageInfo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
