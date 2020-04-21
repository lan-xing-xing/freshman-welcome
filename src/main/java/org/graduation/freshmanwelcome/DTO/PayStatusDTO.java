package org.graduation.freshmanwelcome.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class PayStatusDTO implements Serializable {
    private Long studentId;
    private Double payFee;
}
