package net.laoyeye.yyblog.mapper;

import java.util.List;

import net.laoyeye.yyblog.model.Comment;
import net.laoyeye.yyblog.model.vo.CommentVo;

public interface CommentMapper {

	int countAllComment();

	CommentVo getLatestComment();
	
	List<CommentVo> listCommentByArticleId(Long articleId);
	
	int insert(Comment comment);
}
