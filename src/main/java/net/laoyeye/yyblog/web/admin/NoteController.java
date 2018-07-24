package net.laoyeye.yyblog.web.admin;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.laoyeye.yyblog.annotation.Log;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.NoteDO;
import net.laoyeye.yyblog.model.query.NoteQuery;
import net.laoyeye.yyblog.service.NoteService;
import net.laoyeye.yyblog.service.TagReferService;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年1月17日
 * @website www.laoyeye.net
 */
@Controller
@RequestMapping("/management/note")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private TagReferService tagReferService;

    @Log("打开新增笔记页面")
    @RequiresPermissions("blog:note:index")
    @GetMapping
    public String note() {
        return "management/note";
    }
    
    @Log("打开笔记列表页面")
    @RequiresPermissions("blog:notes:index")
    @GetMapping("/index")
    public String index() {
        return "management/notes";
    }

    @Log("新增笔记")
    @RequiresPermissions("blog:note:add")
    @PostMapping("/add")
    @ResponseBody
    public YYBlogResult add(NoteDO note, String tagName) {
        return noteService.saveNote(note, tagName);
    }
    
    @Log("查询笔记列表")
    @PostMapping("/list")
    @ResponseBody
    public DataGridResult listNote(NoteQuery query) {
        DataGridResult result = noteService.listPageNote(query);
        return result;
    }

    @Log("编辑笔记显示状态")
    @RequiresPermissions("blog:note:show")
    @PostMapping("/edit/show/{id}")
    @ResponseBody
    public YYBlogResult show(@PathVariable("id") Long id, Boolean show) {
        
        return noteService.updateIsShowById(id, show);
    }

    @Log("编辑笔记置顶状态")
    @RequiresPermissions("blog:note:top")
    @PostMapping("/edit/top/{id}")
    @ResponseBody
    public YYBlogResult top(@PathVariable("id") Long id, Boolean top) {
        
        return noteService.updateTopById(id, top);
    }
    
    @Log("删除笔记")
    @RequiresPermissions("blog:note:delete")
    @PostMapping("/delete/{id}")
    @ResponseBody
    public YYBlogResult delete(@PathVariable("id") Long id) {
    
        return noteService.delete(id);
    }
    
    @Log("打开编辑笔记页面")
    @RequiresPermissions("blog:note:editIndex")
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("editNote", noteService.getNoteById(id));
        String[] tagArray = tagReferService.listNameByArticleId(id).toArray(new String[]{});
        String tags = Arrays.toString(tagArray);
        model.addAttribute("noteTags", tags.substring(1, tags.length() - 1));
        return "management/note_edit";
    }

    @Log("编辑笔记提交")
    @RequiresPermissions("blog:note:edit")
    @PostMapping("/doEdit")
    @ResponseBody
    public YYBlogResult doEdit(NoteDO note, String tagNames) {
        
        return noteService.updateNote(note, tagNames);
    }

}
