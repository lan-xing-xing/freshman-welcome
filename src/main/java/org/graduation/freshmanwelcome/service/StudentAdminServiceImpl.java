package org.graduation.freshmanwelcome.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.graduation.freshmanwelcome.DTO.*;
import org.graduation.freshmanwelcome.entity.Address;
import org.graduation.freshmanwelcome.entity.Student;
import org.graduation.freshmanwelcome.exception.CustomizeErrorCode;
import org.graduation.freshmanwelcome.exception.CustomizeException;
import org.graduation.freshmanwelcome.mapper.RoomMapper;
import org.graduation.freshmanwelcome.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StudentAdminServiceImpl implements StudentAdminService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Object queryAllStudentBasic(Integer pageNum, Integer pageSize) {//查询全部学生基本信息
        PageHelper.startPage(pageNum,pageSize);
        List<StudentBasicDTO> StudentBasicDTOList = studentMapper.getAllStudentBasic();
        PageInfo<StudentBasicDTO> page = new PageInfo<>(StudentBasicDTOList);
        return new ResultDTO(20000,page);
    }

    @Override
    public Object queryStudentDetailsById(Long studentId) {
        List<StudentDetailsDTO> studentDetailsDTOS = studentMapper.queryStudentById(studentId);
        if (studentDetailsDTOS.size() > 0){
            return new ResultDTO(20000,studentDetailsDTOS.get(0));
        }else {
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_FOUND_STUDENT);
        }
    }

    @Override
    public Object queryAllStudentDetails(Integer pageNum, Integer pageSize) {//查询全部学生
        PageHelper.startPage(pageNum,pageSize);
        List<StudentDetailsDTO> StudentDetailsDTOList = studentMapper.getAllStudentDetails();
        PageInfo<StudentDetailsDTO> page = new PageInfo<>(StudentDetailsDTOList);
        return new ResultDTO(20000,page);
    }

    @Override
    public List<StudentDetailsDTO> queryAllStudentDetailsNoPage() {//查询全部学生详细信息不分页
        List<StudentDetailsDTO> StudentDetailsDTOList = studentMapper.getAllStudentDetails();
        return StudentDetailsDTOList;
    }

    @Override
    public Object queryStudentByInfo(String key, Object value) {
        List<StudentDetailsDTO> studentDetailsDTOList = studentMapper.queryStudentByMap(key, value);
        return new ResultDTO(20000,studentDetailsDTOList);
    }

    @Override
    public Object queryStudentByInfo(String key, Object value, Integer pageNum, Integer pageSize) {//根据学生的某属性查询学生
        PageHelper.startPage(pageNum,pageSize);
        List<StudentDetailsDTO> studentDetailsDTOList = studentMapper.queryStudentByMap(key, value);
        PageInfo<StudentDetailsDTO> page = new PageInfo<>(studentDetailsDTOList);
        return new ResultDTO(20000,page);
    }

    @Override
    public Object queryStudentRegister(String info) {
        StudentRegisterDTO studentRegisterDTO = studentMapper.queryStudentRegister(info);
        if(studentRegisterDTO != null){
            return new ResultDTO(20000,studentRegisterDTO);
        }else {
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_FOUND_STUDENT);
        }
    }

    @Override
    public Object queryStudentRegister(Integer collegeId, Integer majorId, String info) {
        StudentRegisterDTO studentRegisterDTO = studentMapper.queryStudentRegister(collegeId,majorId,info);
        if(studentRegisterDTO != null){
            return new ResultDTO(20000,studentRegisterDTO);
        }else {
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_FOUND_STUDENT);
        }
    }

    @Override
    public Object updateStudentPayStatus(Long studentId) {
        Student student = studentMapper.selectById(studentId);
        student.setPayStatus(1);
        if (studentMapper.updateStudentById(student) > 0 ){
            return ResultDTO.okOf();
        }else {
            return ResultDTO.errorOf(CustomizeErrorCode.UPDATE_EXCEPTION);
        }
    }

    @Override
    public Object updateStudentRegisterStatus(PostRegisterDTO postRegisterDTO) {
        Student student = studentMapper.selectById(postRegisterDTO.getStudentId());
        student.setRegisterStatus(1);
        //根据roomName获取roomId
        Integer roomId = roomMapper.getRoomId(postRegisterDTO.getRoomName());
        student.setRoomId(roomId);
        if (studentMapper.updateStudentById(student) > 0 ){
            return ResultDTO.okOf();
        }else {
            return ResultDTO.errorOf(CustomizeErrorCode.UPDATE_EXCEPTION);
        }
    }

    @Override
    public List<Student> queryAllStudent() {
        return studentMapper.selectAllStudent();
    }

    @Override
    public Integer insertStudent(List<Student> studentList) {
        Integer result = 0;
        for (Student student : studentList) {
            //先查询是否存在该学生，若存在则跳过，不存在再插入
            if (studentMapper.selectById(student.getStudentId()) != null){
                break;
            }else {
                if (studentMapper.insertStudents(student) > 0){
                    result += 1;
                    continue;
                }else {
                    throw new CustomizeException(CustomizeErrorCode.INSERT_ERROR);
                }
            }
        }
        return result;
    }

    @Override
    public RealTimeDataDTO getRealTimeData() {
        Address address = studentMapper.getAddressInfo();
        RealTimeDataDTO realTimeDataDTO = studentMapper.getStudentNum();
        realTimeDataDTO.setAddress(address);
        return realTimeDataDTO;
    }
}

