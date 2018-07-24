package net.laoyeye.yyblog.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * created by laoyeye on 2018/3/20 at 12:08
 */
public class ArticleVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String cateName;
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
    private String authorName;
    private Date createTime;
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
    public String getCateName() {
        return cateName;
    }
    public void setCateName(String cateName) {
        this.cateName = cateName;
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
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
