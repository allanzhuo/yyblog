package net.laoyeye.yyblog.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.mapper.LogMapper;
import net.laoyeye.yyblog.model.LogDO;
import net.laoyeye.yyblog.model.query.LogQuery;
import net.laoyeye.yyblog.service.LogService;

@Service
public class LogServiceImpl implements LogService {
	@Autowired
	LogMapper logMapper;

	@Async
	@Override
	public void save(LogDO log) {
		 logMapper.save(log);
	}

	@Override
	public DataGridResult list(LogQuery query) {
		PageHelper.startPage(query.getPage(), query.getLimit()); 
		List<LogDO> list = logMapper.listByUsernameAndOperation(query.getUsername(),query.getOperation());
		//取记录总条数
		PageInfo<LogDO> pageInfo = new PageInfo<LogDO>(list);
		long total = pageInfo.getTotal();
		//创建一个返回值对象
		DataGridResult result = new DataGridResult();
		result.setData(list);
		result.setCount(total);
		return result;
	}

	@Override
	public int remove(Long id) {

		return logMapper.remove(id);
	}

	@Override
	public int removeBatch(Long[] ids) {

		return logMapper.removeBatch(ids);
	}
}
