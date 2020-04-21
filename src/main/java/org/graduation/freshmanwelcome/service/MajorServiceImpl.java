package org.graduation.freshmanwelcome.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.graduation.freshmanwelcome.DTO.RealTimeDataDTO;
import org.graduation.freshmanwelcome.DTO.ResultDTO;
import org.graduation.freshmanwelcome.DTO.StudentBasicDTO;
import org.graduation.freshmanwelcome.DTO.StudentDetailsDTO;
import org.graduation.freshmanwelcome.entity.Address;
import org.graduation.freshmanwelcome.mapper.MajorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    MajorMapper majorMapper;

    @Override
    public Object queryStudentBasicByMajor(Integer collegeId, Integer majorId, Integer pageNum, Integer pageSize) {
        //查询专业所有学生的基本信息（分页）
        PageHelper.startPage(pageNum,pageSize);
        List<StudentBasicDTO> studentBasicDTOList = majorMapper.queryStudentBasicByMajor(collegeId, majorId);
        PageInfo<StudentBasicDTO> page = new PageInfo<>(studentBasicDTOList);
        return new ResultDTO(20000,page);
    }

    @Override
    public Object queryStudentDetailsByMajor(Integer collegeId, Integer majorId, Integer pageNum, Integer pageSize) {//查询某专业全部学生
        //查询专业所有学生的详细信息（分页）
        PageHelper.startPage(pageNum,pageSize);
        List<StudentDetailsDTO> studentDetailsDTOList = majorMapper.getStudentDetailsByMajor(collegeId,majorId);
        PageInfo<StudentDetailsDTO> page = new PageInfo<>(studentDetailsDTOList);
        return new ResultDTO(20000,page);
    }

    @Override
    public Object queryStudentBasicByInfo(Integer collegeId, Integer majorId, String key, Object value, Integer pageNum, Integer pageSize) {
        //根据学生某属性获取该专业一个或者多个学生基本信息(分页)
        PageHelper.startPage(pageNum,pageSize);
        List<StudentBasicDTO> studentBasicDTOList = majorMapper.getStudentBasicByInfo(collegeId,majorId,key,value);
        PageInfo<StudentBasicDTO> page = new PageInfo<>(studentBasicDTOList);
        return new ResultDTO(20000,page);
    }

    @Override
    public List<StudentDetailsDTO> queryAllStudentDetailsNoPage(Integer collegeId, Integer majorId) {
        return majorMapper.getStudentDetailsByMajor(collegeId,majorId);
    }

    @Override
    public String getMajorName(Integer majorId) {
        String majorName = majorMapper.getMajorName(majorId);
        if (majorName != null){
            return majorName;
        }else {
            return "20012";
        }
    }

    @Override
    public List<StudentBasicDTO> queryNotReportStudentDetailsNoPage(Integer collegeId, Integer majorId) {
        return majorMapper.getStudentBasicByInfo(collegeId,majorId,"pay_status",0);
    }

    @Override
    public RealTimeDataDTO getRealTimeData(Integer collegeId, Integer majorId) {
        Address address = majorMapper.getAddressInfo(collegeId,majorId);
        RealTimeDataDTO realTimeDataDTO = majorMapper.getStudentNum(collegeId,majorId);
        realTimeDataDTO.setAddress(address);
        return realTimeDataDTO;
    }
}
