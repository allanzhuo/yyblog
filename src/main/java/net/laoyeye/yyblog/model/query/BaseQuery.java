package net.laoyeye.yyblog.model.query;

import java.io.Serializable;

public class BaseQuery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int page;

    private int limit;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
    
}
