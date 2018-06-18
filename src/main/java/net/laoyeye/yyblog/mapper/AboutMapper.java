package net.laoyeye.yyblog.mapper;

import java.util.List;

import net.laoyeye.yyblog.model.About;

public interface AboutMapper {

    int updateByTab(About about);

    About getAboutByTab(String tab);
    
    List<About> listAll();
}
