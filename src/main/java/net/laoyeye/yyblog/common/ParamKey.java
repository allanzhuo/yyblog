package net.laoyeye.yyblog.common;

/**
 * created by laoyeye on 2018/1/22 at 13:37
 */
public interface ParamKey {

    /**
     * 是否全局开放评论
     */
    String ALL_COMMENT_OPEN = "all_comment_open";

    /**
     * 网站logo的文字
     */
    String WEBSITE_TITLE = "website_title";

    /**
     * 页脚文字
     */
    String FOOTER_WORDS = "footer_words";

    /**
     * 首页置顶文字
     */
    String INDEX_TOP_WORD = "index_top_words";

    /**
     * 导航菜单
     */
    String MENU_HOME = "menu_home";
    String MENU_NOTE = "menu_note";
    String MENU_LINK = "menu_link";
    String MENU_LINK_ICON = "menu_link_icon";
    String MENU_LINK_HREF = "menu_link_href";
    String MENU_LINK_SHOW = "menu_link_show";
    String MENU_MINE = "menu_mine";

    /**
     * 微信支付二维码图片地址
     */
    String WECHAT_PAY = "wechat_pay";

    /**
     * 支付宝支付二维码图片地址
     */
    String ALIPAY = "alipay";

    String APP_ID = "app_id";
    String APP_KEY = "app_key";

    /**
     * 是否开放qq登录
     */
    String QQ_LOGIN = "qq_login";

    /**
     * 统计代码
     */
    String STATISTICS_CODE = "statistics_code";

    /**
     * 信息板
     */
    String INFO_LABEL = "info_label";
}
