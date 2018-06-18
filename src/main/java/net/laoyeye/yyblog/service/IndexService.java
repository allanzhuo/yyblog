package net.laoyeye.yyblog.service;

import net.laoyeye.yyblog.model.query.IndexQuery;
import net.laoyeye.yyblog.model.vo.IndexArticleVo;
import net.laoyeye.yyblog.model.vo.IndexVo;

/**
 * created by LAOYEYE on 2018/1/31 at 19:03
 */
public interface IndexService {

    IndexVo getIndexVo();

    IndexArticleVo next(IndexQuery query);
}
