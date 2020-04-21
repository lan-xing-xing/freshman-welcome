package org.graduation.freshmanwelcome.DTO;

import lombok.Data;
import org.graduation.freshmanwelcome.entity.Student;

import java.io.Serializable;
import java.util.Date;

@Data
public class StudentInfoDTO implements Serializable {//student: 接收前端传来的学生信息
    public static final long serialVersionUID = 1L;
    private Long studentId;
    private String phone;
    private String address;
    private String mail;
    private String transportation;
    private Date date;
    private String fatherName;
    private String fatherPhone;
    private String motherName;
    private String motherPhone;
    private String emergencyContactName;
    private String emergencyContactPhone;
}
