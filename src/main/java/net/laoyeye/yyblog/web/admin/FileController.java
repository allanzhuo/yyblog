package net.laoyeye.yyblog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management/file")
public class FileController {

    @RequestMapping
    public String file() {
        return "management/file";
    }

   
}
