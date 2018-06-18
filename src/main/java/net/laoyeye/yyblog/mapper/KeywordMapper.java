package net.laoyeye.yyblog.mapper;

import java.util.List;
import net.laoyeye.yyblog.model.Keyword;

public interface KeywordMapper {
	
	List<Keyword> listKeyword();
	
	List<Keyword> listValidKeyword();
	
	int saveKeyword(Keyword keyword);
	
	int countByWords(String words);
	
	int delete(long id);
	
	int update(Keyword keyword);
}
