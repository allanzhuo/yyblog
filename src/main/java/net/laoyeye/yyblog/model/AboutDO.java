package net.laoyeye.yyblog.model;

import java.io.Serializable;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年1月12日
 * @website www.laoyeye.net
 */
public class AboutDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String tab;

    private String name;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
