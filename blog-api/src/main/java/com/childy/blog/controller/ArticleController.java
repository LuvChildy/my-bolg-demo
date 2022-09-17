package com.childy.blog.controller;

import com.childy.blog.service.ArticleService;
import com.childy.blog.vo.Result;
import com.childy.blog.vo.params.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接口文档是如下定义的：
 * url: /articles
 * 请求方式: POST
 *
 * 参数名称 参数类型    说明
 * page     int     当前页数
 * pageSize int     每页显示数量
 */

//json交互
@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    /**
     * 首页文章列表
     * @param param
     * @return
     */
    @PostMapping
    public Result listArticle(@RequestBody PageParam param) {

        //需要一个server去处理params并且生成一个Result
        return articleService.listArticle(param);
    }
}
