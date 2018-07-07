package net.laoyeye.yyblog.mapper;

import net.laoyeye.yyblog.model.RoleDO;

public interface RoleMapper {
    int remove(Long roleId);

    int save(RoleDO roleDO);

    RoleDO get(Long roleId);

    int update(RoleDO roleDO);

}