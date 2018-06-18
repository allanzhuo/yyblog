package net.laoyeye.yyblog.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import net.laoyeye.yyblog.model.Article;
import net.laoyeye.yyblog.model.Cate;
import net.laoyeye.yyblog.model.Setting;

/**
 * created by laoyeye on 2018/1/30 at 13:21
 */
public class IndexVo implements Serializable {

    private Map<String, Object> settings;
    private int articleCount;
    private List<Cate> cateList;
    private List<ArticleVo> latestArticles;
    private List<TagVo> tagList;

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
	public List<Cate> getCateList() {
		return cateList;
	}
	public void setCateList(List<Cate> cateList) {
		this.cateList = cateList;
	}
	public List<ArticleVo> getLatestArticles() {
		return latestArticles;
	}
	public void setLatestArticles(List<ArticleVo> latestArticles) {
		this.latestArticles = latestArticles;
	}
	public List<TagVo> getTagList() {
		return tagList;
	}
	public void setTagList(List<TagVo> tagList) {
		this.tagList = tagList;
	}

}
