package net.laoyeye.yyblog.model.query;

public class UserQuery extends BaseQuery {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
}
