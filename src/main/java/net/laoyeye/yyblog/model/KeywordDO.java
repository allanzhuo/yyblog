package net.laoyeye.yyblog.model;
/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年2月2日
 * @website www.laoyeye.net
 */
public class KeywordDO {
    private Long id;

    private String words;

    private Boolean enable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words == null ? null : words.trim();
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}