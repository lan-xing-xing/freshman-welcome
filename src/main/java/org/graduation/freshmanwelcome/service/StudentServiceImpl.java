package org.graduation.freshmanwelcome.service;

import org.graduation.freshmanwelcome.DTO.*;
import org.graduation.freshmanwelcome.entity.ParentsContact;
import org.graduation.freshmanwelcome.entity.Student;
import org.graduation.freshmanwelcome.exception.CustomizeErrorCode;
import org.graduation.freshmanwelcome.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ParentsContactMapper parentsContactMapper;

    @Autowired
    private FinancialAidMapper financialAidMapper;

    @Override
    public ResultDTO studentLogin(StudentLoginDTO studentLoginDTO) {//学生登录
        //根据学号查询学生
        Student student = studentMapper.selectById(studentLoginDTO.getStudentId());
        if (student!=null){
            //若学生存在，则比对密码是否正确
            //密码为身份证号后六位
            String idNumber = student.getIdNumber();
            String pwd = idNumber.substring(idNumber.length()-6);
            //输入的密码
            String password = studentLoginDTO.getPassword();
            if (password !=null && pwd.equals(password)){
                //密码正确
                return ResultDTO.okOf();
            }else {
                //密码错误
                return ResultDTO.errorOf(CustomizeErrorCode.PASSWORD_ERROR);
            }
        }else {
            //该账号不存在
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_FOUND_STUDENT);
        }
    }

    @Override
    public Object queryStudentById(Long studentId) {//查询学生全部信息
        List<StudentDetailsDTO> studentDetailsDTOS = studentMapper.queryStudentById(studentId);
        if (studentDetailsDTOS.size() > 0){
            return studentDetailsDTOS.get(0);
        }else {
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_FOUND_STUDENT);
        }
    }

    @Override
    public Object updateStudent(StudentInfoDTO studentInfoDTO) {//学生提交基本信息
        //根据学号查询学生
        Student student = studentMapper.selectById(studentInfoDTO.getStudentId());
        if (student != null){
            //将学生手机号、地址插入学生表
            BeanUtils.copyProperties(studentInfoDTO,student);
            if (studentMapper.updateStudentById(student)>0){
                Map<String, Object> columnMap = new HashMap<>();
                columnMap.put("student_id",student.getStudentId());
                List<ParentsContact> parentsContacts = parentsContactMapper.selectByMap(columnMap);
                if (parentsContacts.size() > 0){
                    //若存在家庭联系信息，则修改信息
                    Integer parentsContactsId = parentsContacts.get(0).getParentsId();
                    ParentsContact parentsContact = new ParentsContact();
                    parentsContact.setParentsId(parentsContactsId);
                    //将studentInfo中的父母信息填入
                    BeanUtils.copyProperties(studentInfoDTO,parentsContact);
                    if (parentsContactMapper.updateParentsContactByStudentId(parentsContact) > 0){
                        studentMapper.InfoStatusToOne(student.getStudentId());
                        return ResultDTO.okOf();
                    }else {
                        return ResultDTO.errorOf(CustomizeErrorCode.INSERT_ERROR);
                    }
                }else {
                    //不存在家庭信息，增加一条家庭信息
                    ParentsContact parentsContact = new ParentsContact();
                    //将studentInfo中的父母信息填入
                    BeanUtils.copyProperties(studentInfoDTO,parentsContact);
                    if (parentsContactMapper.insert(parentsContact) > 0 && studentMapper.InfoStatusToOne(student.getStudentId()) >0){
                        return ResultDTO.okOf();
                    }else {
                        return ResultDTO.errorOf(CustomizeErrorCode.INSERT_ERROR);
                    }
                }
            }else {
                return ResultDTO.errorOf(CustomizeErrorCode.INSERT_ERROR);
            }
        }else {
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_FOUND_STUDENT);
        }
    }

    @Override
    public Object getInfoStatus(Long studentId) {//判断学生是否提交信息
        Student student = studentMapper.selectById(studentId);
        if (student != null){
            return student.getInfoStatus() ==1 ? 1 : 0;
        }
        return ResultDTO.errorOf(CustomizeErrorCode.NOT_FOUND_STUDENT);
    }

    @Override
    public Object getPayStatus(Long studentId) {//判断学生是否缴费
        Student student = studentMapper.selectById(studentId);
        if (student != null){
            return student.getPayStatus() ==1 ? 1 : 0;
        }
        return ResultDTO.errorOf(CustomizeErrorCode.NOT_FOUND_STUDENT);
    }

    @Override
    public Object getRegStatus(Long studentId) {//判断学生是否注册
        Student student = studentMapper.selectById(studentId);
        if (student != null){
            return student.getRegisterStatus() ==1 ? 1 : 0;
        }
        return ResultDTO.errorOf(CustomizeErrorCode.NOT_FOUND_STUDENT);
    }
}
