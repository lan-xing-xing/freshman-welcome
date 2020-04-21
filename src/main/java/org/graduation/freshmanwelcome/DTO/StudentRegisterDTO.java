package org.graduation.freshmanwelcome.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentRegisterDTO implements Serializable {
    private Long studentId;
    private String studentName;
    private Integer payStatus;
    private Integer registerStatus;
    private String roomName;
}
