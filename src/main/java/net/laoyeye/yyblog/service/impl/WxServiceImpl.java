package net.laoyeye.yyblog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.common.utils.IDUtils;
import net.laoyeye.yyblog.mapper.ArticleMapper;
import net.laoyeye.yyblog.mapper.CollectMapper;
import net.laoyeye.yyblog.model.CollectDO;
import net.laoyeye.yyblog.model.query.BaseQuery;
import net.laoyeye.yyblog.model.vo.WxPostVO;
import net.laoyeye.yyblog.service.WxService;
/**
 * 微信接口service
 * @author 小卖铺的老爷爷
 * @date 2018年9月26日
 * @website www.laoyeye.net
 */
@Service
public class WxServiceImpl implements WxService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public List<WxPostVO> listWxPost(BaseQuery query) {
        PageHelper.startPage(query.getPage(), query.getLimit()); 
        List<WxPostVO> list = articleMapper.listWxIndex();
        return list;
    }

    @Override
    public List<WxPostVO> listWxTop() {
        List<WxPostVO> list = articleMapper.listWxTop();
        return list;
    }

    @Override
    public YYBlogResult saveOrUpdateCollect(Long id, Long userId, boolean collect) {
        CollectDO collectDo  = new CollectDO();
        collectDo.setArticleId(id);
        collectDo.setUserId(userId);
        if (collect) {
            collectDo.setId(IDUtils.genId());
            collectDo.setEnable(true);
            collectDo.setCreateUser(userId);
            collectDo.setCreateTime(new Date());
            collectDo.setUpdateUser(userId);
            collectDo.setUpdateTime(new Date());
            collectMapper.insert(collectDo);
        } else {
            collectDo.setEnable(false);
            collectDo.setUpdateUser(userId);
            collectDo.setUpdateTime(new Date());
            collectMapper.updateByUserIdAndArticleId(collectDo);
        }
        return YYBlogResult.ok();
    }

    @Override
    public List<WxPostVO> listAllCollect(String userId) {
        List<WxPostVO> list = collectMapper.listCollectByUserId(userId);
        return list;
    }

}
