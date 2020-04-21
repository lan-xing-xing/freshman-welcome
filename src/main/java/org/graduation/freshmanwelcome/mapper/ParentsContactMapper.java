package org.graduation.freshmanwelcome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.graduation.freshmanwelcome.entity.ParentsContact;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ParentsContactMapper extends BaseMapper<ParentsContact> {
    Integer updateParentsContactByStudentId(ParentsContact parentsContact);
}
