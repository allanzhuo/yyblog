package net.laoyeye.yyblog.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.enums.JobStatusEnum;
import net.laoyeye.yyblog.mapper.TaskMapper;
import net.laoyeye.yyblog.model.TaskDO;
import net.laoyeye.yyblog.model.UserDO;
import net.laoyeye.yyblog.model.query.TaskQuery;
import net.laoyeye.yyblog.model.vo.TaskVO;
import net.laoyeye.yyblog.quartz.utils.QuartzManager;
import net.laoyeye.yyblog.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    QuartzManager quartzManager;

    @Override
    public TaskDO get(Long id) {
        return taskMapper.get(id);
    }

    @Override
    public DataGridResult list(TaskQuery query) {
        PageHelper.startPage(query.getPage(), query.getLimit()); 
        List<TaskVO> list = taskMapper.listTaskVoByDesc(query.getDescription());
        //取记录总条数
        PageInfo<TaskVO> pageInfo = new PageInfo<TaskVO>(list);
        long total = pageInfo.getTotal();
        //创建一个返回值对象
        DataGridResult result = new DataGridResult(); 
        result.setData(list);
        result.setCount(total);
        return result;
    }

    @Override
    public int save(TaskDO task) {
        task.setJobStatus(JobStatusEnum.STOP.getCode());
        task.setCreateUser(((UserDO)SecurityUtils.getSubject().getPrincipal()).getUsername());
        task.setCreateTime(new Date());
        task.setUpdateUser(((UserDO)SecurityUtils.getSubject().getPrincipal()).getUsername());
        task.setUpdateTime(new Date());
        return taskMapper.save(task);
    }

    @Override
    public int update(TaskDO task) {
        return taskMapper.update(task);
    }

    @Override
    public int remove(Long id) {
        try {
            TaskDO task = get(id);
            quartzManager.deleteJob(task);
            return taskMapper.remove(id);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int removeBatch(Long[] ids) {
        for (Long id : ids) {
            try {
                TaskDO task = get(id);
                quartzManager.deleteJob(task);
            } catch (SchedulerException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return taskMapper.removeBatch(ids);
    }

    @Override
    public void initSchedule() throws SchedulerException {
        // 这里获取任务信息数据
        List<TaskDO> jobList = taskMapper.list();
        for (TaskDO task : jobList) {
            if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())) {
                quartzManager.addJob(task);
            }
        }
    }

    @Override
    public void changeStatus(Long jobId, String jobStatus) throws SchedulerException {
        TaskDO task = get(jobId);
        if (task == null) {
            return;
        }
        if (JobStatusEnum.STOP.getCode().equals(jobStatus)) {
            quartzManager.deleteJob(task);
            task.setJobStatus(JobStatusEnum.STOP.getCode());
        } else {
            task.setJobStatus(JobStatusEnum.RUNNING.getCode());
            quartzManager.addJob(task);
        }
        update(task);
    }

    @Override
    public void updateCron(Long jobId) throws SchedulerException {
        TaskDO task = get(jobId);
        if (task == null) {
            return;
        }
        if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())) {
            quartzManager.updateJobCron(task);
        }
        update(task);
    }

    @Override
    public void run(TaskDO task) throws SchedulerException {
        quartzManager.runJobNow(task);
    }

}
