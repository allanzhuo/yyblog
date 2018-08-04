package net.laoyeye.yyblog.service;

import java.util.List;

import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.query.BaseQuery;
import net.laoyeye.yyblog.model.vo.WxPostVO;

/**
 * 微信接口
 * @author 小卖铺的老爷爷
 * @date 2018年8月5日
 * @website www.laoyeye.net
 */
public interface WxService {
    /**
     * 查询微信首页信息
     */
    List<WxPostVO> listWxPost(BaseQuery query);
    /**
     * 查询微信首页Banner,这里取置顶帖
     */
    List<WxPostVO> listWxTop();
    /**
     * 保存收藏信息
     */
    YYBlogResult saveOrUpdateCollect(Long id, Long userId, boolean collect);
    /**
     * 查询所有收藏的文章
     */
    List<WxPostVO> listAllCollect(String userId);
}
