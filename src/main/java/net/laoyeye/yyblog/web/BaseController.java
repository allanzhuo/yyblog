package net.laoyeye.yyblog.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import net.laoyeye.yyblog.model.UserDO;

public class BaseController{
    
    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }
    public static UserDO getUser() {
        Object object = getSubjct().getPrincipal();
        return (UserDO)object;
    }
    public static Long getUserId() {
        return getUser().getId();
    }
    public static void logout() {
        getSubjct().logout();
    }

}
