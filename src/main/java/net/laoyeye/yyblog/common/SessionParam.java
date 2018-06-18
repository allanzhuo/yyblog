package net.laoyeye.yyblog.common;

/**
 * session参数定义
 * @author 小卖铺的老爷爷
 * @date 2018年3月20日
 * @website www.laoyeye.net
 */
public interface SessionParam {

    /**
     * Cookie的名称
     */
    String COOKIE_NAME = "DA4DE6E44EEEC32C30C278A452C355B9";

    /**
     * cookie分隔符
     */
    String COOKIE_SPLIT = ",";

    /**
     * 存放用户信息的cookie的密钥，用来加密解密用
     */
    String COOKIE_SECRET_KEY = "amk[dosedoslda]a";

    /**
     * 前后台登录地址
     */
    String LOGIN_URL = "/login";

    /**
     * 后台首页地址
     */
    String MANAGEMENT_INDEX = "/management/index";
    
    /**
     * 对象存储地址
     */
    String ACCESS_URL = "http://images.laoyeye.net/";
}
