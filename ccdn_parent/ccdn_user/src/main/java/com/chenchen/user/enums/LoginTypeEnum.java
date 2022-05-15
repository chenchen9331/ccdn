package main.java.com.chenchen.user.enums;

import java.util.ArrayList;
import java.util.List;

public enum LoginTypeEnum {
    SUCCESS((short) 0, "登陆成功"),
    FAIL((short) 1, "登录失败"),
    NOAUTORITY((short) 2, "无权登录");

    // 登录码
    private Short code;

    // 描述
    private String description;

    LoginTypeEnum(Short code, String description) {
        this.code = code;
        this.description = description;
    }

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<Short> noLoginType() {
        List<Short> list = new ArrayList<>();
        list.add(FAIL.getCode());
        list.add(NOAUTORITY.getCode());
        return list;
    }
}
