package org.graduation.freshmanwelcome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.graduation.freshmanwelcome.DTO.ClassInfoDTO;
import org.graduation.freshmanwelcome.entity.StuClass;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StuClassMapper extends BaseMapper<StuClass> {
    List<ClassInfoDTO> queryClassInfo(@Param("collegeId") Integer collegeId,
                        @Param("majorId") Integer majorId);
}
