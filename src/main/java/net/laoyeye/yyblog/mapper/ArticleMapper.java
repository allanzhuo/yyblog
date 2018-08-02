package net.laoyeye.yyblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.laoyeye.yyblog.model.ArticleDO;
import net.laoyeye.yyblog.model.query.IndexQuery;
import net.laoyeye.yyblog.model.vo.ArticleVO;
import net.laoyeye.yyblog.model.vo.WxPostVO;

public interface ArticleMapper {
    int countAllArticle();
    //前台查询
    int countArticle();

    ArticleVO getLatestArticle();

    int countByCateId(Long cateId);

    int saveArticle(ArticleDO article);

    List<ArticleVO> listArticleByTitle(@Param("title") String title);
    //前台查询
    List<ArticleVO> listSimilarsArticle();
    //前台查询
    List<ArticleVO> listIndexArticle(IndexQuery query);
    //小程序首页查询
    List<WxPostVO> listWxIndex();
    //小程序首页Banner
    List<WxPostVO> listWxTop();

    int update(ArticleDO article);

    ArticleDO getArticleById(long id);

    int delete(long id);

    int updateViewsById(long id);

    int updateApproveCntById(long articleId);

}
