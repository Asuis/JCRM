package com.jc.crm.config;

public class Result<T> {
    private int code;
    private T data;
    private String msg;
    public static Result success(Object data) {
        Result res = new Result();
        res.setCode(200);
        res.setData(data);
        return res;
    }
    public static Result fail(int code, String msg) {
        Result res =  new Result();
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
