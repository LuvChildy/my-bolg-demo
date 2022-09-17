package com.childy.blog.vo.params;

import lombok.Data;

/**
 * 接口文档是如下定义的：
 * url: /articles
 * 请求方式: POST
 *
 * 参数名称 参数类型    说明
 * page     int     当前页数
 * pageSize int     每页显示数量
 */

@Data
public class PageParam {
    private int page = 1;
    private int pageSize = 10;
}
