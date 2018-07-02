package net.laoyeye.yyblog.web.frontend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.laoyeye.yyblog.common.SessionParam;
import net.laoyeye.yyblog.common.utils.CookieUtils;
import net.laoyeye.yyblog.model.query.IndexQuery;
import net.laoyeye.yyblog.model.vo.IndexArticleVO;
import net.laoyeye.yyblog.service.IndexService;

/**
 * created by laoyeye on 2018/1/31 at 13:48
 */
@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private IndexService indexService;

    @GetMapping(value = {"", "/index"})
    public String index(Model model) {
        model.addAttribute("indexVo", indexService.getIndexVo());
        return "frontend/index";
    }

    @PostMapping(value = {"/next", "/index/next"})
    @ResponseBody
    public IndexArticleVO next(IndexQuery query) {
    	IndexArticleVO result = indexService.next(query);
        return result;
    }

    @GetMapping("/token/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, String from) {
        request.getSession().invalidate();
        CookieUtils.deleteCookie(request, response, SessionParam.COOKIE_NAME);
        if (StringUtils.isEmpty(from)) {
            return "redirect:/";
        } else {
            return "redirect:" + SessionParam.MANAGEMENT_INDEX;
        }
    }
}
