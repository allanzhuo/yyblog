package net.laoyeye.yyblog.service;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.Comment;
import net.laoyeye.yyblog.model.query.CommentQuery;
import net.laoyeye.yyblog.model.vo.CommentVo;

public interface CommentService {
	/**
	 * 获取总的评论数
	 */
	int countAllComment();
	/**
	 * 获取最新的评论
	 */
	CommentVo getLatestComment();
	/**
	 * 前台评论查询
	 */
	DataGridResult listCommentByArticleId(CommentQuery query);
	/**
	 * 前台评论查询
	 */
	YYBlogResult insert(Comment comment);
	
	
}
