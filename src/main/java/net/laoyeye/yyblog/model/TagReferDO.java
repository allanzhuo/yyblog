package net.laoyeye.yyblog.model;

import java.io.Serializable;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年1月16日
 * @website www.laoyeye.net
 */
public class TagReferDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long referId;
    private Long tagId;
    private Boolean isShow;
    private String type;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getReferId() {
        return referId;
    }
    public void setReferId(Long referId) {
        this.referId = referId;
    }
    public Long getTagId() {
        return tagId;
    }
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
    public Boolean getIsShow() {
        return isShow;
    }
    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
}
