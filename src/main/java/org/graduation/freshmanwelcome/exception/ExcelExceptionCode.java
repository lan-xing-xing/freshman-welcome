package org.graduation.freshmanwelcome.exception;

public enum ExcelExceptionCode implements ICustomizeErrorCode {
    EXPORT_EXCEPTION(21001,"导出异常"),
    ERRO_FILE_FORMAT(21002,"文件格式错误"),
    NULL_EXCELTYPEENUM(21001,"excelTypeEnum can not null");

    private Integer code;
    private String data;

    ExcelExceptionCode(Integer code, String data) {
        this.code = code;
        this.data = data;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getData() {
        return data;
    }
}
