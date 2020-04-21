package org.graduation.freshmanwelcome.DTO;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.graduation.freshmanwelcome.entity.Student;

import java.io.Serializable;

@Data
public class StudentDetailsDTO extends Student implements Serializable {//student: 学生详细信息
    public static final long serialVersionUID = 1L;

    @ExcelProperty(value = {"父亲姓名"}, index = 17)
    private String fatherName;

    @ExcelProperty(value = {"父亲电话"}, index = 18)
    private String fatherPhone;

    @ExcelProperty(value = {"母亲姓名"}, index = 19)
    private String motherName;

    @ExcelProperty(value = {"母亲电话"}, index = 20)
    private String motherPhone;

    @ExcelProperty(value = {"紧急联系人姓名"}, index = 21)
    private String emergencyContactName;

    @ExcelProperty(value = {"紧急联系人电话"}, index = 22)
    private String emergencyContactPhone;

    @ExcelProperty(value = {"班级"}, index = 23)
    private String className;

    @ExcelProperty(value = {"房间"}, index = 24)
    private String roomName;

//    @ExcelProperty(value = {"住宿费"}, index = 25)
//    private double roomFee;

    @ExcelProperty(value = {"学院"}, index = 25)
    private String collegeName;

    @ExcelProperty(value = {"宿舍楼栋"}, index = 26)
    private String dormitoryName;

    @ExcelProperty(value = {"班主任姓名"}, index = 27)
    private String headteacherName;

    @ExcelProperty(value = {"专业"}, index = 28)
    private String majorName;
}
