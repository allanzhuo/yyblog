package net.laoyeye.yyblog.service;

import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.AboutDO;

public interface AboutService {
    /**
     * 更新关于信息
     */
    YYBlogResult updateByTab(AboutDO about);
    /**
     * 跟进tab发现关于栏内容
     */
    AboutDO getAboutByTab(String tab);
    
}
