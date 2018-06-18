package net.laoyeye.yyblog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("adminCommentController")
@RequestMapping("/management/comment")
public class CommentController {


    @GetMapping
    public String index() {
        return "management/comment";
    }

   /* @PostMapping("/list")
    @ResponseBody
    public LayuiTable<CommentVo> page(Page<CommentVo> commentPage, CommentQueryBo commentBo) {
        commentPage = commentRepository.findPagination(commentPage, CommentVo.class, commentBo);
        return layuiTable(commentPage);
    }

    @PostMapping("/edit/enable")
    @ResponseBody
    public R editEnable(Long id, Boolean enable) {
        return builder("修改评论状态").exec(() -> commentRepository.updateEnableById(enable, id) == 1);
    }
*/
}
