package net.laoyeye.yyblog.mapper;

import java.util.List;

import net.laoyeye.yyblog.model.CommentDO;
import net.laoyeye.yyblog.model.vo.CommentVO;

public interface CommentMapper {

    int countAllComment();

    CommentVO getLatestComment();
    
    List<CommentVO> listCommentByArticleId(Long articleId);
    
    int insert(CommentDO comment);
}
