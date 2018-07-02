package net.laoyeye.yyblog.service;

import net.laoyeye.yyblog.model.query.IndexQuery;
import net.laoyeye.yyblog.model.vo.IndexArticleVO;
import net.laoyeye.yyblog.model.vo.IndexVO;

/**
 * created by LAOYEYE on 2018/1/31 at 19:03
 */
public interface IndexService {

    IndexVO getIndexVo();

    IndexArticleVO next(IndexQuery query);
}
