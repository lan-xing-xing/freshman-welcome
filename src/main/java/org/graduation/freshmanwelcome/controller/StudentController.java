package org.graduation.freshmanwelcome.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.graduation.freshmanwelcome.DTO.PayStatusDTO;
import org.graduation.freshmanwelcome.DTO.StudentInfoDTO;
import org.graduation.freshmanwelcome.DTO.StudentLoginDTO;
import org.graduation.freshmanwelcome.common.Financial;
import org.graduation.freshmanwelcome.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation("学生登录")
    @RequestMapping(value = "login",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public Object login(@RequestBody StudentLoginDTO studentLoginDTO){
        Gson gson = new Gson();
        return gson.toJson(studentService.studentLogin(studentLoginDTO));
    }

    @ApiOperation("获取学生信息")
    @GetMapping(value = "getinfo/{studentId}",produces = "application/json; charset=utf-8")
    public Object getStudent(@PathVariable(name = "studentId")Long studentId){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        return gson.toJson(studentService.queryStudentById(studentId));
    }

    @ApiOperation("学生提交基本信息")
    @PostMapping(value = "postStudentInfo",produces = "application/json; charset=utf-8")
    public Object postStudentInfo(@RequestBody StudentInfoDTO studentInfoDTO){
        Gson gson = new Gson();
        return gson.toJson(studentService.updateStudent(studentInfoDTO));
    }

    @ApiOperation("学生是否填写信息,1为已填，0为未填")
    @GetMapping(value = "getInfoStatus/{studentId}",produces = "application/json; charset=utf-8")
    public Object getInfoStatus(@PathVariable(name = "studentId")Long studentId){
        Gson gson = new Gson();
        return gson.toJson(studentService.getInfoStatus(studentId));
    }

    @ApiOperation("学生是否缴费,1为已缴，0为未缴")
    @GetMapping(value = "getPayStatus/{studentId}",produces = "application/json; charset=utf-8")
    public Object getPayStatus(@PathVariable(name = "studentId")Long studentId){
        Gson gson = new Gson();
        return gson.toJson(studentService.getPayStatus(studentId));
    }

    @ApiOperation("学生是否注册,1为已注册，0为未注册")
    @GetMapping(value = "getRegStatus/{studentId}",produces = "application/json; charset=utf-8")
    public Object getRegStatus(@PathVariable(name = "studentId")Long studentId){
        Gson gson = new Gson();
        return gson.toJson(studentService.getRegStatus(studentId));
    }

    @ApiOperation("获取学生的缴费情况")
    @GetMapping(value = "getStudentPayStatus/{studentId}",produces = "application/json; charset=utf-8")
    public Object getStudentPayStatus(@PathVariable("studentId") Long studentId){
        String path = Financial.url + "getStudentPayStatus/" + studentId;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(path,String.class);
        return responseEntity.getBody();
    }

    @ApiOperation("学生缴费")
    @PostMapping(value = "postStudentPay",produces = "application/json; charset=utf-8")
    public Object postStudentPay(@RequestBody PayStatusDTO payStatusDTO){
        String path = Financial.url + "updateStudentPayStatus";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(path,payStatusDTO,String.class);
        return responseEntity;
    }
}
