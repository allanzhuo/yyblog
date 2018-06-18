package net.laoyeye.yyblog.common;

/**
 * NK编辑器返回响应体
 * @author 小卖铺的老爷爷
 * @date 2018年3月29日
 * @website www.laoyeye.net
 */
public class NKBlogResult {

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object item;

    public static NKBlogResult build(Integer code, String message, Object data) {
        return new NKBlogResult(code, message, data);
    }

    public static NKBlogResult ok(Object data) {
        return new NKBlogResult(data);
    }

    public static NKBlogResult ok() {
        return new NKBlogResult(null);
    }

    public NKBlogResult() {

    }

    public static NKBlogResult build(Integer code, String message) {
        return new NKBlogResult(code, message, null);
    }

    public NKBlogResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.item = data;
    }

    public NKBlogResult(Object data) {
        this.code = 000;
        this.message = "上传成功";
        this.item = data;
    }

//    public Boolean isOK() {
//        return this.code == 200;
//    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    public Object getItem() {
		return item;
	}

	public void setItem(Object item) {
		this.item = item;
	}

}
