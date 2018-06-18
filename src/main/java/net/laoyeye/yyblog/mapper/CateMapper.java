package net.laoyeye.yyblog.mapper;

import java.util.List;

import net.laoyeye.yyblog.model.Cate;

public interface CateMapper {
	List<Cate> listAllCate();

	int countByCode(String code);
	
	int save(Cate cate);
	
	int delete(Long id);
	
	int updateById(Cate cate);
}
