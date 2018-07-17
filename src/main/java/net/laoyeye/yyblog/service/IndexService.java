package net.laoyeye.yyblog.service;

import net.laoyeye.yyblog.model.query.IndexQuery;
import net.laoyeye.yyblog.model.vo.IndexArticleVO;
import net.laoyeye.yyblog.model.vo.IndexVO;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年1月23日
 * @website www.laoyeye.net
 */
public interface IndexService {

    IndexVO getIndexVo();

    IndexArticleVO next(IndexQuery query);
}
