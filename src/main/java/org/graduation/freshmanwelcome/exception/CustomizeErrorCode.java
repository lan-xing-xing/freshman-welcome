package org.graduation.freshmanwelcome.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    NOT_FOUND_USER(20001,"该用户不存在"),
    NOT_FOUND_STUDENT(20002,"该学生不存在"),
    PASSWORD_ERROR(20003,"密码错误"),
    TOKEN_ERROR(20004,"错误的Token"),
    INSERT_ERROR(20005,"数据插入异常"),
    NO_FINANCIALAID(20006,"该学生没有申请贷款"),
    EMPTY_PICTURE(20007,"图片为空"),
    BIG_PICTURE(20008,"图片过大"),
    FULL_ROOM(20009,"宿舍人数已满"),
    HAVE_ROOM(20010,"该学生已分配宿舍"),
    UPDATE_EXCEPTION(20011,"数据更新异常"),
    NOT_FOUND_COLLEGE(20012,"该学院不存在"),
    NOT_FOUND_MAJOR(20013,"该专业不存在");

    private Integer code;
    private String data;

    CustomizeErrorCode(Integer code, String data) {
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
