package com.childy.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 文章返回json数据格式:
 * {
 *
 * }
 */

//全部构造，使用这个之后才可以使用new的一个直接构造方法。
@Data
@AllArgsConstructor
public class Result {
    private boolean success;
    private int code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return new Result(true, 200, "success", data);
    }

    public static Result fail(int code, String msg) {
        return new Result(false, code, msg, null);
    }
}
