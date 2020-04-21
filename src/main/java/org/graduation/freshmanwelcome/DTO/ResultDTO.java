package org.graduation.freshmanwelcome.DTO;

import lombok.Data;
import org.graduation.freshmanwelcome.exception.CustomizeErrorCode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class ResultDTO implements Serializable {//返回信息
    private Integer code;
    private Object data;

    public ResultDTO(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResultDTO() {
    }

    public static ResultDTO errorOf(Integer code, String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setData(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(),errorCode.getData());
    }

    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(20000);
        resultDTO.setData("成功！！");
        return resultDTO;
    }

    public static ResultDTO token(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(20000);
        Map<String,Object> errorData = new HashMap<>();
//        String[] a = {"admin"};
        errorData.put("roles","super-admin");
        resultDTO.setData(errorData);
        return resultDTO;
    }
}
