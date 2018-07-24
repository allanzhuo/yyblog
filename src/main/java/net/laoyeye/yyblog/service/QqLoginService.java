package net.laoyeye.yyblog.service;

import javax.servlet.http.HttpServletRequest;
import net.laoyeye.yyblog.common.YYBlogResult;

public interface QqLoginService {
    YYBlogResult login(HttpServletRequest request);
}
