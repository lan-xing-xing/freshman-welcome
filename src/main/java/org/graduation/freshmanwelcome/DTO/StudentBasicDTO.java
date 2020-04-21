package org.graduation.freshmanwelcome.DTO;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StudentBasicDTO extends BaseRowModel implements Serializable {//admin: 返回给前端学生的基本信息（列表展示）
    public static final long serialVersionUID = 1L;
    @ExcelProperty(value = {"学号"}, index = 0)
    private Long studentId;

    @ExcelProperty(value = {"姓名"}, index = 1)
    private String studentName;

    @ExcelProperty(value = {"准考证号"}, index = 2)
    private long examId;

    @ExcelProperty(value = {"身份证号"}, index = 3)
    private String idNumber;

    @ExcelProperty(value = {"生源地"}, index = 4)
    private String cradle;

    @ExcelProperty(value = {"性别"}, index = 4)
    private String gender;

    @ExcelProperty(value = {"学院"}, index = 5)
    private String collegeName;

    @ExcelProperty(value = {"专业"}, index = 6)
    private String majorName;

    @ExcelProperty(value = {"班级"}, index = 7)
    private String className;

    @ExcelProperty(value = {"激活状态"}, index = 8)
    private Integer infoStatus;

    @ExcelProperty(value = {"报到状态"}, index = 9)
    private Integer payStatus;

    @ExcelProperty(value = {"注册状态"}, index = 10)
    private Integer registerStatus;
}
