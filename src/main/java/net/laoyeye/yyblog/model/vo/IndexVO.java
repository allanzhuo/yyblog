package net.laoyeye.yyblog.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import net.laoyeye.yyblog.model.CateDO;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年1月2日
 * @website www.laoyeye.net
 */
public class IndexVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Map<String, Object> settings;
    private int articleCount;
    private List<CateDO> cateList;
    private List<ArticleVO> latestArticles;
    private List<TagVO> tagList;

    public Map<String, Object> getSettings() {
        return settings;
    }
    public void setSettings(Map<String, Object> settings) {
        this.settings = settings;
    }
    public int getArticleCount() {
        return articleCount;
    }
    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }
    public List<CateDO> getCateList() {
        return cateList;
    }
    public void setCateList(List<CateDO> cateList) {
        this.cateList = cateList;
    }
    public List<ArticleVO> getLatestArticles() {
        return latestArticles;
    }
    public void setLatestArticles(List<ArticleVO> latestArticles) {
        this.latestArticles = latestArticles;
    }
    public List<TagVO> getTagList() {
        return tagList;
    }
    public void setTagList(List<TagVO> tagList) {
        this.tagList = tagList;
    }

}
