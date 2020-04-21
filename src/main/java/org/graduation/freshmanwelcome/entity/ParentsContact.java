package org.graduation.freshmanwelcome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class ParentsContact implements Serializable {
    @JsonIgnore
    private Integer parentsId;
    private String fatherName;
    private String fatherPhone;
    private String motherName;
    private String motherPhone;
    private String emergencyContactName;
    private String emergencyContactPhone;
    @JsonIgnore
    private Long studentId;
}
