package org.graduation.freshmanwelcome.handler;

import io.swagger.annotations.ApiOperation;
import org.graduation.freshmanwelcome.DTO.StudentToRoomDTO;
import org.graduation.freshmanwelcome.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomHandler {

    @Autowired
    private RoomService roomService;

    @ApiOperation("查询专业可分配的房间")
    @GetMapping("/queryRoom/{collegeId}/{majorId}")
    public Object queryRoom(@PathVariable("collegeId") Integer collegeId,
                            @PathVariable("majorId") Integer majorId){
        return roomService.queryRoom(collegeId,majorId);
    }

    @ApiOperation("为学生分配宿舍")
    @PostMapping("/addStudentToRoom")
    public Object addStudentToRoom(@RequestBody StudentToRoomDTO studentToRoomDTO){
        return roomService.addStudentToRoom(studentToRoomDTO);
    }
}
