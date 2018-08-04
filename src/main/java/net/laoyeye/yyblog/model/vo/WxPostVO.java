package net.laoyeye.yyblog.model.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 微信主页Vo
 * @author 小卖铺的老爷爷
 * @date 2018年8月5日
 * @website www.laoyeye.net
 */
public class WxPostVO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String avatar;
    private String nickname;
    private String cover;
    private String summary;
    private String content;
    private int views;
    private int collections;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
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
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
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
    public int getViews() {
        return views;
    }
    public void setViews(int views) {
        this.views = views;
    }
    public int getCollections() {
        return collections;
    }
    public void setCollections(int collections) {
        this.collections = collections;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
