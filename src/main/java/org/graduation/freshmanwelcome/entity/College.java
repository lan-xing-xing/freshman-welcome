package org.graduation.freshmanwelcome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class College implements Serializable {
    @JsonIgnore
    private Integer collegeId;
    private String collegeName;
}
