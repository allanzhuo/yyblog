package net.laoyeye.yyblog.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.Cate;
import net.laoyeye.yyblog.service.CateService;

/**
 * created by laoyeye on 2018/1/14 at 15:21
 */
@Controller
@RequestMapping("/management/cate")
public class CateController {
	@Autowired
	private CateService cateService;
	
    @RequestMapping
    public String cate() {
    
        return "management/cate";
    }

    @GetMapping("/list")
    @ResponseBody
    public DataGridResult listCate(int page, int limit) {
    	DataGridResult result = cateService.listPageCate(page, limit);
        return result;
    }

    @PostMapping("/add")
    @ResponseBody
    public YYBlogResult add(Cate cate) {
    	YYBlogResult result = cateService.save(cate);
    	return result;
    }

    @PostMapping("/delete")
    @ResponseBody
    public YYBlogResult delete(long id) {
    	
        return cateService.delete(id);
    }

    @PostMapping("/edit")
    @ResponseBody
    public YYBlogResult edit(Cate cate) {
    	
        return cateService.updateById(cate);
    }
}
