package net.laoyeye.yyblog.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import net.laoyeye.yyblog.model.Article;

/**
 * created by laoyeye on 2018/2/4 at 12:31
 */

public class IndexArticleVo implements Serializable {
	private int code;
    private List<ArticleVo> data;
    private Map<Long, Object> tags;
    private long totalPage;
    private long totalCount;
    
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public List<ArticleVo> getData() {
		return data;
	}
	public void setData(List<ArticleVo> data) {
		this.data = data;
	}
	public Map<Long, Object> getTags() {
		return tags;
	}
	public void setTags(Map<Long, Object> tags) {
		this.tags = tags;
	}
	public long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
    
}
