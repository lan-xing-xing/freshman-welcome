package org.graduation.freshmanwelcome.DTO;

import lombok.Data;
import org.graduation.freshmanwelcome.entity.Address;

@Data
public class RealTimeDataDTO {
    private Integer totalNum;
    private Integer payNum;
    private Integer registerNum;
    private Double payRate;
    private Double registerRate;
    private Integer maleNum;
    private Integer femaleNum;
    private Integer malePay;
    private Integer femalePay;
    private Address address;
}
