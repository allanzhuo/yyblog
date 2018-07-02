package net.laoyeye.yyblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.laoyeye.yyblog.mapper.ArticleMapper;
import net.laoyeye.yyblog.mapper.CateMapper;
import net.laoyeye.yyblog.mapper.SettingMapper;
import net.laoyeye.yyblog.mapper.TagMapper;
import net.laoyeye.yyblog.mapper.TagReferMapper;
import net.laoyeye.yyblog.model.ArticleDO;
import net.laoyeye.yyblog.model.CateDO;
import net.laoyeye.yyblog.model.SettingDO;
import net.laoyeye.yyblog.model.TagDO;
import net.laoyeye.yyblog.model.query.IndexQuery;
import net.laoyeye.yyblog.model.vo.ArticleVO;
import net.laoyeye.yyblog.model.vo.IndexArticleVO;
import net.laoyeye.yyblog.model.vo.IndexVO;
import net.laoyeye.yyblog.model.vo.TagVO;
import net.laoyeye.yyblog.service.IndexService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private SettingMapper settingMapper;
    @Autowired
    private CateMapper cateMapper;
    @Autowired
    private TagReferMapper tagReferMapper;
    @Autowired
    private TagMapper tagMapper;

    @Override
    public IndexVO getIndexVo() {
        List<SettingDO> settings = settingMapper.listAll();
        Map<String,Object> map = new HashMap<String,Object>();
        for (SettingDO setting : settings) {
        	map.put(setting.getCode(), setting.getValue());
		}
        int articleCount = articleMapper.countAllArticle();
        List<CateDO> cateList = cateMapper.listAllCate();
        PageHelper.startPage(1, 10); 
        //ID,TITLE
        List<ArticleVO> latestArticles = articleMapper.listArticleByTitle(null);
        List<TagVO> tagList = tagReferMapper.listNameAndCnt();
        IndexVO vo = new IndexVO();
        
        vo.setSettings(map);
        vo.setArticleCount(articleCount);
        vo.setCateList(cateList);
        vo.setLatestArticles(latestArticles);
        vo.setTagList(tagList);
        return vo;
    }

    @Override
    public IndexArticleVO next(IndexQuery query) {
    	
    	PageHelper.startPage(query.getPage(), query.getLimit()); 
    	List<ArticleVO> list = articleMapper.listIndexArticle(query);
		//取记录总条数
		PageInfo<ArticleVO> pageInfo = new PageInfo<ArticleVO>(list);
		long total = pageInfo.getTotal();
		Map<Long,Object> map = new HashMap<Long,Object>();
		for (ArticleVO articleVo : list) {
			List<TagDO> listTag = tagMapper.listTagByReferId(articleVo.getId());
			map.put(articleVo.getId(), listTag);
		}
		IndexArticleVO vo = new IndexArticleVO();
		vo.setData(list);
		vo.setTags(map);
		long totalPage = total % query.getLimit() == 0 ? total / query.getLimit():total / query.getLimit() + 1;
		vo.setTotalPage(totalPage);
		vo.setTotalCount(total);
		vo.setCode(200);
		return vo;
    }
}
