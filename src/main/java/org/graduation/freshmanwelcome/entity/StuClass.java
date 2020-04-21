package org.graduation.freshmanwelcome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class StuClass implements Serializable {
    @JsonIgnore
    private Integer classId;
    private String className;
    @JsonIgnore
    private Integer headteacherId;
    @JsonIgnore
    private Integer collegeMajorId;
}
