package com.bb.bb.common;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    private String time;

    public Result(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
