package net.laoyeye.yyblog.service;

import org.springframework.stereotype.Service;
import net.laoyeye.yyblog.common.DataGridResult;

@Service
public interface SessionService {
    /**获取在线用户*/
    DataGridResult list(int page, int limit, String username);

    /**强制退出*/
    boolean removeUser(String sessionId);
}
