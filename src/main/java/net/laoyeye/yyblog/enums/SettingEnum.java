package net.laoyeye.yyblog.enums;

import net.laoyeye.yyblog.enums.EnumCodeList.CodeListItem;

/**
 * 用户操作枚举类
 * 
 * @author laoyeye
 *
 */
public enum SettingEnum implements CodeListItem {


    /**
	 * secret_id=腾讯云存储的secret_id
	 */
	SECRET_ID("secret_id", "腾讯云存储的secret_id"),
    /**
	 * secret_key=腾讯云存储的secret_key
	 */
	SECRET_KEY("secret_key", "腾讯云存储的secret_key"),
	/**
	 * bucket=COS存储桶名称
	 */
	BUCKET("bucket", "COS存储桶名称"),
    /**
	 * region=bucket的区域
	 */
	REGION("region", "bucket的区域");

    private String value = null;
    private String code = null;

    private SettingEnum(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static SettingEnum getEnumByKey(String key) {
        for (SettingEnum e : SettingEnum.values()) {
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
