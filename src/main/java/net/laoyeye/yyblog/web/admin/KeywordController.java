package net.laoyeye.yyblog.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.KeywordDO;
import net.laoyeye.yyblog.model.query.BaseQuery;
import net.laoyeye.yyblog.service.KeywordService;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年1月22日
 * @website www.laoyeye.net
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
    public YYBlogResult add(KeywordDO keyword) {

        return keywordService.saveKeyword(keyword);
    }
    
    @PostMapping("/delete")
    @ResponseBody
    public YYBlogResult delete(long id) {

        return keywordService.delete(id);
    }
    
    @PostMapping("/edit/enable")
    @ResponseBody
    public YYBlogResult editEnable(KeywordDO keyword) {

        return keywordService.update(keyword);
    }

    @PostMapping("/edit/words")
    @ResponseBody
    public YYBlogResult editWords(KeywordDO keyword) {
        
        return keywordService.update(keyword);
    }

}
