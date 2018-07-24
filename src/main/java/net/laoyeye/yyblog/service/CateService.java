package net.laoyeye.yyblog.service;

import java.util.List;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.CateDO;

public interface CateService {
    
    List<CateDO> listAllCate();
    
    DataGridResult listPageCate(int page,int limit);
    
    YYBlogResult save(CateDO cate);
    
    YYBlogResult delete(Long id);
    
    YYBlogResult updateById(CateDO cate);
}
