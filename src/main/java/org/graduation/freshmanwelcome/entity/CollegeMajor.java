package org.graduation.freshmanwelcome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class CollegeMajor implements Serializable {
    @JsonIgnore
    private Integer collegeMajorId;
    @JsonIgnore
    private Integer collegeId;
    @JsonIgnore
    private Integer majorId;
}
