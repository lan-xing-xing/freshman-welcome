package org.graduation.freshmanwelcome.service;

import org.graduation.freshmanwelcome.DTO.HeadteacherInfoDTO;
import org.graduation.freshmanwelcome.DTO.ResultDTO;
import org.graduation.freshmanwelcome.entity.Headteacher;
import org.graduation.freshmanwelcome.exception.CustomizeErrorCode;
import org.graduation.freshmanwelcome.mapper.HeadteacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadteacherServiceImpl implements HeadteacherService {

    @Autowired
    private HeadteacherMapper headteacherMapper;

    @Override
    public Object queryMajorHeadteacher(Integer collegeId, Integer majorId) {
        List<HeadteacherInfoDTO> headteacherInfoDTOList = headteacherMapper.queryMajorHeadteacher(collegeId,majorId);
        ResultDTO resultDTO = new ResultDTO(20000,headteacherInfoDTOList);
        return resultDTO;
    }

    @Override
    public Object updateHeadteacherInfo(Headteacher headteacher) {
        if (headteacherMapper.updateTeacherById(headteacher) > 0){
            return ResultDTO.okOf();
        }else {
            return ResultDTO.errorOf(CustomizeErrorCode.INSERT_ERROR);
        }
    }
}
