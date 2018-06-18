package net.laoyeye.yyblog.service;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.Article;
import net.laoyeye.yyblog.model.query.ArticleQuery;
import net.laoyeye.yyblog.model.vo.ArticleVo;

public interface ArticleService {
	/**
	 * 获取总的文章数
	 */
	int countAllArticle();
	/**
	 * 获取最新文章
	 */
	ArticleVo getLatestArticle();
	/**
	 * 创建文章
	 */
	YYBlogResult saveArticle(Article article, String tagName);
	/**
	 * 创建草稿，首页用
	 */
	YYBlogResult saveSimpleArticle(Article article);
	/**
	 * 分页查询文章
	 */
	DataGridResult listPageArticle(ArticleQuery articleQuery);
	/**
	 * 修改打赏状态
	 */
	YYBlogResult updateAppreciableById(Long id, Boolean appreciable);
	/**
	 * 修改评论状态
	 */
	YYBlogResult updateCommentedById(Long id, Boolean commented);
	/**
	 * 修改置顶状态
	 */
	YYBlogResult updateTopById(Long id, Boolean top);
	/**
	 * 根据id获取文章
	 */
	Article getArticleById(long id);
	/**
	 * 更新文章
	 */
	YYBlogResult updateArticle(Article article, String tagName);
	/**
	 * 删除文章
	 */
	YYBlogResult delete(Long id);
	/**
	 * 更新文章浏览次数
	 */
	int updateViewsById(long id);
	
	YYBlogResult updateApproveCntById(long id);
}
