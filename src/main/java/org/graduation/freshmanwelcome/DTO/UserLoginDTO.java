package org.graduation.freshmanwelcome.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable {//Admin: 接收前端传来的账号和密码
    private String username;
    private String password;
}
