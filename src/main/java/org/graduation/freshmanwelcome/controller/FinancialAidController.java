package org.graduation.freshmanwelcome.controller;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.graduation.freshmanwelcome.DTO.GreenInfoDTO;
import org.graduation.freshmanwelcome.service.FinancialAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FinancialAidController {

    @Autowired
    private FinancialAidService financialAidService;

    @ApiOperation("学生提交绿色通道信息")
    @PostMapping(value = "postGreenInfo",produces = "application/json; charset=utf-8")
    public Object postGreenInfo(@RequestBody GreenInfoDTO greenInfoDTO){
        Gson gson = new Gson();
        return gson.toJson(financialAidService.postGreenInfo(greenInfoDTO));
    }

    @ApiOperation("贷款审核状态，0为未审核,1为审核失败，2为审核成功")
    @GetMapping(value = "getFinancialAidStatus/{studentId}",produces = "application/json; charset=utf-8")
    public Object getFinancialAidStatus(@PathVariable("studentId") Long studentId){
        return financialAidService.getFinancialAidSatus(studentId);
    }
}
