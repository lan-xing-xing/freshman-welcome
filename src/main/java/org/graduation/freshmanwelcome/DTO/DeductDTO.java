package org.graduation.freshmanwelcome.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeductDTO implements Serializable {
    private Long studentId;
    private Double deductFee;

    public DeductDTO(Long studentId, Double deductFee) {
        this.studentId = studentId;
        this.deductFee = deductFee;
    }
}
