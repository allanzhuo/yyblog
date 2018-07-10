package net.laoyeye.yyblog.service;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.UserDO;
import net.laoyeye.yyblog.model.query.UserQuery;

public interface UserService {
	
	DataGridResult listPageUser(UserQuery query);
	
	YYBlogResult updateEnableById(Long id, Boolean enable);
	
	YYBlogResult updateByUsername(UserDO user);
	
	String getNicknameById(long id);
}
