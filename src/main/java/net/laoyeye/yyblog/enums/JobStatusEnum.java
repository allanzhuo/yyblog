package net.laoyeye.yyblog.enums;

import net.laoyeye.yyblog.enums.EnumCodeList.CodeListItem;

/**
 * 用户操作枚举类
 * 
 * @author laoyeye
 *
 */
public enum JobStatusEnum implements CodeListItem {


    /**
	 * 0=停止
	 */
    STOP("0", "停止"),
    /**
	 * 1=运行
	 */
    RUNNING("1", "运行");

    private String value = null;
    private String code = null;

    private JobStatusEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static JobStatusEnum getEnumByKey(String key) {
        for (JobStatusEnum e : JobStatusEnum.values()) {
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
    public String getCode() {
        return code;
    }
}
