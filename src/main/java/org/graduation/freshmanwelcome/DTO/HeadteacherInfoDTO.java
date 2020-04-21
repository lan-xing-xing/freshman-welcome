package org.graduation.freshmanwelcome.DTO;

import lombok.Data;
import org.graduation.freshmanwelcome.entity.Headteacher;

import java.io.Serializable;

@Data
public class HeadteacherInfoDTO extends Headteacher implements Serializable {
    public static final long serialVersionUID = 1L;
    private String className;
}
