package com.childy.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.childy.blog.dao.mapper.ArticleMapper;
import com.childy.blog.dao.pojo.Article;
import com.childy.blog.service.ArticleService;
import com.childy.blog.service.TagService;
import com.childy.blog.vo.ArticleVo;
import com.childy.blog.vo.Result;
import com.childy.blog.vo.params.PageParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//注册进去Spring的Service容器
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagService tagService;

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
         * @Date:2022年9月18日 现在还不能返回，因为这个是数据库直接返回的数据，需要vo对象，即面向view的对象。
         * 但是天太晚了，明天再学习。
         */
        List<ArticleVo> articleVos = copyList(records);
        return Result.success(articleVos);
    }

    private List<ArticleVo> copyList(List<Article> records) {
        List<ArticleVo> list = new ArrayList<>();
        for (Article article : records) {
            list.add(copy(article));
        }
        return list;
    }

    private ArticleVo copy(Article article, boolean showTag, boolean showAuthor) {
        ArticleVo articleVo = new ArticleVo();
        //Spring 提供的方法，用来copy属性
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setCreateTime(new Date(article.getCreateData()).toString());

        //并不是所有的文章都需要有作者和tag信息。
        if (showTag) {
            Long id = article.getId();
            articleVo.setTags(tagService.findTagsVoByArticleId(id));
        }
        if (showAuthor) {
            articleVo.setAuthor(article.getAuthor());
        }
        return articleVo;
    }

}
