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
import net.laoyeye.yyblog.model.Article;
import net.laoyeye.yyblog.model.Cate;
import net.laoyeye.yyblog.model.Setting;
import net.laoyeye.yyblog.model.Tag;
import net.laoyeye.yyblog.model.query.IndexQuery;
import net.laoyeye.yyblog.model.vo.ArticleVo;
import net.laoyeye.yyblog.model.vo.IndexArticleVo;
import net.laoyeye.yyblog.model.vo.IndexVo;
import net.laoyeye.yyblog.model.vo.TagVo;
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
    public IndexVo getIndexVo() {
        List<Setting> settings = settingMapper.listAll();
        Map<String,Object> map = new HashMap<String,Object>();
        for (Setting setting : settings) {
        	map.put(setting.getCode(), setting.getValue());
		}
        int articleCount = articleMapper.countAllArticle();
        List<Cate> cateList = cateMapper.listAllCate();
        PageHelper.startPage(1, 10); 
        //ID,TITLE
        List<ArticleVo> latestArticles = articleMapper.listArticleByTitle(null);
        List<TagVo> tagList = tagReferMapper.listNameAndCnt();
        IndexVo vo = new IndexVo();
        
        vo.setSettings(map);
        vo.setArticleCount(articleCount);
        vo.setCateList(cateList);
        vo.setLatestArticles(latestArticles);
        vo.setTagList(tagList);
        return vo;
    }

    @Override
    public IndexArticleVo next(IndexQuery query) {
    	
    	PageHelper.startPage(query.getPage(), query.getLimit()); 
    	List<ArticleVo> list = articleMapper.listIndexArticle(query);
		//取记录总条数
		PageInfo<ArticleVo> pageInfo = new PageInfo<ArticleVo>(list);
		long total = pageInfo.getTotal();
		Map<Long,Object> map = new HashMap<Long,Object>();
		for (ArticleVo articleVo : list) {
			List<Tag> listTag = tagMapper.listTagByReferId(articleVo.getId());
			map.put(articleVo.getId(), listTag);
		}
		IndexArticleVo vo = new IndexArticleVo();
		vo.setData(list);
		vo.setTags(map);
		long totalPage = total % query.getLimit() == 0 ? total / query.getLimit():total / query.getLimit() + 1;
		vo.setTotalPage(totalPage);
		vo.setTotalCount(total);
		vo.setCode(200);
		return vo;
    }
}
