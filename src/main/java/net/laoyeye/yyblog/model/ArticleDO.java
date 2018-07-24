package net.laoyeye.yyblog.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年3月20日
 * @website www.laoyeye.net
 */
public class ArticleDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String title;
    private Long cateId;
    private String cover;
    private String summary;
    private String content;
    private String textContent;
    private Integer views;
    private Integer approveCnt;
    private Boolean commented;
    private Boolean appreciable;
    private Boolean draft;
    private Boolean top;
    private Long authorId;
    private Date createTime;
    private Date updateTime;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getCateId() {
        return cateId;
    }
    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }
    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTextContent() {
        return textContent;
    }
    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
    public Integer getViews() {
        return views;
    }
    public void setViews(Integer views) {
        this.views = views;
    }
    public Integer getApproveCnt() {
        return approveCnt;
    }
    public void setApproveCnt(Integer approveCnt) {
        this.approveCnt = approveCnt;
    }
    public Boolean getCommented() {
        return commented;
    }
    public void setCommented(Boolean commented) {
        this.commented = commented;
    }
    public Boolean getAppreciable() {
        return appreciable;
    }
    public void setAppreciable(Boolean appreciable) {
        this.appreciable = appreciable;
    }
    public Boolean getDraft() {
        return draft;
    }
    public void setDraft(Boolean draft) {
        this.draft = draft;
    }
    public Boolean getTop() {
        return top;
    }
    public void setTop(Boolean top) {
        this.top = top;
    }
    public Long getAuthorId() {
        return authorId;
    }
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
