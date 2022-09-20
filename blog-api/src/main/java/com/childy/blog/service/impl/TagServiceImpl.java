package com.childy.blog.service.impl;

import com.childy.blog.dao.mapper.TagMapper;
import com.childy.blog.dao.pojo.Tag;
import com.childy.blog.service.TagService;
import com.childy.blog.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<TagVo> findTagsVoByArticleId(Long id) {
        //mybatis-plus 无法完成多表查询，所以需要自己来写。TODO: 这是什么东西？
        List<Tag> tags = tagMapper.findTagsByArticleId(id);
        return copyList(tags);
    }

    private List<TagVo> copyList(List<Tag> tags) {
        List<TagVo> tagVos = new ArrayList<>();
        for (Tag tag : tags) {
            tagVos.add(copy(tag));
        }
        return tagVos;
    }

    private TagVo copy(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
        return tagVo;
    }
}
