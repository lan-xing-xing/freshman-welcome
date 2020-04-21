package org.graduation.freshmanwelcome.handler;

import org.graduation.freshmanwelcome.DTO.ResultDTO;
import org.graduation.freshmanwelcome.DTO.UserLoginDTO;
import org.graduation.freshmanwelcome.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginHandler {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public Object login(@RequestBody UserLoginDTO userLoginDTO){
        return loginService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword());
    }

    @GetMapping(value = "/user/info")
    public Object getUserInfo(@RequestParam("token") String token){
        //根据token获取登录用户的详细
        return loginService.getUserInfo(token);
    }

    @PostMapping(value = "/user/logout")
    public Object logout(){
     return ResultDTO.okOf();
    }
}
