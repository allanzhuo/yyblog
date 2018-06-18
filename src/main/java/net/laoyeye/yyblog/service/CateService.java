package net.laoyeye.yyblog.service;

import java.util.List;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.Cate;

public interface CateService {
	
	List<Cate> listAllCate();
	
	DataGridResult listPageCate(int page,int limit);
	
	YYBlogResult save(Cate cate);
	
	YYBlogResult delete(Long id);
	
	YYBlogResult updateById(Cate cate);
}
