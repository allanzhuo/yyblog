package net.laoyeye.yyblog.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.common.utils.FilterHtml;
import net.laoyeye.yyblog.common.utils.IDUtils;
import net.laoyeye.yyblog.mapper.NoteMapper;
import net.laoyeye.yyblog.mapper.TagMapper;
import net.laoyeye.yyblog.mapper.TagReferMapper;
import net.laoyeye.yyblog.model.NoteDO;
import net.laoyeye.yyblog.model.TagDO;
import net.laoyeye.yyblog.model.TagReferDO;
import net.laoyeye.yyblog.model.query.NoteQuery;
import net.laoyeye.yyblog.model.vo.NoteVO;
import net.laoyeye.yyblog.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService {
	@Autowired
	private NoteMapper noteMapper;
	@Autowired
	private TagMapper tagMapper;
	@Autowired
	private TagReferMapper tagReferMapper;

	@Override
	public int countAllNote() {

		return noteMapper.countAllNote();
	}

	@Override
	public NoteVO getLatestNote() {

		return noteMapper.getLatestNote();
	}

	@Transactional
	@Override
	public YYBlogResult saveNote(NoteDO note, String tagName) {

		//插入笔记
		note.setId(IDUtils.genId());
		note.setTop(false);
		note.setIsShow(true);
		getTextContentByContent(note);
		note.setCreateTime(new Date());

		noteMapper.saveNote(note);
		if (!StringUtils.isEmpty(tagName)) {
			//标签处理
			String[] tagNameArray = tagName.split(",");
			TagDO tag = new TagDO();
			TagReferDO tagRefer = new TagReferDO();
			for (String name : Arrays.asList(tagNameArray)) {
				if (tagMapper.countByName(name) == 0) {
					tag.setId(IDUtils.genId());
					tag.setName(name);
					tagMapper.saveTag(tag);
				} else {
					tag = tagMapper.getTagByName(name);
				}
				tagRefer.setId(IDUtils.genId());
				tagRefer.setReferId(note.getId());
				tagRefer.setTagId(tag.getId());
				tagRefer.setIsShow(true);
				tagRefer.setType("2");
				tagReferMapper.saveTagRefer(tagRefer);
			}
		}
		return YYBlogResult.ok();
	}


	@Override
	public DataGridResult listPageNote(NoteQuery query) {
		PageHelper.startPage(query.getPage(), query.getLimit()); 
		List<NoteDO> list = noteMapper.listNoteByTitle(query.getTitle());
		//取记录总条数
		PageInfo<NoteDO> pageInfo = new PageInfo<NoteDO>(list);
		long total = pageInfo.getTotal();
		//创建一个返回值对象
		DataGridResult result = new DataGridResult(); 
		result.setData(list);
		result.setCount(total);
		return result;
	}

	@Transactional
	@Override
	public YYBlogResult updateIsShowById(Long id, Boolean isShow) {
		NoteDO note = new NoteDO();
		note.setId(id);
		note.setIsShow(isShow);
		note.setUpdateTime(new Date());
		noteMapper.update(note);
		return YYBlogResult.ok();
	}

	@Transactional
	@Override
	public YYBlogResult updateTopById(Long id, Boolean top) {
		NoteDO note = new NoteDO();
		note.setId(id);
		note.setTop(top);
		note.setUpdateTime(new Date());
		noteMapper.update(note);
		return YYBlogResult.ok();
	}

	@Transactional
	@Override
	public YYBlogResult delete(Long id) {
		tagReferMapper.deleteByReferId(id);
		noteMapper.delete(id);
		return YYBlogResult.ok();
	}


	@Override
	public NoteDO getNoteById(Long id) {

		return noteMapper.getNoteById(id);
	}

	@Override
	public YYBlogResult updateNote(NoteDO note, String tagName) {
		note.setUpdateTime(new Date());
		getTextContentByContent(note);
		noteMapper.update(note);
		tagReferMapper.deleteByReferId(note.getId());
		String[] tagNameArray = tagName.split(",");
		TagDO tag = new TagDO();
		TagReferDO tagRefer = new TagReferDO();
		for (String name : Arrays.asList(tagNameArray)) {
			if (tagMapper.countByName(name) == 0) {
				tag.setId(IDUtils.genId());
				tag.setName(name);
				tagMapper.saveTag(tag);
			} else {
				tag = tagMapper.getTagByName(name);
			}
			tagRefer.setId(IDUtils.genId());
			tagRefer.setReferId(note.getId());
			tagRefer.setTagId(tag.getId());
			tagRefer.setIsShow(true);
			tagRefer.setType("2");
			tagReferMapper.saveTagRefer(tagRefer);
		}
		return YYBlogResult.ok();
	}

	private void getTextContentByContent(NoteDO note) {
		String textContent = FilterHtml.filterHtml(note.getContent().trim());
		textContent = StringUtils.trimAllWhitespace(textContent);
		note.setTextContent(textContent);
	}
}
