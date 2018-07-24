package net.laoyeye.yyblog.model.query;

/**
 * 评论查询
 * @author 小卖铺的老爷爷
 * @date 2018年5月5日
 * @website www.laoyeye.net
 */
public class CommentQuery extends BaseQuery {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long articleId;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
