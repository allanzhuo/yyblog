package net.laoyeye.yyblog.service;

import org.springframework.stereotype.Service;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.model.LogDO;
import net.laoyeye.yyblog.model.query.LogQuery;

@Service
public interface LogService {
	void save(LogDO logDO);
	
	DataGridResult list(LogQuery query);
	
	int remove(Long id);
	
	int removeBatch(Long[] ids);
}
