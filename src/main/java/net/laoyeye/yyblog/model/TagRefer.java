package net.laoyeye.yyblog.model;

import java.io.Serializable;

/**
 * created by laoyeye on 2018/1/16 at 16:58
 */
public class TagRefer implements Serializable {

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
