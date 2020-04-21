package org.graduation.freshmanwelcome.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiOperation;
import org.graduation.freshmanwelcome.DTO.PostRegisterDTO;
import org.graduation.freshmanwelcome.DTO.RealTimeDataDTO;
import org.graduation.freshmanwelcome.common.Financial;
import org.graduation.freshmanwelcome.service.StudentAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class StudentHandler {

    @Autowired
    private StudentAdminService studentAdminService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation("获取所有学生基本信息（分页）")
    @GetMapping(value = "/queryAllStudentBasic/{pageNum}/{pageSize}",produces = "application/json; charset=utf-8")
    public Object queryAllStudentBasic(@PathVariable(name = "pageNum") Integer pageNum,
                                  @PathVariable(name = "pageSize") Integer pageSize){
        return studentAdminService.queryAllStudentBasic(pageNum,pageSize);
    }

    @ApiOperation("获取某个学生详细信息")
    @GetMapping(value = "getDatils/{studentId}", produces = "application/json; charset=utf-8")
    public Object getStudent(@PathVariable(name = "studentId")Long studentId){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        return gson.toJson(studentAdminService.queryStudentDetailsById(studentId));
    }

    @ApiOperation("获取所有学生详细信息（分页）")
    @GetMapping(value = "/queryAllStudentDetails/{pageNum}/{pageSize}",produces = "application/json; charset=utf-8")
    public Object queryAllStudentDetails(@PathVariable(name = "pageNum") Integer pageNum,
                                  @PathVariable(name = "pageSize") Integer pageSize){
        return studentAdminService.queryAllStudentDetails(pageNum,pageSize);
    }

    @ApiOperation("根据某学生的属性查询学生(分页)")
    @GetMapping(value = "/queryStudentByInfo/{key}/{value}/{pageNum}/{pageSize}",produces = "application/json; charset=utf-8")
    public Object queryStudentByInfo(@PathVariable(name = "key")String key,
                                     @PathVariable(name = "value") Object value,
                                     @PathVariable(name = "pageNum") Integer pageNum,
                                     @PathVariable(name = "pageSize") Integer pageSize){
        return studentAdminService.queryStudentByInfo(key,value,pageNum,pageSize);
    }

    @ApiOperation("获取报到人数")
    @GetMapping(value = "getSchoolPayNumber",produces = "application/json; charset=utf-8")
    public Object getSchoolPayNumber(){
        String path = Financial.url + "getSchoolPayNumber";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(path,String.class);
        return responseEntity.getBody();
    }

    @ApiOperation("根据专业和学号、姓名、身份证号任意一项查询")
    @GetMapping(value = "/{collegeId}/{majorId}/getStudentRegister/{info}",produces = "application/json; charset=utf-8")
    public Object getStudentRegister(@PathVariable("collegeId") Integer collegeId,
                                     @PathVariable("majorId") Integer majorId,
                                     @PathVariable("info") String info){
        return studentAdminService.queryStudentRegister(collegeId,majorId,info);
    }

    @ApiOperation("修改学生报到状态")
    @GetMapping("/updateStudentPayStatus/{studentId}")
    public Object updateStudentPayStatus(@PathVariable("studentId") Long studentId){
        return studentAdminService.updateStudentPayStatus(studentId);
    }

    @ApiOperation("修改学生注册状态")
    @PostMapping("/updateStudentRegisterStatus")
    public Object updateStudentRegisterStatus(@RequestBody PostRegisterDTO postRegisterDTO){
        return studentAdminService.updateStudentRegisterStatus(postRegisterDTO);
    }
}
