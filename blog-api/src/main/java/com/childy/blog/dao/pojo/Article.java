package com.childy.blog.dao.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Article {
    private static final int articleTop = 1;
    public static final int articleCommon = 0;
    private long id;
    private long createData;
    private String title;
    private String summary;
    private int commonCounts;
    //置顶标志
    private boolean weight;
    private String author;
    private List<Tag> tags;
}
