package org.graduation.freshmanwelcome.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClassInfoDTO implements Serializable {
    private String className;
    private String headteacherName;
    private Integer studentNum;
    private Integer haveInfo;
    private Integer havePay;
    private Integer haveRegister;
}
