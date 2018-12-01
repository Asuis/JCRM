package com.jc.crm.controller;

import com.jc.crm.config.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author asuis
 */
@Api
@RestController
@RequestMapping("api/opp")
public class BusinessOpportunityController {
    @PostMapping
    public Result create() {
        return null;
    }
    @PutMapping
    public Result update() {
        return null;
    }
    @GetMapping
    public Result get() {
        return null;
    }
}
