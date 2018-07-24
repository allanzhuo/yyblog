package net.laoyeye.yyblog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年1月25日
 * @website www.laoyeye.net
 */
@Controller("managementMessageController")
@RequestMapping("/management/message")
public class MessageController {

    @GetMapping
    public String index() {
        return "management/message";
    }

}
