package com.yzunlp.qzfeng.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Huang
 * @date: 2025/6/28 15:31
 */
public class Result<T> implements Serializable {

    private Integer code; //编码：1成功，0和其它数字为失败
    private String msg; //错误信息
    private T data; //数据

    public Result() {
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.setCode(1);
        result.setMsg("操作成功");
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.setCode(1);
        result.setMsg("操作成功");
        result.setData(object);
        return result;
    }

    public static <T> Result<T> success(String msg, T object) {
        Result<T> result = new Result<T>();
        result.setCode(1);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    public static <T> Result<T> success(Integer code, T object) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg("操作成功");
        result.setData(object);
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }
}
