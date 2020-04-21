package org.graduation.freshmanwelcome.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserInfoDTO implements Serializable {//Admin：返回给前端用户信息
    private String name;
    private List<String> roles;
    private String introduction;
    private String avatar;
    private String path;
}
