package net.laoyeye.yyblog.mapper;

import java.util.List;

import net.laoyeye.yyblog.model.TagRefer;
import net.laoyeye.yyblog.model.vo.TagVo;

public interface TagReferMapper {
	
	int countByTagId(Long tagId);
	
	int saveTagRefer(TagRefer tagRefer);
	
	List<String> listNameByArticleId(long referId);
	
	int deleteByReferId(long referId);
	
	List<TagVo> listNameAndCnt();
}
