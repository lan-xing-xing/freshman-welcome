package org.graduation.freshmanwelcome.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostRegisterDTO implements Serializable {
    private Long studentId;
    private String roomName;
}
