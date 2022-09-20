package com.childy.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.childy.blog.dao.pojo.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据文章ID查询文章Tags
     * Note: 因为不支持多表查询，所以需要手动写xml，位置目录需要对应。TODO: 参考一下Spring boot框架文档。
     * @param id
     * @return
     */
    List<Tag> findTagsByArticleId(Long id);
}
