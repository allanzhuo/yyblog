package net.laoyeye.yyblog.service.impl;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.utils.StringUtils;
import net.laoyeye.yyblog.model.UserDO;
import net.laoyeye.yyblog.model.vo.UserOnlineVO;
import net.laoyeye.yyblog.service.SessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * session
 * @author 小卖铺的老爷爷
 * @date 2018年7月9日
 * @website www.laoyeye.net
 */
@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionDAO sessionDAO; 

    @Override
    public DataGridResult list(int page, int limit, String username) {
        List<UserOnlineVO> list = new ArrayList<>();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            UserOnlineVO userOnline = new UserOnlineVO();
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                continue;
            } else {
                SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) session
                        .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                UserDO userDO = (UserDO) principalCollection.getPrimaryPrincipal();
                userOnline.setUsername(userDO.getUsername());
            }
            userOnline.setId((String) session.getId());
            userOnline.setHost(session.getHost());
            userOnline.setStartTimestamp(session.getStartTimestamp());
            userOnline.setLastAccessTime(session.getLastAccessTime());
            userOnline.setTimeout(session.getTimeout());
            if (!StringUtils.isEmpty(username)) {
                if (userOnline.getUsername().equals(username)) {
                    list.add(userOnline);
                }
            } else {
                list.add(userOnline);
            }
        }
        int size = list.size();
        int star = (page - 1) * limit;
        List<UserOnlineVO> listPage = list.subList(star , size-star > limit ? star + limit : size);
        //创建一个返回值对象
        DataGridResult result = new DataGridResult();
        result.setData(listPage);
        result.setCount(size);
        return result;
    }

    @Override
    public boolean removeUser(String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        session.setTimeout(0);
        return true;
    }
}
