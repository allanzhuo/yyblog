package net.laoyeye.yyblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.laoyeye.yyblog.model.ArticleDO;
import net.laoyeye.yyblog.model.query.IndexQuery;
import net.laoyeye.yyblog.model.vo.ArticleVO;

public interface ArticleMapper {
	int countAllArticle();
	
	ArticleVO getLatestArticle();
	
	int countByCateId(Long cateId);
	
	int saveArticle(ArticleDO article);
	
	List<ArticleVO> listArticleByTitle(@Param("title") String title);
	
	List<ArticleVO> listIndexArticle(IndexQuery query);
	
	int update(ArticleDO article);
	
	ArticleDO getArticleById(long id);
	
	int delete(long id);
	
	int updateViewsById(long id);
	
	int updateApproveCntById(long articleId);
}
