package net.laoyeye.yyblog.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.UserDO;
import net.laoyeye.yyblog.model.query.UserQuery;

public interface UserService {
	YYBlogResult getUserByName(HttpServletRequest request, HttpServletResponse response, 
			String username, String password, Boolean remember);
	/**
	 * 后台的验证，前台不要调此接口
	 */
	boolean checkUserByToken(String token);
	
	UserDO getUserByToken(String token);
	
	DataGridResult listPageUser(UserQuery query);
	
	YYBlogResult updateEnableById(Long id, Boolean enable);
	
	YYBlogResult updateByUsername(UserDO user);
	
	String getNicknameById(long id);
}
