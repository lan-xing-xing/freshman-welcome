package org.graduation.freshmanwelcome.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String roles;
    private String token;
    private String introduction;
    private String avatar;
    private String path;
}
