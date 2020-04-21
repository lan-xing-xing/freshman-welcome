package org.graduation.freshmanwelcome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.graduation.freshmanwelcome.DTO.RealTimeDataDTO;
import org.graduation.freshmanwelcome.DTO.StudentBasicDTO;
import org.graduation.freshmanwelcome.DTO.StudentDetailsDTO;
import org.graduation.freshmanwelcome.entity.Address;
import org.graduation.freshmanwelcome.entity.Major;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MajorMapper extends BaseMapper<Major> {
    //查询专业所有学生的基本信息
    List<StudentBasicDTO> queryStudentBasicByMajor(@Param("collegeId") Integer collegeId, @Param("majorId") Integer majorId);

    //查询专业所有学生的详细信息
    List<StudentDetailsDTO> getStudentDetailsByMajor(@Param("collegeId") Integer collegeId, @Param("majorId") Integer majorId);

    //根据学生某属性获取该专业一个或者多个学生基本信息
    List<StudentBasicDTO> getStudentBasicByInfo(@Param("collegeId") Integer collegeId,@Param("majorId") Integer majorId,
                                                @Param("key") String key, @Param("value") Object value);
    String getMajorName(Integer majorId);

    RealTimeDataDTO getStudentNum(@Param("collegeId") Integer collegeId, @Param("majorId") Integer majorId);

    Address getAddressInfo(@Param("collegeId") Integer collegeId, @Param("majorId") Integer majorId);
}
