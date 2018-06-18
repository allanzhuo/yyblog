package net.laoyeye.yyblog.common;

import java.util.List;
/**
 * 数据返回响应体
 * @author 小卖铺的老爷爷
 * @date 2018年3月19日
 * @website www.laoyeye.net
 */
public class DataGridResult {
	private int code;
	private String message;
	private long count;
	private List<?> data;

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}

}
