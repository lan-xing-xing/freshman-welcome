package org.graduation.freshmanwelcome.handler;

import org.graduation.freshmanwelcome.DTO.ResultDTO;
import org.graduation.freshmanwelcome.entity.Student;
import org.graduation.freshmanwelcome.service.StudentAdminService;
import org.graduation.freshmanwelcome.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ImportHandler {

    @Autowired
    private StudentAdminService studentAdminService;

    @PostMapping("/import/student")
    public Object import_order(@RequestParam("file")MultipartFile excel) {
        System.out.println(excel);
        Object objList = ExcelUtil.readExcel(excel, new Student(), 1, 1);
        if (objList == null) {
            return ResultDTO.errorOf(500, "导入的数据不能为空");
        }
        List<Student> studentList = (List<Student>) objList;
        if (studentList == null || studentList.size() <= 0) {
            return ResultDTO.errorOf(500, "导入的数据不能为空");
        }
        Integer result = studentAdminService.insertStudent(studentList);
        studentList.forEach(System.out::println);
        return new ResultDTO(20000,"成功插入" + result + "条数据");
    }
}
