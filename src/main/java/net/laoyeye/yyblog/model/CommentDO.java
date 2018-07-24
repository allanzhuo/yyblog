package net.laoyeye.yyblog.model;

import java.io.Serializable;
import java.util.Date;
import static java.lang.Boolean.TRUE;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年1月2日
 * @website www.laoyeye.net
 */
public class CommentDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private Long articleId;
    private String content;
    private String ipAddr;
    private String ipCnAddr;
    private String parentId;
    private Boolean enable = TRUE;
    private Date createTime;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getArticleId() {
        return articleId;
    }
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getIpAddr() {
        return ipAddr;
    }
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
    public String getIpCnAddr() {
        return ipCnAddr;
    }
    public void setIpCnAddr(String ipCnAddr) {
        this.ipCnAddr = ipCnAddr;
    }
    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public Boolean getEnable() {
        return enable;
    }
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}
