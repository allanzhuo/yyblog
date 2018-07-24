package net.laoyeye.yyblog.model.query;

public class TaskQuery extends BaseQuery {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // 任务描述
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
