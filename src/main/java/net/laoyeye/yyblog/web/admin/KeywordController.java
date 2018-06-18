package net.laoyeye.yyblog.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.Keyword;
import net.laoyeye.yyblog.model.query.BaseQuery;
import net.laoyeye.yyblog.service.KeywordService;

/**
 * created by laoyeye on 2018/1/22 at 11:12
 */
@Controller
@RequestMapping("/management/keyword")
public class KeywordController {
	@Autowired
	private KeywordService keywordService;
	
    @GetMapping
    public String index() {
        return "management/keyword";
    }

    @GetMapping("/list")
    @ResponseBody
    public DataGridResult listKeyword(BaseQuery query) {
    	DataGridResult result = keywordService.listPageKeyword(query);
    	return result;
    }
    
    @PostMapping("/add")
    @ResponseBody
    public YYBlogResult add(Keyword keyword) {

        return keywordService.saveKeyword(keyword);
    }
    
    @PostMapping("/delete")
    @ResponseBody
    public YYBlogResult delete(long id) {

        return keywordService.delete(id);
    }
    
    @PostMapping("/edit/enable")
    @ResponseBody
    public YYBlogResult editEnable(Keyword keyword) {

        return keywordService.update(keyword);
    }

    @PostMapping("/edit/words")
    @ResponseBody
    public YYBlogResult editWords(Keyword keyword) {
    	
        return keywordService.update(keyword);
    }

}
