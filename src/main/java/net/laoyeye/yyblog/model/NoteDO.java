package net.laoyeye.yyblog.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年1月14日
 * @website www.laoyeye.net
 */
public class NoteDO implements Serializable {


    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String textContent;
    private String content;
    private Boolean top;
    private Boolean isShow;
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
    public String getTextContent() {
        return textContent;
    }
    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Boolean getTop() {
        return top;
    }
    public void setTop(Boolean top) {
        this.top = top;
    }
    public Boolean getIsShow() {
        return isShow;
    }
    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
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
