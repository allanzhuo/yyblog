package net.laoyeye.yyblog.mapper;

import java.util.List;
import net.laoyeye.yyblog.model.KeywordDO;

public interface KeywordMapper {
    
    List<KeywordDO> listKeyword();
    
    List<KeywordDO> listValidKeyword();
    
    int saveKeyword(KeywordDO keyword);
    
    int countByWords(String words);
    
    int delete(long id);
    
    int update(KeywordDO keyword);
}
