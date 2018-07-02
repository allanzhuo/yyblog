package net.laoyeye.yyblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.common.utils.IDUtils;
import net.laoyeye.yyblog.mapper.KeywordMapper;
import net.laoyeye.yyblog.model.KeywordDO;
import net.laoyeye.yyblog.model.query.BaseQuery;
import net.laoyeye.yyblog.service.KeywordService;

@Service
public class KeywordServiceImpl implements KeywordService{
	@Autowired
	private KeywordMapper keywordMapper;

	@Override
	public DataGridResult listPageKeyword(BaseQuery query) {
		PageHelper.startPage(query.getPage(), query.getLimit()); 
		List<KeywordDO> list = keywordMapper.listKeyword();
		//取记录总条数
		PageInfo<KeywordDO> pageInfo = new PageInfo<KeywordDO>(list);
		long total = pageInfo.getTotal();
		//创建一个返回值对象
		DataGridResult result = new DataGridResult(); 
		result.setData(list);
		result.setCount(total);
		return result;
	}

	@Override
	public YYBlogResult saveKeyword(KeywordDO keyword) {
		if (keywordMapper.countByWords(keyword.getWords()) == 0) {
			keyword.setId(IDUtils.genId());
			keyword.setEnable(true);
			keywordMapper.saveKeyword(keyword);
		} else {
			return YYBlogResult.build(400, "关键字已存在！");
		}
		return YYBlogResult.ok();
	}

	@Override
	public YYBlogResult delete(long id) {
		keywordMapper.delete(id);
		return YYBlogResult.ok();
	}

	@Override
	public YYBlogResult update(KeywordDO keyword) {
		keywordMapper.update(keyword);
		return YYBlogResult.ok();
	}

	@Override
	public List<KeywordDO> listValidKeyword() {

		return keywordMapper.listValidKeyword();
	}

}
