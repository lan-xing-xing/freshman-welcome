package org.graduation.freshmanwelcome.service;

import org.graduation.freshmanwelcome.DTO.ResultDTO;
import org.graduation.freshmanwelcome.DTO.UserInfoDTO;
import org.graduation.freshmanwelcome.entity.User;
import org.graduation.freshmanwelcome.exception.CustomizeErrorCode;
import org.graduation.freshmanwelcome.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultDTO login(String username, String password) {//用户登录
        Map map = new HashMap();
        map.put("username",username);
        List<User> list = userMapper.selectByMap(map);
        if (list.size() !=0){
            String pwd = userMapper.checkUser(username);
            if (pwd.equals(password)){
                //登录成功
                /**
                 * 需要返回的数据格式：
                 * {code: 20000, data: {token: "admin-token"}}
                 * token根据登录的用户账号从数据中获取
                 * admin-token user-token
                 */
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCode(20000);
                User user = list.get(0);
                String token = user.getToken();
                Map<String,Object> successData = new HashMap<>();
                successData.put("token",token);
                resultDTO.setData(successData);
                return resultDTO;
            }else {
                //密码错误
                return ResultDTO.errorOf(CustomizeErrorCode.PASSWORD_ERROR);
            }
        }else {
            //不存在该用户
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_FOUND_USER);
        }
    }

    @Override
    public Object getUserInfo(String token) {//根据token获取用户的详细信息
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        Map<String,Object> tokenCol = new HashMap<>();
        tokenCol.put("token",token);
        List<User> userList = userMapper.selectByMap(tokenCol);
        if (userList.size() > 0){
            User user = userList.get(0);
            BeanUtils.copyProperties(user,userInfoDTO);
            //将取到的role以逗号分隔，放入数组中
            String roleString = user.getRoles();
            List<String> roles = Arrays.asList(roleString.split("\\s*,\\s*"));
            userInfoDTO.setRoles(roles);
            userInfoDTO.setName(user.getUsername());
            ResultDTO resultDTO = new ResultDTO();
            resultDTO.setCode(20000);
            resultDTO.setData(userInfoDTO);
            return resultDTO;
        }else {
            return ResultDTO.errorOf(CustomizeErrorCode.TOKEN_ERROR);
        }
    }
}
