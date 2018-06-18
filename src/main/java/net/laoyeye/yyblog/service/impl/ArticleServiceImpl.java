package net.laoyeye.yyblog.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*import org.apache.commons.lang3.StringUtils;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.common.utils.FilterHtml;
import net.laoyeye.yyblog.common.utils.IDUtils;
import net.laoyeye.yyblog.mapper.ArticleMapper;
import net.laoyeye.yyblog.mapper.TagMapper;
import net.laoyeye.yyblog.mapper.TagReferMapper;
import net.laoyeye.yyblog.model.Article;
import net.laoyeye.yyblog.model.Tag;
import net.laoyeye.yyblog.model.TagRefer;
import net.laoyeye.yyblog.model.query.ArticleQuery;
import net.laoyeye.yyblog.model.vo.ArticleVo;
import net.laoyeye.yyblog.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private TagMapper tagMapper;
	@Autowired
	private TagReferMapper tagReferMapper;

	@Override
	public int countAllArticle() {

		return articleMapper.countAllArticle();
	}

	@Override
	public ArticleVo getLatestArticle() {

		return articleMapper.getLatestArticle();
	}

	@Transactional
	@Override
	public YYBlogResult saveArticle(Article article,String tagName) {

		//插入文章
		article.setId(IDUtils.genId());
		article.setCreateTime(new Date());
		article.setUpdateTime(new Date());
		//浏览数
		article.setViews(0);
		//点赞数
		article.setApproveCnt(0);
		article.setAppreciable(article.getAppreciable() == null ? false : true );
		article.setCommented(article.getCommented() == null ? false : true );
		article.setTop(false);
		getSumByFilterContent(article);
		articleMapper.saveArticle(article);
		if (!StringUtils.isEmpty(tagName)) {
			//标签处理
			String[] tagNameArray = tagName.split(",");
			Tag tag = new Tag();
			TagRefer tagRefer = new TagRefer();
			for (String name : Arrays.asList(tagNameArray)) {
				if (tagMapper.countByName(name) == 0) {
					tag.setId(IDUtils.genId());
					tag.setName(name);
					tagMapper.saveTag(tag);
				} else {
					tag = tagMapper.getTagByName(name);
				}
				tagRefer.setId(IDUtils.genId());
				tagRefer.setReferId(article.getId());
				tagRefer.setTagId(tag.getId());
				tagRefer.setIsShow(true);
				tagRefer.setType("1");
				tagReferMapper.saveTagRefer(tagRefer);
			}
		}
		return YYBlogResult.ok();
	}

	@Override
	public DataGridResult listPageArticle(ArticleQuery query) {
		PageHelper.startPage(query.getPage(), query.getLimit()); 
		List<ArticleVo> list = articleMapper.listArticleByTitle(query.getTitle());
		//取记录总条数
		PageInfo<ArticleVo> pageInfo = new PageInfo<ArticleVo>(list);
		long total = pageInfo.getTotal();
		//创建一个返回值对象
		DataGridResult result = new DataGridResult(); 
		result.setData(list);
		result.setCount(total);
		return result;
	}

	@Transactional
	@Override
	public YYBlogResult updateAppreciableById(Long id, Boolean appreciable) {
		Article article = new Article();
		article.setId(id);
		article.setAppreciable(appreciable);
		article.setUpdateTime(new Date());
		articleMapper.update(article);
		return YYBlogResult.ok();
	}

	@Transactional
	@Override
	public YYBlogResult updateCommentedById(Long id, Boolean commented) {
		Article article = new Article();
		article.setId(id);
		article.setCommented(commented);
		article.setUpdateTime(new Date());
		articleMapper.update(article);
		return YYBlogResult.ok();
	}

	@Transactional
	@Override
	public YYBlogResult updateTopById(Long id, Boolean top) {
		Article article = new Article();
		article.setId(id);
		article.setTop(top);
		article.setUpdateTime(new Date());
		articleMapper.update(article);
		return YYBlogResult.ok();
	}

	@Override
	public Article getArticleById(long id) {
		Article articleById = articleMapper.getArticleById(id);
		return articleById;
	}

	@Transactional
	@Override
	public YYBlogResult updateArticle(Article article, String tagName) {
		article.setUpdateTime(new Date());
		getSumByFilterContent(article);
		articleMapper.update(article);
		tagReferMapper.deleteByReferId(article.getId());
		String[] tagNameArray = tagName.split(",");
		Tag tag = new Tag();
		TagRefer tagRefer = new TagRefer();
		for (String name : Arrays.asList(tagNameArray)) {
			if (tagMapper.countByName(name) == 0) {
				tag.setId(IDUtils.genId());
				tag.setName(name);
				tagMapper.saveTag(tag);
			} else {
				tag = tagMapper.getTagByName(name);
			}
			tagRefer.setId(IDUtils.genId());
			tagRefer.setReferId(article.getId());
			tagRefer.setTagId(tag.getId());
			tagRefer.setIsShow(true);
			tagRefer.setType("1");
			tagReferMapper.saveTagRefer(tagRefer);
		}
		return YYBlogResult.ok();
	}

	private void getSumByFilterContent(Article article) {
		String clearContent = FilterHtml.filterHtml(article.getContent().trim());
		clearContent = StringUtils.trimAllWhitespace(clearContent);
		if (StringUtils.isEmpty(article.getSummary())) {
			String summary = clearContent.substring(0, clearContent.length() < 243 ? clearContent.length() : 243);
			article.setSummary(summary);
		}
		article.setTextContent(clearContent);
	}

	@Transactional
	@Override
	public YYBlogResult delete(Long id) {
		tagReferMapper.deleteByReferId(id);
		articleMapper.delete(id);
		return YYBlogResult.ok();
	}

	@Override
	public YYBlogResult saveSimpleArticle(Article article) {
		//插入文章
		article.setId(IDUtils.genId());
		article.setCreateTime(new Date());
		article.setUpdateTime(new Date());
		//浏览数
		article.setViews(0);
		//点赞数
		article.setApproveCnt(0);
		article.setAppreciable(article.getAppreciable() == null ? false : true );
		article.setCommented(article.getCommented() == null ? false : true );
		article.setTop(false);
		article.setDraft(true);
		getSumByFilterContent(article);
		articleMapper.saveArticle(article);
		return YYBlogResult.ok();
	}

	@Override
	public int updateViewsById(long id) {
		
		return articleMapper.updateViewsById(id);
	}

	@Override
	public YYBlogResult updateApproveCntById(long id) {
		articleMapper.updateApproveCntById(id);
		return YYBlogResult.ok();
	}

}
