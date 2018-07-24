package net.laoyeye.yyblog.mapper;

import java.util.List;
import net.laoyeye.yyblog.model.SettingDO;

public interface SettingMapper {

    List<SettingDO> listAll();

    String getValueByCode(String code);
    
    int updateValueByCode(SettingDO setting);
}
