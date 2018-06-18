package net.laoyeye.yyblog.service;

import java.util.List;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.Keyword;
import net.laoyeye.yyblog.model.query.BaseQuery;

public interface KeywordService {

	/**
	 * 分页查询关键字
	 */
	DataGridResult listPageKeyword(BaseQuery query);
	/**
	 * 查询所有有效的关键字
	 */
	List<Keyword> listValidKeyword();
	/**
	 * 保存关键字
	 */
	YYBlogResult saveKeyword(Keyword keyword);
	/**
	 * 保存关键字
	 */
	YYBlogResult delete(long id);
	/**
	 * 修改关键字状态/内容
	 */
	YYBlogResult update(Keyword keyword);
}
