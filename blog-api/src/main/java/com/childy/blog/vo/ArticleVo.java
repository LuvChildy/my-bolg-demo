package com.childy.blog.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArticleVo {
    private Long id;
    private String title;
    private String summary;
    private int commentCount;
    private int viewCount;
    private boolean weight;
    private String createTime;
    private String author;
    private List<TagVo> tags;
}
