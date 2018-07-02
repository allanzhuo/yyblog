package net.laoyeye.yyblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.laoyeye.yyblog.model.NoteDO;
import net.laoyeye.yyblog.model.vo.NoteVO;

public interface NoteMapper {

	int countAllNote();

	NoteVO getLatestNote();
	
	int saveNote(NoteDO note);
	
	List<NoteDO> listNoteByTitle(@Param("title")String title);
	
	int update(NoteDO note);
	
	int delete(long id);
	
	NoteDO getNoteById(Long id);
}
