package org.graduation.freshmanwelcome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.graduation.freshmanwelcome.DTO.HeadteacherInfoDTO;
import org.graduation.freshmanwelcome.entity.Headteacher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface HeadteacherMapper extends BaseMapper<Headteacher> {
    List<HeadteacherInfoDTO> queryMajorHeadteacher(@Param("collegeId") Integer collegeId,
                                                   @Param("majorId") Integer majorId);

    Integer updateTeacherById(Headteacher headteacher);
}
