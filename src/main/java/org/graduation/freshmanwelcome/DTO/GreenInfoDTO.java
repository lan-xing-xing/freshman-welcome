package org.graduation.freshmanwelcome.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class GreenInfoDTO implements Serializable {//student：接收前端传来的绿色通道信息
    private Long studentId;
    private Integer deduction;
    private MultipartFile file;
}
