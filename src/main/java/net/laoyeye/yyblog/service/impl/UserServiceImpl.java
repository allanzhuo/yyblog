package net.laoyeye.yyblog.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.mapper.UserMapper;
import net.laoyeye.yyblog.model.UserDO;
import net.laoyeye.yyblog.model.query.UserQuery;
import net.laoyeye.yyblog.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    
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

	@Override
	public UserDO getUserById(long id) {

		return userMapper.getUserById(id);
	}
}
