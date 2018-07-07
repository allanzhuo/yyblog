package net.laoyeye.yyblog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by laoyeye on 2018/1/25 at 14:28
 */
@Controller("managementMessageController")
@RequestMapping("/management/message")
public class MessageController {



    @GetMapping
    public String index() {
        return "management/message";
    }

    /*@PostMapping("/list")
    @ResponseBody
    public LayuiTable<MessageVo> page(Page<MessageVo> messageVoPage, MessageQueryBo messageQueryBo) {
        messageVoPage = messageRepository.findPagination(messageVoPage, MessageVo.class, messageQueryBo);
        return layuiTable(messageVoPage);
    }

    @PostMapping("/edit/enable")
    @ResponseBody
    public R editEnable(Long id, Boolean enable) {
        return builder("修改留言状态").exec(() -> messageRepository.updateEnableById(enable, id) == 1);
    }
*/
}
