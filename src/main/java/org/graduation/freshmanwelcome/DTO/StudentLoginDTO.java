package org.graduation.freshmanwelcome.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentLoginDTO implements Serializable {//student: 接收前端传来的学生账号和密码
    private Long studentId;
    private String password;
}
