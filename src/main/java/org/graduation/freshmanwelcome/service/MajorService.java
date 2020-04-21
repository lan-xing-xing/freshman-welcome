package org.graduation.freshmanwelcome.service;

import org.graduation.freshmanwelcome.DTO.RealTimeDataDTO;
import org.graduation.freshmanwelcome.DTO.StudentBasicDTO;
import org.graduation.freshmanwelcome.DTO.StudentDetailsDTO;

import java.util.List;

public interface MajorService {
    Object queryStudentBasicByMajor(Integer collegeId, Integer majorId, Integer pageNum, Integer pageSize);

    Object queryStudentDetailsByMajor(Integer collegeId, Integer majorId, Integer pageNum, Integer pageSize);

    Object queryStudentBasicByInfo(Integer collegeId, Integer majorId, String key, Object value, Integer pageNum, Integer pageSize);

    List<StudentDetailsDTO> queryAllStudentDetailsNoPage(Integer collegeId, Integer majorId);

    String getMajorName(Integer majorId);

    List<StudentBasicDTO> queryNotReportStudentDetailsNoPage(Integer collegeId, Integer majorId);

    RealTimeDataDTO getRealTimeData(Integer collegeId, Integer majorId);
}
