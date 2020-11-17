package com.chenchen.common.entity;

/**
 * 返回结果集
 * @author chenchen
 */
public class ResultEntity {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 是否成功
     */
    private Boolean flag;

    /**
     * 信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    public ResultEntity() {
    }

    public ResultEntity(Integer code, Boolean flag, String message) {
        this.code = code;
        this.flag = flag;
        this.message = message;
    }

    public ResultEntity(Integer code, Boolean flag, String message, Object data) {
        this.code = code;
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "code=" + code +
                ", flag=" + flag +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
