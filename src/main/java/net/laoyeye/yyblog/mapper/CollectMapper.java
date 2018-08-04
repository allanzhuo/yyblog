package net.laoyeye.yyblog.mapper;

import java.util.List;

import net.laoyeye.yyblog.model.CollectDO;
import net.laoyeye.yyblog.model.vo.WxPostVO;

public interface CollectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CollectDO record);

    int insertSelective(CollectDO record);

    CollectDO getByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CollectDO record);

    int updateByPrimaryKey(CollectDO record);
    
    int updateByUserIdAndArticleId(CollectDO record);
    
    List<WxPostVO> listCollectByUserId(String userId);
}