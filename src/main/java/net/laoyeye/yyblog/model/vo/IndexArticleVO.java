package net.laoyeye.yyblog.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年1月2日
 * @website www.laoyeye.net
 */
public class IndexArticleVO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int code;
    private List<ArticleVO> data;
    private Map<Long, Object> tags;
    private long totalPage;
    private long totalCount;
    
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public List<ArticleVO> getData() {
        return data;
    }
    public void setData(List<ArticleVO> data) {
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
