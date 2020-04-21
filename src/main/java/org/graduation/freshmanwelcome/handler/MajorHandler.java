package org.graduation.freshmanwelcome.handler;

import io.swagger.annotations.ApiOperation;
import org.graduation.freshmanwelcome.DTO.DeductDTO;
import org.graduation.freshmanwelcome.DTO.PayStatusDTO;
import org.graduation.freshmanwelcome.DTO.ResultDTO;
import org.graduation.freshmanwelcome.common.Financial;
import org.graduation.freshmanwelcome.exception.CustomizeErrorCode;
import org.graduation.freshmanwelcome.service.MajorService;
import org.graduation.freshmanwelcome.service.StudentAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MajorHandler {

    @Autowired
    private MajorService majorService;

    @Autowired
    private StudentAdminService studentAdminService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation("获取专业全部学生基本信息（分页展示）")
    @GetMapping("/queryStudentBasicByMajor/{collegeId}/{majorId}/{pageNum}/{pageSize}")
    public Object queryStudentBasicByMajor(@PathVariable(name = "collegeId")Integer collegeId,
                                           @PathVariable(name = "majorId")Integer majorId,
                                           @PathVariable(name = "pageNum")Integer pageNum,
                                           @PathVariable(name = "pageSize")Integer pageSize){
        return majorService.queryStudentBasicByMajor(collegeId,majorId,pageNum,pageSize);
    }

    @ApiOperation("分页获取某学院某专业所有学生（分页展示）")
    @GetMapping(value = "/queryStudentDetailsByMajor/{collegeId}/{majorId}/{pageNum}/{pageSize}")
    public Object queryStudentByMajor(@PathVariable(name = "collegeId")Integer collegeId,
                                      @PathVariable(name = "majorId")Integer majorId,
                                      @PathVariable(name = "pageNum") Integer pageNum,
                                      @PathVariable(name = "pageSize") Integer pageSize){
        return majorService.queryStudentDetailsByMajor(collegeId,majorId, pageNum, pageSize);
    }

    @ApiOperation("根据学生某属性获取该专业一个或者多个学生信息（分页展示）")
    @GetMapping(value = "/{collegeId}/{majorId}/queryStudentBasicByInfo/{key}/{value}/{pageNum}/{pageSize}")
    public Object queryStudentBasicByInfo(@PathVariable(name = "collegeId")Integer collegeId,
                                          @PathVariable(name = "majorId")Integer majorId,
                                          @PathVariable(name = "key")String key,
                                          @PathVariable(name = "value") Object value,
                                          @PathVariable(name = "pageNum") Integer pageNum,
                                          @PathVariable(name = "pageSize") Integer pageSize){
        return majorService.queryStudentBasicByInfo(collegeId,majorId,key,value,pageNum,pageSize);
    }

    @ApiOperation("获取学生的缴费状况")
    @GetMapping(value = "getStudentPayStatus/{studentId}",produces = "application/json; charset=utf-8")
    public Object getStudentPayStatus(@PathVariable("studentId") Long studentId){
        String path = Financial.url + "getStudentPayStatus/" + studentId;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(path,String.class);
        return responseEntity.getBody();
    }

    @ApiOperation("根据专业和某一信息获取学生的缴费状况")
    @GetMapping(value = "getStudentPayStatusByInfo/{collegeId}/{majorId}/{info}",produces = "application/json; charset=utf-8")
    public Object getStudentPayStatusByInfo(@PathVariable("collegeId") Integer collegeId,
                                      @PathVariable("majorId") Integer majorId,
                                      @PathVariable("info") String info){
        String path = Financial.url + "getStudentPayStatusByInfo/" + collegeId + '/' + majorId + '/' + info;
        System.out.println(path);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(path,String.class);
        return responseEntity.getBody();
    }

    @ApiOperation("学生缴费")
    @PostMapping(value = "postStudentPay",produces = "application/json; charset=utf-8")
    public Object postStudentPay(@RequestBody PayStatusDTO payStatusDTO){
        //修改已缴金额
        String path = Financial.url + "updateStudentPayStatus";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(path,payStatusDTO,String.class);
        if (responseEntity.getBody().contains("20000")){
            //修改报到状态
            if (studentAdminService.updateStudentPayStatus(payStatusDTO.getStudentId()).toString().contains("20000")){
                return ResultDTO.okOf();
            }else {
                return ResultDTO.errorOf(CustomizeErrorCode.UPDATE_EXCEPTION);
            }
        }else {
            return ResultDTO.errorOf(CustomizeErrorCode.UPDATE_EXCEPTION);
        }
    }

    @ApiOperation("获取报到人数")
    @GetMapping(value = "getMajorPayNumber/{collegeId}/{majorId}",produces = "application/json; charset=utf-8")
    public Object getMajorPayNumber(@PathVariable("collegeId") Integer collegeId,
                                      @PathVariable("majorId") Integer majorId){
        String path = Financial.url + "getMajorPayNumber/" + collegeId + "/" + majorId;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(path,String.class);
        return responseEntity.getBody();
    }
}
