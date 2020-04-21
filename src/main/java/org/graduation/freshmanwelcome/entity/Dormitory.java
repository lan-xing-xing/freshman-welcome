package org.graduation.freshmanwelcome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class Dormitory implements Serializable {
    @JsonIgnore
    private Integer dormitoryId;
    private String dormitoryName;
}
