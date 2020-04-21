package org.graduation.freshmanwelcome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.graduation.freshmanwelcome.DTO.RealTimeDataDTO;
import org.graduation.freshmanwelcome.DTO.StudentBasicDTO;
import org.graduation.freshmanwelcome.DTO.StudentDetailsDTO;
import org.graduation.freshmanwelcome.DTO.StudentRegisterDTO;
import org.graduation.freshmanwelcome.entity.Address;
import org.graduation.freshmanwelcome.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    //根据学号查找学生
    Student selectById(Long studentId);

    //根据学号修改学生信息
    Integer updateStudentById(Student student);

    //修改学生填写信息的状态0->1
    Integer InfoStatusToOne(Long studentId);

    //根据学号查询学生的详细信息
    List<StudentDetailsDTO> queryStudentById(Long studentId);

    //查询所有该学生详细信息
    List<StudentDetailsDTO> getAllStudentDetails();

    //根据学生表某个属性查询学生，例如：student_id，student_name,exam_id,id_number,class_id等
    List<StudentDetailsDTO> queryStudentByMap(@Param("key") String key, @Param("value") Object value);

    //查询全部学生基本信息
    List<StudentBasicDTO> getAllStudentBasic();

    //根据学号、姓名、身份证号任意一项查询
    StudentRegisterDTO queryStudentRegister(String info);

    StudentRegisterDTO queryStudentRegister(@Param("collegeId") Integer collegeId,
                                            @Param("majorId") Integer majorId,
                                            @Param("info") String info);

    List<Student> selectAllStudent();

    Integer insertStudents(Student student);

    RealTimeDataDTO getStudentNum();

    Address getAddressInfo();
}
