package net.laoyeye.yyblog.mapper;

import java.util.List;

import net.laoyeye.yyblog.model.AboutDO;

public interface AboutMapper {

    int updateByTab(AboutDO about);

    AboutDO getAboutByTab(String tab);
    
    List<AboutDO> listAll();
}
