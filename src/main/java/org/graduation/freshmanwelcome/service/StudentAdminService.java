package org.graduation.freshmanwelcome.service;

import org.graduation.freshmanwelcome.DTO.PostRegisterDTO;
import org.graduation.freshmanwelcome.DTO.RealTimeDataDTO;
import org.graduation.freshmanwelcome.DTO.StudentDetailsDTO;
import org.graduation.freshmanwelcome.entity.Student;

import java.util.List;

public interface StudentAdminService {
    //分页查询全部学生基本信息
    Object queryAllStudentBasic(Integer pageNum, Integer pageSize);

    //根据学生id查询该学生的详细信息
    Object queryStudentDetailsById(Long studentId);

    //分页查询所有学生详细信息
    Object queryAllStudentDetails(Integer pageNum, Integer pageSize);

    List<StudentDetailsDTO> queryAllStudentDetailsNoPage();

    //根据某个属性查询学生（学号，准考证号，身份证号）
    Object queryStudentByInfo(String key, Object value);

    //根据某个属性查询学生（分页）
    Object queryStudentByInfo(String key, Object value, Integer pageNum, Integer pageSize);

    //根据学号、姓名、身份证号任意一项查询
    Object queryStudentRegister(String info);

    Object queryStudentRegister(Integer collegeId, Integer majorId, String info);

    //修改学生报到状态
    Object updateStudentPayStatus(Long studentId);

    //修改学生注册状态
    Object updateStudentRegisterStatus(PostRegisterDTO postRegisterDTO);

    List<Student> queryAllStudent();

    //导入学生数据
    Integer insertStudent(List<Student> studentList);

    //获取全校实时报到率信息
    RealTimeDataDTO getRealTimeData();
}
