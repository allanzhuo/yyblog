package net.laoyeye.yyblog.service;

import java.util.List;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.KeywordDO;
import net.laoyeye.yyblog.model.query.BaseQuery;

public interface KeywordService {

	/**
	 * 分页查询关键字
	 */
	DataGridResult listPageKeyword(BaseQuery query);
	/**
	 * 查询所有有效的关键字
	 */
	List<KeywordDO> listValidKeyword();
	/**
	 * 保存关键字
	 */
	YYBlogResult saveKeyword(KeywordDO keyword);
	/**
	 * 保存关键字
	 */
	YYBlogResult delete(long id);
	/**
	 * 修改关键字状态/内容
	 */
	YYBlogResult update(KeywordDO keyword);
}
