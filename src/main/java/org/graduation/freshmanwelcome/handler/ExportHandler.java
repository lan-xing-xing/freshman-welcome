package org.graduation.freshmanwelcome.handler;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.graduation.freshmanwelcome.DTO.ResultDTO;
import org.graduation.freshmanwelcome.DTO.StudentBasicDTO;
import org.graduation.freshmanwelcome.DTO.StudentDetailsDTO;
import org.graduation.freshmanwelcome.entity.Student;
import org.graduation.freshmanwelcome.exception.CustomizeErrorCode;
import org.graduation.freshmanwelcome.service.CollegeService;
import org.graduation.freshmanwelcome.service.MajorService;
import org.graduation.freshmanwelcome.service.StudentAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class ExportHandler {

    @Autowired
    private StudentAdminService studentAdminService;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private MajorService majorService;

    @GetMapping("/exportStudentDetails/{path}")
    public Object exportStudentDetails(@PathVariable("path") String path,
                                     HttpServletResponse response) throws IOException {
        List<StudentDetailsDTO> list;
        String description;
        // 根据path来判断是学校、学院还是专业
        if (path.equals("school")){
            // 学校
            description = "全校";
            list = studentAdminService.queryAllStudentDetailsNoPage();
        }else {
            //学院 path例如：2
            Integer collegeId = new Integer(path);
            description = collegeService.getCollegeName(collegeId);
            if (description.equals("20011")){
                return ResultDTO.errorOf(CustomizeErrorCode.NOT_FOUND_COLLEGE);
            }
            //查询学院全部学生的详细信息
            list = collegeService.queryAllStudentDetailsNoPage(collegeId);
        }
        Sheet sheet = new Sheet(1, 0,StudentDetailsDTO.class);
        export(response,list,sheet,description);
        return ResultDTO.okOf();
    }

    @GetMapping("/exportStudentDetails/{collegeId}/{majorId}")
    public Object exportMajorStudentDetails(@PathVariable("collegeId") Integer collegeId,
                                            @PathVariable("majorId") Integer majorId,
                                       HttpServletResponse response) throws IOException {
        String collegeName = collegeService.getCollegeName(collegeId);
        String majorName;
        if (collegeName.equals("20011")){
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_FOUND_COLLEGE);
        }else {
            majorName = majorService.getMajorName(majorId);
            if (majorName.equals("20012")){
                return ResultDTO.errorOf(CustomizeErrorCode.NOT_FOUND_MAJOR);
            }
        }
        String description = collegeName + majorName;
        List<StudentDetailsDTO> list = majorService.queryAllStudentDetailsNoPage(collegeId,majorId);
        Sheet sheet = new Sheet(1, 0,StudentDetailsDTO.class);
        export(response,list,sheet,description);
        return ResultDTO.okOf();
    }

    @GetMapping("/exportStudentDetails/notReport/{collegeId}/{majorId}")
    public Object exportNotReportStudentDetails(@PathVariable("collegeId") Integer collegeId,
                                            @PathVariable("majorId") Integer majorId,
                                       HttpServletResponse response) throws IOException {
        String description = "未报到";
        List<StudentBasicDTO> list = majorService.queryNotReportStudentDetailsNoPage(collegeId,majorId);
        Sheet sheet = new Sheet(1, 0,StudentBasicDTO.class);
        export(response,list,sheet,description);
        return ResultDTO.okOf();
    }

    @GetMapping("/exportStudent")
    public Object exportStudent(HttpServletResponse response) throws IOException {
        String description = "基本";
        List<Student> list = studentAdminService.queryAllStudent();
        Sheet sheet = new Sheet(1, 0,StudentBasicDTO.class);
        export(response,list,sheet,description);
        return ResultDTO.okOf();
    }
    
    public void export(HttpServletResponse response, List list, Sheet sheet, String describtion) throws IOException {
        ServletOutputStream out = response.getOutputStream();
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        String fileName = describtion + "学生信息表";
        //设置自适应宽度
        sheet.setAutoWidth(Boolean.TRUE);
        // 第一个 sheet 名称
        sheet.setSheetName("学生信息");
        writer.write(list, sheet);
        //通知浏览器以附件的形式下载处理，设置返回头要注意文件名有中文
        response.setHeader("Content-disposition", "attachment;filename=" + new String( fileName.getBytes("gb2312"), "ISO8859-1" ) + ".xlsx");
        writer.finish();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        out.flush();
    }
}
