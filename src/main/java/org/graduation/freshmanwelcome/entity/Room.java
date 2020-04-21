package org.graduation.freshmanwelcome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class Room implements Serializable {
    @JsonIgnore
    private Integer roomId;
    private String roomName;
    private double roomFee;
    private Integer max;
    @JsonIgnore
    private Integer dormitoryId;
    @JsonIgnore
    private Integer collegeMajorId;
}
