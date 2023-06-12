package com.project.managementsystem.universal;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/************************
 * ManageSystem
 * com.project.managesystem.global
 * MHC
 * author : mhc
 * date:  2023/6/11 11:44
 * description : 这是一个与前端交接的通用结果返回类
 ************************/
@Data
public class Result<T> {
    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //信息

    private T data; //数据

    private Map<String, Object> map = new HashMap<>(); //动态数据

    public static <T> Result<T> success(T object) {
        Result<T> r = new Result<T>();
        r.data = object;
        r.code = 200;
        return r;
    }

    //接受对象的list


    public static <T> Result<T> error(String msg) {
        Result<T> r = new Result<>();
        r.msg = msg;
        r.code = 0;
        return r;
    }


    public static <T> Result<T> success(String msg, Integer code, T data) {
        Result<T> r = new Result<>();
        r.msg = msg;
        r.code = code;
        r.data = data;
        return r;
    }

    public static <T> Result<T> success(String msg, Integer code) {
        Result<T> r = new Result<>();
        r.msg = msg;
        r.code = code;
        return r;
    }

    public static <T> Result<T> success() {
        Result<T> r = new Result<>();
        r.msg = "success";
        r.code = 200;
        return r;
    }

    public static <T> Result<T> error() {
        Result<T> r = new Result<>();
        r.msg = "error";
        r.code = 0;
        return r;
    }

    public static <T> Result<T> error(T data) {
        Result<T> r = new Result<>();
        r.msg = "error";
        r.code = 0;
        r.data = data;
        return r;
    }

    public static <T> Result<T> error(String msg, T data) {
        Result<T> r = new Result<>();
        r.msg = msg;
        r.code = 0;
        r.data = data;
        return r;
    }

    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
