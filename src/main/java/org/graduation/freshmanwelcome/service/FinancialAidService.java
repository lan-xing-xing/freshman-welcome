package org.graduation.freshmanwelcome.service;

import org.graduation.freshmanwelcome.DTO.DeductDTO;
import org.graduation.freshmanwelcome.DTO.GreenInfoDTO;

public interface FinancialAidService {
    // 获取学生贷款状态
    Object getFinancialAidSatus(Long studentId);

    //获取学生绿色通道信息
    Object postGreenInfo(GreenInfoDTO greenInfoDTO);

    // 获取专业所有贷款信息
    Object queryMajorFinancialAid(Integer collegeId, Integer majorId, Integer pageNum, Integer pageSize);

    //学生贷款审核失败
    Object checkLoanFail(Long studentId);

    //学生贷款审核成功
    Object checkLoanSuccess(DeductDTO deductDTO);

    //获取学生贷款审核图片
    Object queryCheckInfo(Long studentId) throws Exception;
}
