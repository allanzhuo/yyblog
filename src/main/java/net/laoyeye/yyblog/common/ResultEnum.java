package net.laoyeye.yyblog.common;

/**
 * 老爷爷自定义响应码
 * @author 小卖铺的老爷爷
 * @date 2018年4月24日
 * @website www.laoyeye.net
 */
public enum ResultEnum {

    SUCCESS("000", "操作成功."),
    FAIL("001", "操作失败."),
    UNKONW_ERROR("101", "系统开了小差."),
    NO_RECORDS("102", "查无记录."),
    UNSURPPORT_OPT("103", "暂时不支持此操作."),
    NOT_FOUND("404", "非法的操作."),
    UN_AUTHORIZED("403", "你没有操作权限."),
    USERNAME_EXISTS("300", "用户名已存在.");

    private String code;

    private String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}