package com.childy.blog.service;

import com.childy.blog.vo.Result;
import com.childy.blog.vo.params.PageParam;

public interface ArticleService {

    /**
     * 处理param，分页查询文章列表
     * @param param
     * @return
     */
    Result listArticle(PageParam param);
}
