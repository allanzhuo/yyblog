package net.laoyeye.yyblog.service;

import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.About;

public interface AboutService {
	/**
	 * 更新关于信息
	 */
    YYBlogResult updateByTab(About about);
	/**
	 * 跟进tab发现关于栏内容
	 */
    About getAboutByTab(String tab);
    
}
