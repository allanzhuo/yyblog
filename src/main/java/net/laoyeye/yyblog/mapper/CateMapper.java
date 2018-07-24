package net.laoyeye.yyblog.mapper;

import java.util.List;

import net.laoyeye.yyblog.model.CateDO;

public interface CateMapper {
    List<CateDO> listAllCate();

    int countByCode(String code);
    
    int save(CateDO cate);
    
    int delete(Long id);
    
    int updateById(CateDO cate);
}
