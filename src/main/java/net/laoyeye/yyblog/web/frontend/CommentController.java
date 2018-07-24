package net.laoyeye.yyblog.web.frontend;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.common.utils.IDUtils;
import net.laoyeye.yyblog.common.utils.Injection;
import net.laoyeye.yyblog.common.utils.StringUtils;
import net.laoyeye.yyblog.common.utils.WebUtils;
import net.laoyeye.yyblog.model.CommentDO;
import net.laoyeye.yyblog.model.KeywordDO;
import net.laoyeye.yyblog.service.ArticleService;
import net.laoyeye.yyblog.service.CommentService;
import net.laoyeye.yyblog.service.KeywordService;
import net.laoyeye.yyblog.service.SettingService;

/**
 * 评论Controller
 * @author 小卖铺的老爷爷
 * @date 2018年5月10日
 * @website www.laoyeye.net
 */
@Controller
@RequestMapping("/token/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private KeywordService keywordService;
    @Autowired
    private SettingService settingService;
    @Autowired
    private ArticleService articleService;

    @PostMapping("/sub")
    @ResponseBody
    public YYBlogResult sub(CommentDO comment, HttpServletRequest request) {
        if (!settingService.getValueByCode("all_comment_open").equals("1") || !articleService.getArticleById(comment.getArticleId()).getCommented()) {
            return YYBlogResult.build(500, "未开放评论功能，请勿非法操作！");
        }
        comment.setId(IDUtils.genId());
        comment.setEnable(true);
        comment.setIpAddr(WebUtils.getIpAddress(request));
        comment.setIpCnAddr(WebUtils.getIpCnInfo(WebUtils.getIpInfo(comment.getIpAddr())));
        //后面改样式用到
        comment.setParentId(null);
        comment.setContent(Injection.stripSqlInjection(comment.getContent()));
        List<KeywordDO> keywords = keywordService.listValidKeyword();
        for (KeywordDO keyword : keywords) {
            comment.setContent(comment.getContent().replace(keyword.getWords(), StringUtils.addStrings(keyword.getWords(), keyword.getWords().length())));
        }
        comment.setCreateTime(new Date());
        return commentService.insert(comment);

    }
}
