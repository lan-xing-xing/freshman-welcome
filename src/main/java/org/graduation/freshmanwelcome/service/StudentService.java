package org.graduation.freshmanwelcome.service;

import org.graduation.freshmanwelcome.DTO.ResultDTO;
import org.graduation.freshmanwelcome.DTO.StudentInfoDTO;
import org.graduation.freshmanwelcome.DTO.StudentLoginDTO;

public interface StudentService {
    //学生登录
    ResultDTO studentLogin(StudentLoginDTO studentLoginDTO);

    //根据学生id查询学生信息
    Object queryStudentById(Long studentId);

    //更新学生信息
    Object updateStudent(StudentInfoDTO studentInfoDTO);

    //获取学生激活状态
    Object getInfoStatus(Long studentId);

    //获取学生报到状态
    Object getPayStatus(Long studentId);

    //获取学生注册状态
    Object getRegStatus(Long studentId);
}
