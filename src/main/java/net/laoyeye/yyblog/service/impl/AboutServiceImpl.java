package net.laoyeye.yyblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.mapper.AboutMapper;
import net.laoyeye.yyblog.model.AboutDO;
import net.laoyeye.yyblog.service.AboutService;

@Service
public class AboutServiceImpl implements AboutService{
	@Autowired
	private AboutMapper aboutMapper;

	@Override
	public YYBlogResult updateByTab(AboutDO about) {
		aboutMapper.updateByTab(about);
		return YYBlogResult.ok();
	}

	@Override
	public AboutDO getAboutByTab(String tab) {

		return aboutMapper.getAboutByTab(tab);
	}

}
