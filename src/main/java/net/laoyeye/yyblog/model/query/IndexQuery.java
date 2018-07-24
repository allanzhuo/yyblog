package net.laoyeye.yyblog.model.query;

import net.laoyeye.yyblog.common.utils.Injection;
import net.laoyeye.yyblog.common.utils.UrlUtils;

/**
 * 先按置顶排序再按时间排序
 * created by laoyeye on 2018/2/4 at 14:14
 */
public class IndexQuery extends BaseQuery {


    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String title;

    private String textContent;

    private Long cateId;

    private String tag;


    public void setTitle(String title) {
        title = UrlUtils.urlDecode(title, "UTF-8");
        this.title = Injection.stripSqlXSS(title);
    }

    public void setTextContent(String textContent) {
        textContent = UrlUtils.urlDecode(textContent, "UTF-8");
        this.textContent = Injection.stripSqlXSS(textContent);
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    public String getTitle() {
        return title;
    }

    public String getTextContent() {
        return textContent;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        tag = UrlUtils.urlDecode(tag, "UTF-8");
        this.tag = tag;
    }

}
