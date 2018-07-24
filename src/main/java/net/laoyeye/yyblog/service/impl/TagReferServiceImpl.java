package net.laoyeye.yyblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.laoyeye.yyblog.mapper.TagReferMapper;
import net.laoyeye.yyblog.service.TagReferService;

@Service
public class TagReferServiceImpl implements TagReferService {
    @Autowired
    private TagReferMapper tagReferMapper;

    @Override
    public List<String> listNameByArticleId(long id) {
    
        return tagReferMapper.listNameByArticleId(id);
    }

}
