package org.graduation.freshmanwelcome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.graduation.freshmanwelcome.DTO.RoomInfoDTO;
import org.graduation.freshmanwelcome.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RoomMapper extends BaseMapper<Room> {
    List<RoomInfoDTO> queryRoom(@Param("collegeId") Integer collegeId,
                                @Param("majorId") Integer majorId);

    Integer selectNumById(Integer roomId);

    Room selectRoomById(Integer roomId);

    Integer getRoomId(String roomName);
}
