package net.laoyeye.yyblog.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.common.utils.IDUtils;
import net.laoyeye.yyblog.mapper.ArticleMapper;
import net.laoyeye.yyblog.mapper.CateMapper;
import net.laoyeye.yyblog.model.Cate;
import net.laoyeye.yyblog.service.CateService;

@Service
public class CateServiceImpl implements CateService{
	@Autowired
	private CateMapper cateMapper;
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public List<Cate> listAllCate() {

		return cateMapper.listAllCate();
	}

	@Override
	public DataGridResult listPageCate(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize); 
		List<Cate> list = cateMapper.listAllCate();
		//取记录总条数
		PageInfo<Cate> pageInfo = new PageInfo<Cate>(list);
		long total = pageInfo.getTotal();
		//创建一个返回值对象
		DataGridResult result = new DataGridResult(); 
		result.setData(list);
		result.setCount(total);
		return result;
	}

	@Override
	public YYBlogResult save(Cate cate) {
		int count = cateMapper.countByCode(cate.getCode());
    	if (count != 0) {
    		return YYBlogResult.build(400, "分类编码[" + cate.getName() + "]已存在！");
		}
    	cate.setId(IDUtils.genId());
    	cateMapper.save(cate);
    	return YYBlogResult.ok();
	}

	@Override
	public YYBlogResult delete(Long id) {
		int count = articleMapper.countByCateId(id);
		if (count != 0) {
    		return YYBlogResult.build(400, "请删除分类文章后再删除！");
		}
		cateMapper.delete(id);
		return YYBlogResult.ok();
	}

	@Override
	public YYBlogResult updateById(Cate cate) {
		cateMapper.updateById(cate);
		return YYBlogResult.ok();
	}

}
