package net.laoyeye.yyblog.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.mapper.TagMapper;
import net.laoyeye.yyblog.mapper.TagReferMapper;
import net.laoyeye.yyblog.model.TagDO;
import net.laoyeye.yyblog.model.query.TagQuery;
import net.laoyeye.yyblog.service.TagService;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TagReferMapper tagReferMapper;

    @Override
    public List<TagDO> listAllTag() {

        return tagMapper.listAllTag();
    }

    @Override
    public DataGridResult listPageTag(TagQuery query) {
        if (StringUtils.isBlank(query.getName())) {
            PageHelper.startPage(query.getPage(), query.getLimit()); 
            List<TagDO> list = tagMapper.listAllTag();
            //取记录总条数
            PageInfo<TagDO> pageInfo = new PageInfo<TagDO>(list);
            long total = pageInfo.getTotal();
            //创建一个返回值对象
            DataGridResult result = new DataGridResult(); 
            result.setData(list);
            result.setCount(total);
            return result;
        }
        PageHelper.startPage(query.getPage(), query.getLimit()); 
        List<TagDO> list = tagMapper.listTagByName(query.getName());
        //取记录总条数
        PageInfo<TagDO> pageInfo = new PageInfo<TagDO>(list);
        long total = pageInfo.getTotal();
        //创建一个返回值对象
        DataGridResult result = new DataGridResult(); 
        result.setData(list);
        result.setCount(total);
        return result;
    }

    @Override
    public YYBlogResult delete(Long id) {
        int count = tagReferMapper.countByTagId(id);
        if (count != 0) {
            return YYBlogResult.build(400, "请删除标签相关文章/笔记后再删除！");
        }
        tagMapper.delete(id);
        return YYBlogResult.ok();
    }

    @Override
    public YYBlogResult updateById(TagDO tag) {
         tagMapper.updateById(tag);
         return YYBlogResult.ok();
    }

}
