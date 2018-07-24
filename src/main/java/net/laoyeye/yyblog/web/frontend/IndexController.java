package net.laoyeye.yyblog.web.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.laoyeye.yyblog.common.Constant;
import net.laoyeye.yyblog.common.utils.WebUtils;
import net.laoyeye.yyblog.model.query.IndexQuery;
import net.laoyeye.yyblog.model.vo.IndexArticleVO;
import net.laoyeye.yyblog.service.IndexService;
import net.laoyeye.yyblog.web.BaseController;

/**
 * 前台首页
 * @author 小卖铺的老爷爷
 * @date 2018年1月23日
 * @website www.laoyeye.net
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController{
    @Autowired
    private IndexService indexService;

    @GetMapping(value = {"", "/index"})
    public String index(Model model) {
        model.addAttribute("indexVo", indexService.getIndexVo());
        model.addAttribute("login", WebUtils.toMap(getUser()));
        return "frontend/index";
    }

    @PostMapping(value = {"/next", "/index/next"})
    @ResponseBody
    public IndexArticleVO next(IndexQuery query) {
        IndexArticleVO result = indexService.next(query);
        return result;
    }

    @GetMapping("/token/logout")
    public String logout(String from) {
        logout();
        if (StringUtils.isEmpty(from)) {
            return "redirect:/";
        } else {
            return "redirect:" + Constant.MANAGEMENT_INDEX;
        }
    }
}
