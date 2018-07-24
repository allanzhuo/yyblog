package net.laoyeye.yyblog.service;

import org.quartz.SchedulerException;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.model.TaskDO;
import net.laoyeye.yyblog.model.query.TaskQuery;

/**
 * jobService
 * @author 小卖铺的老爷爷
 * @date 2018年6月23日
 * @website www.laoyeye.net
 */
public interface TaskService {
    
    TaskDO get(Long id);
    
    DataGridResult list(TaskQuery query);
    
    int save(TaskDO taskScheduleJob);
    
    int update(TaskDO taskScheduleJob);
    
    int remove(Long id);
    
    int removeBatch(Long[] ids);

    void initSchedule() throws SchedulerException;

    void changeStatus(Long jobId, String jobStatus) throws SchedulerException;

    void updateCron(Long jobId) throws SchedulerException;
    
    void run(TaskDO scheduleJob) throws SchedulerException;
}
