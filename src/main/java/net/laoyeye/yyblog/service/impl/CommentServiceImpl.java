package net.laoyeye.yyblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.mapper.CommentMapper;
import net.laoyeye.yyblog.model.CommentDO;
import net.laoyeye.yyblog.model.query.CommentQuery;
import net.laoyeye.yyblog.model.vo.CommentVO;
import net.laoyeye.yyblog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public int countAllComment() {

		return commentMapper.countAllComment();
	}

	@Override
	public CommentVO getLatestComment() {

		return commentMapper.getLatestComment();
	}

	@Override
	public DataGridResult listCommentByArticleId(CommentQuery query) {
		PageHelper.startPage(query.getPage(), query.getLimit()); 
		List<CommentVO> list = commentMapper.listCommentByArticleId(query.getArticleId());
		//取记录总条数
		PageInfo<CommentVO> pageInfo = new PageInfo<CommentVO>(list);
		long total = pageInfo.getTotal();
		//创建一个返回值对象
		DataGridResult result = new DataGridResult(); 
		result.setData(list);
		result.setCount(total);
		return result;
	}

	@Override
	public YYBlogResult insert(CommentDO comment) {
		commentMapper.insert(comment);
		return YYBlogResult.ok();
	}



}
