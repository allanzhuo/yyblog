package net.laoyeye.yyblog.service;

import java.util.Set;

public interface MenuService {
	Set<String> listPerms(Long userId);
}