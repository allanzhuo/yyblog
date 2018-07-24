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

}
