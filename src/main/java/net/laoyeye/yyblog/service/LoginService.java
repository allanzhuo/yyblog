package net.laoyeye.yyblog.service;

import javax.servlet.http.HttpServletRequest;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.UserDO;

public interface LoginService {
    YYBlogResult login(HttpServletRequest request);
    Long wxLogin(UserDO user);
}
