package net.laoyeye.yyblog.service;

import java.util.List;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.Tag;
import net.laoyeye.yyblog.model.query.TagQuery;

public interface TagService {
	List<Tag> listAllTag();
	
	DataGridResult listPageTag(TagQuery query);
	
	YYBlogResult updateById(Tag tag);
	
	YYBlogResult delete(Long id);
}
