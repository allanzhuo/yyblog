package net.laoyeye.yyblog.mapper;

import net.laoyeye.yyblog.model.UserRoleDO;

public interface UserRoleMapper {
    int remove(Long id);

    int save(UserRoleDO userRoleDO);

    UserRoleDO get(Long id);

    int update(UserRoleDO userRoleDO);
}