package net.laoyeye.yyblog.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.mapper.SettingMapper;
import net.laoyeye.yyblog.model.SettingDO;
import net.laoyeye.yyblog.service.SettingService;

@Service
public class SettingServiceImpl implements SettingService{
	@Autowired
	private SettingMapper settingMapper;
	
	@Override
	public List<SettingDO> listAll() {

		return settingMapper.listAll();
	}

	@Override
	public String getValueByCode(String code) {
	
		return settingMapper.getValueByCode(code);
	}

	@Override
	public YYBlogResult updateValueByCode(SettingDO setting) {
		int count = settingMapper.updateValueByCode(setting);
		if (count != 1) {
			YYBlogResult.build(500, "更新配置信息失败！");
		}
		return YYBlogResult.ok();
	}

}
