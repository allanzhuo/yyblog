package net.laoyeye.yyblog.mapper;

import java.util.List;

import net.laoyeye.yyblog.model.MenuDO;

public interface MenuMapper {
    int remove(Long menuId);

    int save(MenuDO menuDO);

    MenuDO get(Long menuId);

    int update(MenuDO menuDO);

    List<String> listUserPerms(Long userId);
}