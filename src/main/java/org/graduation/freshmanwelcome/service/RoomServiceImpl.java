package org.graduation.freshmanwelcome.service;

import org.graduation.freshmanwelcome.DTO.ResultDTO;
import org.graduation.freshmanwelcome.DTO.RoomInfoDTO;
import org.graduation.freshmanwelcome.DTO.StudentToRoomDTO;
import org.graduation.freshmanwelcome.entity.Room;
import org.graduation.freshmanwelcome.entity.Student;
import org.graduation.freshmanwelcome.exception.CustomizeErrorCode;
import org.graduation.freshmanwelcome.mapper.RoomMapper;
import org.graduation.freshmanwelcome.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Object queryRoom(Integer collegeId, Integer majorId) {
        List<RoomInfoDTO> roomInfoDTOList = roomMapper.queryRoom(collegeId,majorId);
        return new ResultDTO(20000,roomInfoDTOList);
    }

    @Override
    public Object addStudentToRoom(StudentToRoomDTO studentToRoomDTO) {
        //判断房间是否满人
        Room room = roomMapper.selectRoomById(studentToRoomDTO.getRoomId());
        //获取房间人数
        Integer haveNum = roomMapper.selectNumById(room.getRoomId());
        if (room.getMax() > haveNum){
            //判断学生是否已分配宿舍
            Student student = studentMapper.selectById(studentToRoomDTO.getStudentId());
            if (student.getRoomId() == null){
                //将roomId更新到学生表
                student.setRoomId(studentToRoomDTO.getRoomId());
                if (studentMapper.updateStudentById(student) > 0){
                    return ResultDTO.okOf();
                }else {
                    return ResultDTO.errorOf(CustomizeErrorCode.INSERT_ERROR);
                }
            }else {
                return ResultDTO.errorOf(CustomizeErrorCode.HAVE_ROOM);
            }
        }else {
            return ResultDTO.errorOf(CustomizeErrorCode.FULL_ROOM);
        }
    }
}
