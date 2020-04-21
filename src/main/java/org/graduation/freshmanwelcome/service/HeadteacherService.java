package org.graduation.freshmanwelcome.service;

import org.graduation.freshmanwelcome.entity.Headteacher;

public interface HeadteacherService {
    // 获取某专业全部班主任信息
    Object queryMajorHeadteacher(Integer collegeId,Integer majorId);

    Object updateHeadteacherInfo(Headteacher headteacher);
}
