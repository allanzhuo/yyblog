package net.laoyeye.yyblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.laoyeye.yyblog.model.User;

public interface UserMapper {

	User getUserByName(String username);

	User getUserById(long id);
	
	List<User> listUserByNickname(@Param("nickname") String nickname);
	
	int update(User user);
	
	int updateByUsername(User user);
	
	User getUserByOpenId(String openId);
	
	int save(User user);
	
	String getNicknameById(long id);
}
