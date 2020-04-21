package org.graduation.freshmanwelcome.handler;

import io.swagger.annotations.ApiOperation;
import org.graduation.freshmanwelcome.service.StuClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StuClassHandler {

    @Autowired
    private StuClassService stuClassService;

    @ApiOperation("获取某专业的班级信息")
    @GetMapping(value = "/queryClassInfo/{collegeId}/{majorId}")
    public Object queryClassInfo(@PathVariable("collegeId") Integer collegeId,
                                 @PathVariable("majorId") Integer majorId){
        return stuClassService.queryClassInfo(collegeId,majorId);
    }
}
