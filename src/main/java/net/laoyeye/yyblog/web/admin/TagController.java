package net.laoyeye.yyblog.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.TagDO;
import net.laoyeye.yyblog.model.query.TagQuery;
import net.laoyeye.yyblog.service.TagService;

@Controller
@RequestMapping("/management/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping
    public String tag() {
        return "management/tag";
    }

    @GetMapping("/list")
    @ResponseBody
    public DataGridResult listTag(TagQuery query) {
        DataGridResult result = tagService.listPageTag(query);
        return result;
    }

    @PostMapping("/edit")
    @ResponseBody
    public YYBlogResult edit(TagDO tag) {
        
        return tagService.updateById(tag);
    }

    @PostMapping("/delete")
    @ResponseBody
    public YYBlogResult delete(long id) {
        
        return tagService.delete(id);       
    }

}
