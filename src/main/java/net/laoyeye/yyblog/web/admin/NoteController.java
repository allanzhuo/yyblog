package net.laoyeye.yyblog.web.admin;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.common.utils.IDUtils;
import net.laoyeye.yyblog.model.NoteDO;
import net.laoyeye.yyblog.model.query.NoteQuery;
import net.laoyeye.yyblog.service.NoteService;
import net.laoyeye.yyblog.service.TagReferService;

/**
 * created by laoyeye on 2018/1/17 at 20:44
 */
@Controller
@RequestMapping("/management/note")
public class NoteController {
	@Autowired
	private NoteService noteService;
	@Autowired
	private TagReferService tagReferService;

    @GetMapping
    public String note() {
        return "management/note";
    }

    @GetMapping("/index")
    public String index() {
        return "management/notes";
    }

    @PostMapping("/add")
    @ResponseBody
    public YYBlogResult add(NoteDO note, String tagName) {
    	return noteService.saveNote(note, tagName);
    }
    @PostMapping("/list")
    @ResponseBody
    public DataGridResult listNote(NoteQuery query) {
    	DataGridResult result = noteService.listPageNote(query);
        return result;
    }

    @PostMapping("/edit/show/{id}")
    @ResponseBody
    public YYBlogResult show(@PathVariable("id") Long id, Boolean show) {
    	
        return noteService.updateIsShowById(id, show);
    }

    @PostMapping("/edit/top/{id}")
    @ResponseBody
    public YYBlogResult top(@PathVariable("id") Long id, Boolean top) {
    	
        return noteService.updateTopById(id, top);
    }
    
    @PostMapping("/delete/{id}")
    @ResponseBody
    public YYBlogResult delete(@PathVariable("id") Long id) {
    
        return noteService.delete(id);
    }
    
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("editNote", noteService.getNoteById(id));
        String[] tagArray = tagReferService.listNameByArticleId(id).toArray(new String[]{});
        String tags = Arrays.toString(tagArray);
        model.addAttribute("noteTags", tags.substring(1, tags.length() - 1));
        return "management/note_edit";
    }

    @PostMapping("/doEdit")
    @ResponseBody
    public YYBlogResult doEdit(NoteDO note, String tagNames) {
    	
        return noteService.updateNote(note, tagNames);
    }

}
