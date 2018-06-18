package net.laoyeye.yyblog.mapper;

import java.util.List;
import net.laoyeye.yyblog.model.Setting;

public interface SettingMapper {

	List<Setting> listAll();

	String getValueByCode(String code);
	
	int updateValueByCode(Setting setting);
}
