package net.laoyeye.yyblog.service;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.CommentDO;
import net.laoyeye.yyblog.model.query.CommentQuery;
import net.laoyeye.yyblog.model.vo.CommentVO;

public interface CommentService {
    /**
     * 获取总的评论数
     */
    int countAllComment();
    /**
     * 获取最新的评论
     */
    CommentVO getLatestComment();
    /**
     * 前台评论查询
     */
    DataGridResult listCommentByArticleId(CommentQuery query);
    /**
     * 前台评论查询
     */
    YYBlogResult insert(CommentDO comment);
    
    
}
