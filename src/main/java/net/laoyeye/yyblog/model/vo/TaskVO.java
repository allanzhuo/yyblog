package net.laoyeye.yyblog.model.vo;

import java.io.Serializable;

public class TaskVO implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    private Long id;
    // 任务名
    private String jobName;
    // 任务描述
    private String description;
    // 任务分组
    private String jobGroup;
    // 任务执行时调用哪个类的方法 包名+类名
    private String beanClass;
    // cron表达式
    private String cronExpression;
    // 任务状态
    private String jobStatus;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getJobName() {
        return jobName;
    }
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getJobGroup() {
        return jobGroup;
    }
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }
    public String getBeanClass() {
        return beanClass;
    }
    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }
    public String getCronExpression() {
        return cronExpression;
    }
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
    public String getJobStatus() {
        return jobStatus;
    }
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
