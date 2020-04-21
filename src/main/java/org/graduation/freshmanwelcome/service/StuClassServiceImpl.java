package org.graduation.freshmanwelcome.service;

import org.graduation.freshmanwelcome.DTO.ResultDTO;
import org.graduation.freshmanwelcome.mapper.StuClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StuClassServiceImpl implements StuClassService {

    @Autowired
    private StuClassMapper stuClassMapper;

    @Override
    public Object queryClassInfo(Integer collegeId, Integer majorId) {
        return new ResultDTO(20000,stuClassMapper.queryClassInfo(collegeId,majorId));
    }
}
