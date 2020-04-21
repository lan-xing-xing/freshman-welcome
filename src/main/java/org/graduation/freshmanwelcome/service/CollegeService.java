package org.graduation.freshmanwelcome.service;

import org.graduation.freshmanwelcome.DTO.RealTimeDataDTO;
import org.graduation.freshmanwelcome.DTO.StudentDetailsDTO;
import org.graduation.freshmanwelcome.entity.College;

import java.util.List;

public interface CollegeService {
    List<College> getAllCollege();

    //分页查询某学院所有学生基本信息
    Object queryStudenBasicByCollege(Integer collegeId, Integer pageNum, Integer pageSize);

    //分页查询某学院所有学生详细信息
    Object queryStudentDetailsByCollege(Integer collegeId, Integer pageNum, Integer pageSize);

    //根据学生某属性获取该学院一个或者多个学生信息
    Object queryStudentBasicByInfo(String collegeId, String key, Object value, Integer pageNum, Integer pageSize);

    List<StudentDetailsDTO> queryAllStudentDetailsNoPage(Integer collegeId);

    String getCollegeName(Integer collegeId);

    RealTimeDataDTO getRealTimeData(Integer collegeId);
}
