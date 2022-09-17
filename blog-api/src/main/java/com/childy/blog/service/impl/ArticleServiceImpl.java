package com.childy.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.childy.blog.dao.mapper.ArticleMapper;
import com.childy.blog.dao.pojo.Article;
import com.childy.blog.service.ArticleService;
import com.childy.blog.vo.Result;
import com.childy.blog.vo.params.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//注册进去Spring的Service容器
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * @param param
     * @return
     */
    @Override
    public Result listArticle(PageParam param) {
        /**
         * 接口需求:
         * 1. 分页查询 article数据库表
         */
        //TODO mybatis的接口，需要看一下文档说明，接口如何使用，分页又是什么？
        Page<Article> page = new Page<>(param.getPage(), param.getPageSize());
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        //先查询置顶数据，可以放在一个函数里面
        //wrapper.orderByDesc(Article::isWeight);
        //对应数据库查询语句： order by create_data desc
        wrapper.orderByDesc(Article::isWeight, Article::getCreateData);
        Page<Article> articlePage = articleMapper.selectPage(page, wrapper);
        //TODO record是什么东西？查一下文档
        List<Article> records = articlePage.getRecords();

        /**
         * @Date:2022年9月18日 现在还不能返回，因为这个是数据库直接返回的数据，需要vo对象。
         * 但是天太晚了，明天再学习。
         */
        return null;
    }
}
