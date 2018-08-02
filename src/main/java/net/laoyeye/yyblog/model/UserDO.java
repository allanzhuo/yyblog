package net.laoyeye.yyblog.model;

import java.io.Serializable;
import java.util.Date;

/**
 * user实体类
 * @author 小卖铺的老爷爷
 * @date 2018年1月13日
 * @website www.laoyeye.net
 */
public class UserDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String nickname;
    private String password;
    private String avatar;
    private String email;
    private Boolean enable;
    private String openId;
    private String wxOpenId;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Boolean getEnable() {
        return enable;
    }
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
    public String getOpenId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getWxOpenId() {
        return wxOpenId;
    }
    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
}
