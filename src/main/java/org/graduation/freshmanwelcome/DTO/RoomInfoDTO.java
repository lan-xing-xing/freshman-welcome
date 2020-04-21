package org.graduation.freshmanwelcome.DTO;

import lombok.Data;

@Data
public class RoomInfoDTO {
    private Integer roomId;
    private String roomName;
    private String dormitoryName;
    private Integer max;
    private Integer haveNum;
}
