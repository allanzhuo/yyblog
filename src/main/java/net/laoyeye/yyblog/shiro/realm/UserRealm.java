package net.laoyeye.yyblog.shiro.realm;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.DigestUtils;

import net.laoyeye.yyblog.mapper.UserMapper;
import net.laoyeye.yyblog.model.UserDO;
import net.laoyeye.yyblog.service.MenuService;
import net.laoyeye.yyblog.web.config.ApplicationContextRegister;

public class UserRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        Long userId = ((UserDO)SecurityUtils.getSubject().getPrincipal()).getId();
        MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
        Set<String> perms = menuService.listPerms(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();

        String password = new String((char[]) token.getCredentials());

        UserMapper userMapper = ApplicationContextRegister.getBean(UserMapper.class);
        // 查询用户信息
        UserDO user = null;
        if (username.length() > 12) {
            user = userMapper.getUserByOpenId(username);
            // 账号不存在
            if (user == null) {
                throw new UnknownAccountException("账号或密码不正确");
            }
            // 账号锁定
            if (user.getEnable() == false) {
                throw new LockedAccountException("账号已被锁定,请联系管理员");
            }
        } else {
            user = userMapper.getUserByName(username);
            // 账号不存在
            if (user == null) {
                throw new UnknownAccountException("账号或密码不正确");
            }
            
            // 密码错误
            if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
                throw new IncorrectCredentialsException("账号或密码不正确");
            }
            
            // 账号锁定
            if (user.getEnable() == false) {
                throw new LockedAccountException("账号已被锁定,请联系管理员");
            }
        }

        //不使用shiro自带的密码验证
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

}

