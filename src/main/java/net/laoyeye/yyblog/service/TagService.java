package net.laoyeye.yyblog.service;

import java.util.List;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.TagDO;
import net.laoyeye.yyblog.model.query.TagQuery;

public interface TagService {
    List<TagDO> listAllTag();
    
    DataGridResult listPageTag(TagQuery query);
    
    YYBlogResult updateById(TagDO tag);
    
    YYBlogResult delete(Long id);
}
