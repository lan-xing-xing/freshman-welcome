package org.graduation.freshmanwelcome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class Headteacher implements Serializable {
    private Integer headteacherId;
    private String headteacherName;
    private String phone;
    private String address;
    private String gender;
}
