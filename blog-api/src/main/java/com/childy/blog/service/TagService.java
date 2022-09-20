package com.childy.blog.service;

import com.childy.blog.vo.TagVo;

import java.util.List;

public interface TagService {
    List<TagVo> findTagsVoByArticleId(Long id);
}
