package org.graduation.freshmanwelcome.service;

import org.graduation.freshmanwelcome.DTO.StudentToRoomDTO;

public interface RoomService {
    Object queryRoom(Integer collegeId, Integer majorId);

    Object addStudentToRoom(StudentToRoomDTO studentToRoomDTO);
}
