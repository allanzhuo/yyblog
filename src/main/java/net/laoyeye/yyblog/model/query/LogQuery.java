package net.laoyeye.yyblog.model.query;

public class LogQuery extends BaseQuery {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // 用户名
    private String username;
    // 操作
    private String operation;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getOperation() {
        return operation;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }

}
