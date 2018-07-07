package net.laoyeye.yyblog.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.SessionParam;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.common.utils.AESUtils;
import net.laoyeye.yyblog.mapper.UserMapper;
import net.laoyeye.yyblog.model.UserDO;
import net.laoyeye.yyblog.model.query.UserQuery;
import net.laoyeye.yyblog.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;

/*	@Override
	public YYBlogResult getUserByName(HttpServletRequest request, HttpServletResponse response, String username, String password, Boolean remember) {
		try {
			UserDO user = userMapper.getUserByName(username);
			//如果没有此用户名
			if (user == null) {
				return YYBlogResult.build(400, "用户名或密码错误");
			}
			//比对密码
			if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
				return YYBlogResult.build(400, "用户名或密码错误");
			}
			//生成token
			String token = AESUtils.encrypt(String.valueOf(user.getId())+","+System.currentTimeMillis(), 
					SessionParam.COOKIE_SECRET_KEY);
			//如果选择免登陆，设置cookie生效时间为一天
			if (remember) {
				CookieUtils.setCookie(request, response, SessionParam.COOKIE_NAME, token, 24*60*60, true);
			} else {
				//添加cookie的逻辑，关闭浏览器失效
				CookieUtils.setCookie(request, response, SessionParam.COOKIE_NAME, token);
			}
			return YYBlogResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
*/
	@Override
	public boolean checkUserByToken(String token) {
	/*	try {
			String[] array = AESUtils.decrypt(token, SessionParam.COOKIE_SECRET_KEY).split(SessionParam.COOKIE_SPLIT);
			String userId = array[0];
			String date = array[1];
			//过期的时间戳
			long timestamp = Long.parseLong(date) + (24*60*60*1000);
			//当前时间戳
			long currentstamp = System.currentTimeMillis();
			if (timestamp < currentstamp) {
				return false;
			}
			UserDO user = userMapper.getUserById(Long.parseLong(userId));
			if (user != null && user.getRoleId() == 1 && user.getEnable() == true) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return false;
	}

	@Override
	public UserDO getUserByToken(String token) {
		try {
			String[] array = AESUtils.decrypt(token, SessionParam.COOKIE_SECRET_KEY).split(SessionParam.COOKIE_SPLIT);
			String userId = array[0];
			String date = array[1];
			//过期的时间戳
			long timestamp = Long.parseLong(date) + (24*60*60*1000);
			//当前时间戳
			long currentstamp = System.currentTimeMillis();
			if (timestamp < currentstamp) {
				return null;
			}
			UserDO user = userMapper.getUserById(Long.parseLong(userId));
			if (user != null && user.getEnable() == true) {
				user.setPassword(null);
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*@Cacheable(value="user", key="#query.nickname"+"")*/
	@Override
	public DataGridResult listPageUser(UserQuery query) {
		PageHelper.startPage(query.getPage(), query.getLimit()); 
		List<UserDO> list = userMapper.listUserByNickname(query.getNickname());
		//取记录总条数
		PageInfo<UserDO> pageInfo = new PageInfo<UserDO>(list);
		long total = pageInfo.getTotal();
		//创建一个返回值对象
		DataGridResult result = new DataGridResult(); 
		result.setData(list);
		result.setCount(total);
		return result;
	}

	@Override
	public YYBlogResult updateEnableById(Long id, Boolean enable) {
		UserDO user = new UserDO();
		user.setId(id);
		user.setEnable(enable);
		user.setUpdateTime(new Date());
		userMapper.update(user);
		return YYBlogResult.ok();
	}

	@Override
	public YYBlogResult updateByUsername(UserDO user) {
		int count = userMapper.updateByUsername(user);
		if (count == 1) {
			return YYBlogResult.ok();
		}
		return YYBlogResult.build(500, "修改个人资料失败！");
	}

	@Override
	public String getNicknameById(long id) {
		
		return userMapper.getNicknameById(id);
	}
}
