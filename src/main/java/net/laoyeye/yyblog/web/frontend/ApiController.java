package net.laoyeye.yyblog.web.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;

import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.common.utils.HttpClientUtil;
import net.laoyeye.yyblog.common.utils.JSONUtils;
import net.laoyeye.yyblog.common.utils.StringUtils;
import net.laoyeye.yyblog.model.ArticleDO;
import net.laoyeye.yyblog.model.UserDO;
import net.laoyeye.yyblog.model.query.BaseQuery;
import net.laoyeye.yyblog.model.vo.WxPostVO;
import net.laoyeye.yyblog.service.ArticleService;
import net.laoyeye.yyblog.service.LoginService;
import net.laoyeye.yyblog.service.UserService;
import net.laoyeye.yyblog.service.WxService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 对外api
 * @author 小卖铺的老爷爷
 * @date 2018年3月9日
 * @website www.laoyeye.net
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private WxService wxService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Value("${weixin.appid}")
    private String wxAppid;
    @Value("${weixin.secret}")
    private String wxSecret;
    @Value("${weixin.url}")
    private String wxUrl;

    @GetMapping("/qq")
    public String qqLogin(HttpServletRequest request) {
        String authorizeUrl = null;
        try {
            authorizeUrl = new Oauth().getAuthorizeURL(request);
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        return "redirect:" + authorizeUrl;
    }

    @GetMapping("/qq/callback")
    public String qqCallback(HttpServletRequest request) {
        YYBlogResult result = loginService.login(request);
        if (result.getCode() == 200) {
            return "redirect:/index";
        }
        return "error/page";
    }

    @GetMapping("/wx/index")
    @ResponseBody
    public List<WxPostVO> listIndex(BaseQuery query) {
        return wxService.listWxPost(query);
    }

    @GetMapping("/wx/{id}")
    @ResponseBody
    public WxPostVO getArticle(@PathVariable("id") Long id) {
        try {
            articleService.updateViewsById(id);
        } catch (Exception ignore) {
        }
        ArticleDO article = articleService.getArticleById(id);
        WxPostVO vo = new WxPostVO();
        vo.setId(article.getId());
        vo.setTitle(article.getTitle());
        UserDO user = userService.getUserById(article.getAuthorId());
        vo.setNickname(user.getNickname());
        vo.setAvatar(user.getAvatar());
        vo.setCover(article.getCover());
        vo.setSummary(article.getSummary());
        vo.setContent(article.getContent());
        vo.setViews(article.getViews());
        vo.setCollections(1);
        vo.setCreateTime(article.getCreateTime());
        return vo;
    }

    @GetMapping("/wx/top")
    @ResponseBody
    public List<WxPostVO> listTop() {
        return wxService.listWxTop();
    }

    @GetMapping("/wx/collect")
    @ResponseBody
    public YYBlogResult saveCollect(Long id, Long userId, boolean collect) {
        return wxService.saveOrUpdateCollect(id, userId, collect);
    }

    @GetMapping("/wx/list/collect")
    @ResponseBody
    public List<WxPostVO> listCollect(String userId) {
        return wxService.listAllCollect(userId);
    }

    @GetMapping("/wx/login")
    @ResponseBody
    public YYBlogResult wxLogin(String code,String nickname,String avatar) {
        String url = wxUrl + "&appid=" + wxAppid + "&secret=" + wxSecret + "&js_code=" + code;
        String resp = HttpClientUtil.doGet(url);
        Map<String, Object> jsonToMap = JSONUtils.jsonToMap(resp);
        if (StringUtils.isBlank(jsonToMap)) {
            return YYBlogResult.build(500, "获取用户信息失败");
        }
        String openId = (String)jsonToMap.get("openid");
        String userId = "";
        if (!StringUtils.isEmpty(openId)) {
            UserDO user = new UserDO();
            user.setWxOpenId(openId);
            user.setNickname(nickname);
            user.setAvatar(avatar);
            userId = loginService.wxLogin(user).toString();
        }
        return YYBlogResult.ok(userId);
    }
}
