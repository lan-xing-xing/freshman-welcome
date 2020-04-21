package org.graduation.freshmanwelcome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.graduation.freshmanwelcome.entity.Dormitory;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DormitoryMapper extends BaseMapper<Dormitory> {
}
