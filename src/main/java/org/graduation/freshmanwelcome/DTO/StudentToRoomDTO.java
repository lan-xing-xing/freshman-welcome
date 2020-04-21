package org.graduation.freshmanwelcome.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentToRoomDTO implements Serializable {
    private Long studentId;
    private Integer roomId;
}
