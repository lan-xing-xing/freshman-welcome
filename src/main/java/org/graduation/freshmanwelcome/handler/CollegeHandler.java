package org.graduation.freshmanwelcome.handler;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.graduation.freshmanwelcome.common.Financial;
import org.graduation.freshmanwelcome.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CollegeHandler {

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation("获取全部学院信息")
    @GetMapping(value = "/getAllCollege", produces = "application/json; charset=utf-8")
    public Object getAllCollege(){
        Gson gson = new Gson();
        return gson.toJson(collegeService.getAllCollege());
    }

    @ApiOperation("获取学院全部学生基本信息(分页展示)")
    @GetMapping(value = "/queryStudentBasicByCollege/{collegeId}/{pageNum}/{pageSize}")
    public Object queryStudentBasicByCollege(@PathVariable(name = "collegeId") Integer collegeId,
                                             @PathVariable(name = "pageNum")Integer pageNum,
                                             @PathVariable(name = "pageSize")Integer pageSize){
        return collegeService.queryStudenBasicByCollege(collegeId,pageNum,pageSize);
    }

    @ApiOperation("获取学院全部学生的详细信息（分页展示）")
    @GetMapping(value = "/queryStudentDetailsByCollege/{collegeId}/{pageNum}/{pageSize}")
    public Object queryStudentDetailsByCollege(@PathVariable(name = "collegeId") Integer collegeId,
                                             @PathVariable(name = "pageNum")Integer pageNum,
                                             @PathVariable(name = "pageSize")Integer pageSize){
        return collegeService.queryStudentDetailsByCollege(collegeId,pageNum,pageSize);
    }

    @ApiOperation("根据学生某属性获取该学院一个或者多个学生信息（分页展示）")
    @GetMapping(value = "/{collegeId}/queryStudentBasicByInfo/{key}/{value}/{pageNum}/{pageSize}")
    public Object queryStudentBasicByInfo(@PathVariable(name = "collegeId")String collegeId,
                                          @PathVariable(name = "key")String key,
                                          @PathVariable(name = "value") Object value,
                                          @PathVariable(name = "pageNum") Integer pageNum,
                                          @PathVariable(name = "pageSize") Integer pageSize){
        return collegeService.queryStudentBasicByInfo(collegeId,key,value,pageNum,pageSize);
    }

    @ApiOperation("获取报到人数")
    @GetMapping(value = "getCollegePayNumber/{collegeId}",produces = "application/json; charset=utf-8")
    public Object getCollegePayNumber(@PathVariable("collegeId") Integer collegeId){
        String path = Financial.url + "getCollegePayNumber/" + collegeId;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(path,String.class);
        return responseEntity.getBody();
    }
}
