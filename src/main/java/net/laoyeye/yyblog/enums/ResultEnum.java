package net.laoyeye.yyblog.enums;

import net.laoyeye.yyblog.enums.EnumCodeList.CodeListItem;

/**
 * 老爷爷自定义响应码
 * @author 小卖铺的老爷爷
 * @date 2018年4月24日
 * @website www.laoyeye.net
 */
public enum ResultEnum {

	NO_RECORDS(102, "查无记录."),
	
	UNSURPPORT_OPT(103, "暂时不支持此操作."),
	
	SUCCESS(200, "操作成功."),
	
	NOT_FOUND(404, "非法的操作."),
	
	UN_AUTHORIZED(403, "你没有操作权限."),
	
	UNKONW_ERROR(500, "系统开了小差."),
	
	FAIL(505, "操作失败."),
	
	USERNAME_EXISTS(300, "用户名已存在.");

	private String value = null;
	private Integer code = null;

	private ResultEnum(Integer _code, String _value) {
		this.value = _value;
		this.code = _code;
	}

	public static ResultEnum getEnumByKey(String key) {
		for (ResultEnum e : ResultEnum.values()) {
			if (e.getCode().equals(key)) {
				return e;
			}
		}
		return null;
	}

	/** 获取value */
	public String getValue() {
		return value;
	}

	/** 获取code */
	public Integer getCode() {
		return code;
	}
}