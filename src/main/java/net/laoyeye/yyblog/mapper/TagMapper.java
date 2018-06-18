package net.laoyeye.yyblog.mapper;

import java.util.List;
import net.laoyeye.yyblog.model.Tag;

public interface TagMapper {
	List<Tag> listAllTag();

	List<Tag> listTagByName(String name);
	
	Tag getTagByName(String name);
	
	int updateById(Tag tag);
	
	int delete(Long id);
	
	int saveTag(Tag tag);
	
	int countByName(String name);
	
	List<Tag> listTagByReferId(Long articleId);
}
