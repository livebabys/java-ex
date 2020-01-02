package com.bb.bb.common;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private int code = 0;

    private String msg = "success";

    private T data;

    private String time;

    public Result(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Result(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public Result (T data){
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
