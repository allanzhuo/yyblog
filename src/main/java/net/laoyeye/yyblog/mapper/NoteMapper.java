package net.laoyeye.yyblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.laoyeye.yyblog.model.Note;
import net.laoyeye.yyblog.model.vo.NoteVo;

public interface NoteMapper {

	int countAllNote();

	NoteVo getLatestNote();
	
	int saveNote(Note note);
	
	List<Note> listNoteByTitle(@Param("title")String title);
	
	int update(Note note);
	
	int delete(long id);
	
	Note getNoteById(Long id);
}
