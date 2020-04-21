package org.graduation.freshmanwelcome.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Major implements Serializable {
    private Integer majorId;
    private String majorName;
}
