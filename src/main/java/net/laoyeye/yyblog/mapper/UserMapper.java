package net.laoyeye.yyblog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.laoyeye.yyblog.model.UserDO;

public interface UserMapper {

	UserDO getUserByName(String username);

	UserDO getUserById(long id);
	
	List<UserDO> listUserByNickname(@Param("nickname") String nickname);
	
	int update(UserDO user);
	
	int updateByUsername(UserDO user);
	
	UserDO getUserByOpenId(String openId);
	
	int save(UserDO user);
	
	String getNicknameById(long id);
}
