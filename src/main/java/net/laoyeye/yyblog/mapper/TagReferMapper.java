package net.laoyeye.yyblog.mapper;

import java.util.List;

import net.laoyeye.yyblog.model.TagReferDO;
import net.laoyeye.yyblog.model.vo.TagVO;

public interface TagReferMapper {
	
	int countByTagId(Long tagId);
	
	int saveTagRefer(TagReferDO tagRefer);
	
	List<String> listNameByArticleId(long referId);
	
	int deleteByReferId(long referId);
	
	List<TagVO> listNameAndCnt();
}
