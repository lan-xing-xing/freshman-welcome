package org.graduation.freshmanwelcome.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.graduation.freshmanwelcome.DTO.DeductDTO;
import org.graduation.freshmanwelcome.entity.FinancialAid;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface FinancialAidMapper extends BaseMapper<FinancialAid> {
    boolean updateByStudentId(FinancialAid financialAid);

    List<FinancialAid> queryMajorFinancialAid(@Param("collegeId") Integer collegeId,
                                              @Param("majorId") Integer majorId);

    Integer checkLoanFail(Long studentId);

    Integer checkLoanSuccess(DeductDTO deductDTO);

    String queryInfoPath(Long studentId);
}