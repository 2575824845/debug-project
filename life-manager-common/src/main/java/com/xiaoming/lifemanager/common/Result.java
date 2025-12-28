package com.xiaoming.lifemanager.common;

public class Result<T> {
    private int code;           // 状态码：1成功，0失败
    private String message;     // 提示信息
    private T data;             // 数据对象

    // 构造方法
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功返回
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(1, message, data);
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(1, message, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(1, "操作成功", data);
    }

    public static <T> Result<T> success() {
        return new Result<>(1, "操作成功", null);
    }

    // 失败返回
    public static <T> Result<T> error(String message) {
        return new Result<>(0, message, null);
    }

    // getter和setter
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}