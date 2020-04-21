package org.graduation.freshmanwelcome.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.graduation.freshmanwelcome.DTO.RealTimeDataDTO;
import org.graduation.freshmanwelcome.DTO.ResultDTO;
import org.graduation.freshmanwelcome.DTO.StudentBasicDTO;
import org.graduation.freshmanwelcome.DTO.StudentDetailsDTO;
import org.graduation.freshmanwelcome.entity.Address;
import org.graduation.freshmanwelcome.entity.College;
import org.graduation.freshmanwelcome.exception.CustomizeErrorCode;
import org.graduation.freshmanwelcome.mapper.CollegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public List<College> getAllCollege() {
        return collegeMapper.selectList(null);
    }

    @Override
    public Object queryStudenBasicByCollege(Integer collegeId, Integer pageNum, Integer pageSize) {
        //查询学院全部学生基本信息(分页)
        PageHelper.startPage(pageNum,pageSize);
        List<StudentBasicDTO> studentBasicDTOList = collegeMapper.getStudentBasicByCollege(collegeId);
        PageInfo<StudentBasicDTO> page = new PageInfo<>(studentBasicDTOList);
        return new ResultDTO(20000,page);
    }

    @Override
    public Object queryStudentDetailsByCollege(Integer collegeId, Integer pageNum, Integer pageSize) {
        //查询某学院全部学生详细信息(分页)
        PageHelper.startPage(pageNum,pageSize);
        List<StudentDetailsDTO> studentDetailsDTOList = collegeMapper.getStudentDetailsByCollege(collegeId);
        PageInfo<StudentDetailsDTO> page = new PageInfo<>(studentDetailsDTOList);
        return new ResultDTO(20000,page);
    }

    @Override
    public Object queryStudentBasicByInfo(String collegeId, String key, Object value, Integer pageNum, Integer pageSize) {
        //根据学生某属性获取该学院一个或者多个学生信息(分页)
        PageHelper.startPage(pageNum,pageSize);
        List<StudentBasicDTO> studentBasicDTOList = collegeMapper.getStudentBasicByInfo(collegeId,key,value);
        PageInfo<StudentBasicDTO> page = new PageInfo<>(studentBasicDTOList);
        return new ResultDTO(20000,page);
    }

    @Override
    public List<StudentDetailsDTO> queryAllStudentDetailsNoPage(Integer collegeId) {
        return collegeMapper.getStudentDetailsByCollege(collegeId);
    }

    @Override
    public String getCollegeName(Integer collegeId) {
        String collegeName = collegeMapper.getCollegeName(collegeId);
        if (collegeName != null){
            return collegeName;
        }else {
            return "20011";
        }
    }

    @Override
    public RealTimeDataDTO getRealTimeData(Integer collegeId) {
        Address address = collegeMapper.getAddressInfo(collegeId);
        RealTimeDataDTO realTimeDataDTO = collegeMapper.getStudentNum(collegeId);
        realTimeDataDTO.setAddress(address);
        return realTimeDataDTO;
    }
}
