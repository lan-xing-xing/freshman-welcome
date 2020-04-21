package org.graduation.freshmanwelcome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class FinancialAid implements Serializable {
    private Integer financialAidId;
    private Long studentId;
    private Integer deduction;
    private Integer auditStatus;
    private String documentPath;
}
