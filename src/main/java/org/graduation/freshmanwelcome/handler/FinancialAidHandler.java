package org.graduation.freshmanwelcome.handler;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.graduation.freshmanwelcome.DTO.DeductDTO;
import org.graduation.freshmanwelcome.service.FinancialAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class FinancialAidHandler {

    @Autowired
    private FinancialAidService financialAidService;

    @ApiOperation("获取某专业全部贷款信息（分页）")
    @GetMapping(value = "/queryMajorFinancialAid/{collegeId}/{majorId}/{pageNum}/{pageSize}", produces = "application/json; charset=utf-8")
    public Object queryMajorFinancialAid(@PathVariable("collegeId") Integer collegeId,
                                         @PathVariable("majorId") Integer majorId,
                                         @PathVariable("pageNum") Integer pageNum,
                                         @PathVariable("pageSize") Integer pageSize){
        Gson gson = new Gson();
        return gson.toJson(financialAidService.queryMajorFinancialAid(collegeId,majorId,pageNum,pageSize));
    }

    @ApiOperation("获取审核材料")
    @GetMapping(value = "/queryCheckInfo/{studentId}",produces = MediaType.IMAGE_JPEG_VALUE)
    public Object queryCheckInfo(@PathVariable("studentId") Long studentId) throws Exception {
        return financialAidService.queryCheckInfo(studentId);
    }

    @ApiOperation("贷款审核通过")
    @PostMapping(value = "checkLoanSuccess",produces = "application/json; charset=utf-8")
    public Object checkLoanSuccess(@RequestBody DeductDTO deductDTO){
        return financialAidService.checkLoanSuccess(deductDTO);
    }

    @ApiOperation("贷款审核未通过")
    @GetMapping(value = "checkLoanFail/{studentId}",produces = "application/json; charset=utf-8")
    public Object checkLoanFail(@PathVariable("studentId") Long studentId){
        return financialAidService.checkLoanFail(studentId);
    }
}
