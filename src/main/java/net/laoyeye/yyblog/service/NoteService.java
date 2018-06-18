package net.laoyeye.yyblog.service;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.Note;
import net.laoyeye.yyblog.model.query.NoteQuery;
import net.laoyeye.yyblog.model.vo.NoteVo;

public interface NoteService {
	/**
	 * 获取总的笔记数
	 */
	int countAllNote();
	/**
	 * 获取最新笔记
	 */
	NoteVo getLatestNote();
	/**
	 * 新增保存笔记
	 */
	YYBlogResult saveNote(Note note, String tagName);
	/**
	 * 查询笔记列表
	 */
	DataGridResult listPageNote(NoteQuery query);
	/**
	 * 修改状态
	 */
	YYBlogResult updateIsShowById(Long id, Boolean isShow);
	/**
	 * 修改置顶
	 */
	YYBlogResult updateTopById(Long id, Boolean top);
	/**
	 * 修改笔记
	 */
	YYBlogResult updateNote(Note note, String tagName);
	/**
	 * 删除笔记
	 */
	YYBlogResult delete(Long id);
	/**
	 * 根据ID获取笔记
	 */
	Note getNoteById(Long id);
}
