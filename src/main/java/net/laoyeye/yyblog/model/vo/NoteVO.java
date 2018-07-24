package net.laoyeye.yyblog.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * created by laoyeye on 2018/3/20 at 12:08
 */
public class NoteVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String title;
    private String content;
    private Date createTime;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
