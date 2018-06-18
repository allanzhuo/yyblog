package net.laoyeye.yyblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.laoyeye.yyblog.model.Article;
import net.laoyeye.yyblog.model.query.IndexQuery;
import net.laoyeye.yyblog.model.vo.ArticleVo;

public interface ArticleMapper {
	int countAllArticle();
	
	ArticleVo getLatestArticle();
	
	int countByCateId(Long cateId);
	
	int saveArticle(Article article);
	
	List<ArticleVo> listArticleByTitle(@Param("title") String title);
	
	List<ArticleVo> listIndexArticle(IndexQuery query);
	
	int update(Article article);
	
	Article getArticleById(long id);
	
	int delete(long id);
	
	int updateViewsById(long id);
	
	int updateApproveCntById(long articleId);
}
