package net.laoyeye.yyblog.mapper;

import net.laoyeye.yyblog.model.RoleMenuDO;

public interface RoleMenuMapper {
    int remove(Long id);

    int save(RoleMenuDO roleMenuDO);

    RoleMenuDO get(Long id);

    int update(RoleMenuDO roleMenuDO);

}