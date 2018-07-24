package net.laoyeye.yyblog.mapper;

import net.laoyeye.yyblog.model.TaskDO;
import net.laoyeye.yyblog.model.vo.TaskVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 任务类mapper
 * @author 小卖铺的老爷爷
 * @date 2018年6月29日
 * @website www.laoyeye.net
 */
public interface TaskMapper {

    TaskDO get(Long id);
    
    List<TaskDO> list();
    
    List<TaskVO> listTaskVoByDesc(@Param("desc") String desc);
    
    int save(TaskDO task);
    
    int update(TaskDO task);
    
    int remove(Long id);
    
    int removeBatch(Long[] ids);
}
