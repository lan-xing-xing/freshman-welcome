package org.graduation.freshmanwelcome.handler;

import io.swagger.annotations.ApiOperation;
import org.graduation.freshmanwelcome.DTO.RealTimeDataDTO;
import org.graduation.freshmanwelcome.DTO.ResultDTO;
import org.graduation.freshmanwelcome.service.CollegeService;
import org.graduation.freshmanwelcome.service.MajorService;
import org.graduation.freshmanwelcome.service.StudentAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class RealTimeDataHandler {

    @Autowired
    private StudentAdminService studentAdminService;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private MajorService majorService;

    @ApiOperation("实时获取报到率和注册率")
    @GetMapping("/getRealTimeData/{path}")
    public Object getRealTimeData(@PathVariable("path") String path) {
        RealTimeDataDTO realTimeDataDTO = new RealTimeDataDTO();
        if (!path.equals("school")){
            //学院
            Integer collegeId = Integer.parseInt(path);
            realTimeDataDTO = collegeService.getRealTimeData(collegeId);
        }else {
            //全校
            realTimeDataDTO = studentAdminService.getRealTimeData();
        }
        return new ResultDTO(20000,getRealTimeDataDTo(realTimeDataDTO));
    }

    @ApiOperation("实时获取专业报到率和注册率")
    @GetMapping("/getRealTimeData/{collegeId}/{majorId}")
    public Object getRealTimeData(@PathVariable("collegeId") Integer collegeId,
                                  @PathVariable("majorId")Integer majorId){
        RealTimeDataDTO realTimeDataDTO = majorService.getRealTimeData(collegeId,majorId);
        return new ResultDTO(20000,getRealTimeDataDTo(realTimeDataDTO));
    }

    public static RealTimeDataDTO getRealTimeDataDTo(RealTimeDataDTO realTimeDataDTO){
        Integer totalNum = realTimeDataDTO.getTotalNum();
        Integer payNum = realTimeDataDTO.getPayNum();
        Integer registerNum = realTimeDataDTO.getRegisterNum();
        double payRate = new BigDecimal((float)payNum/totalNum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        double registerRate = new BigDecimal((float)registerNum/totalNum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        realTimeDataDTO.setPayRate(payRate);
        realTimeDataDTO.setRegisterRate(registerRate);
        return realTimeDataDTO;
    }
}
