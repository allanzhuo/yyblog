package net.laoyeye.yyblog.mapper;

import java.util.List;
import net.laoyeye.yyblog.model.TagDO;

public interface TagMapper {
	List<TagDO> listAllTag();

	List<TagDO> listTagByName(String name);
	
	TagDO getTagByName(String name);
	
	int updateById(TagDO tag);
	
	int delete(Long id);
	
	int saveTag(TagDO tag);
	
	int countByName(String name);
	
	List<TagDO> listTagByReferId(Long articleId);
}
