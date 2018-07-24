package net.laoyeye.yyblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.laoyeye.yyblog.model.LogDO;

/**
 * 系统日志
 * @author 小卖铺的老爷爷
 * @date 2018年7月1日
 * @website www.laoyeye.net
 */
public interface LogMapper {

    LogDO get(Long id);
    
    List<LogDO> listByUsernameAndOperation(@Param("username")String username,@Param("operation")String operation);
    
    int save(LogDO log);
    
    int update(LogDO log);
    
    int remove(Long id);
    
    int removeBatch(Long[] ids);
}
