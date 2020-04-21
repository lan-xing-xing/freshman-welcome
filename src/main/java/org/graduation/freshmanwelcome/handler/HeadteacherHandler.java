package org.graduation.freshmanwelcome.handler;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.graduation.freshmanwelcome.entity.Headteacher;
import org.graduation.freshmanwelcome.service.HeadteacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HeadteacherHandler {

    @Autowired
    private HeadteacherService headteacherService;

    @ApiOperation("根据学院id和专业id获取所有老师信息")
    @GetMapping(value = "/queryMajorHeadteacher/{collegeId}/{majorId}", produces = "application/json; charset=utf-8")
    public Object queryMajorHeadteacher(@PathVariable(name = "collegeId") Integer collegeId,
                                        @PathVariable(name = "majorId") Integer majorId){
        Gson gson = new Gson();
        return gson.toJson(headteacherService.queryMajorHeadteacher(collegeId,majorId));
    }

    @ApiOperation("修改班主任信息")
    @PostMapping("/updateHeadteacherInfo")
    public Object updateHeadteacherInfo(@RequestBody Headteacher headteacher){
        return headteacherService.updateHeadteacherInfo(headteacher);
    }

}
