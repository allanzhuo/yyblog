package net.laoyeye.yyblog.model;

import java.io.Serializable;

/**
 * created by laoyeye on 2018/1/16 at 15:59
 */
public class Tag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
