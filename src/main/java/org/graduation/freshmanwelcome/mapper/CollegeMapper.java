package org.graduation.freshmanwelcome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.graduation.freshmanwelcome.DTO.RealTimeDataDTO;
import org.graduation.freshmanwelcome.DTO.StudentBasicDTO;
import org.graduation.freshmanwelcome.DTO.StudentDetailsDTO;
import org.graduation.freshmanwelcome.entity.Address;
import org.graduation.freshmanwelcome.entity.College;
import org.graduation.freshmanwelcome.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CollegeMapper extends BaseMapper<College> {

    List<College> findAll();

    //获取学院全部学生基本信息（分页）
    List<StudentBasicDTO> getStudentBasicByCollege(Integer collegeId);

    //获取学院全部学生详细信息（分页）
    List<StudentDetailsDTO> getStudentDetailsByCollege(Integer collegeId);

    //根据学生某属性获取该学院一个或者多个学生信息(分页)
    List<StudentBasicDTO> getStudentBasicByInfo(@Param("collegeId") String collegeId,
                                                @Param("key") String key, @Param("value") Object value);

    String getCollegeName(Integer collegeId);

    RealTimeDataDTO getStudentNum(Integer collegeId);

    Address getAddressInfo(Integer collegeId);
}
