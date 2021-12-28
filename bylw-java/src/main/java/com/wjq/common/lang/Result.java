package com.wjq.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @author wjq
 * @date 2021/12/23 21:17
 **/

@Data
public class Result implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public Result success(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public Result success(Object data) {
        return this.success(200, "操作成功", data);
    }

    public Result fail(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public Result fail(String msg, Object data) {
        return this.fail(400, msg, data);
    }

    public Result fail(String msg) {
        return this.fail(400, msg, null);
    }
}
