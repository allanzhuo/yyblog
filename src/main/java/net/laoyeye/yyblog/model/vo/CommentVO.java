package net.laoyeye.yyblog.model.vo;

import net.laoyeye.yyblog.model.CommentDO;

/**
 * created by laoyeye on 2018/3/20 at 12:08
 */
public class CommentVO extends CommentDO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String nickname;
    private String title;
    private String avatar;
    
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
