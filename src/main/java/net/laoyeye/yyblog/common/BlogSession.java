//package net.laoyeye.yyblog.common;
//
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import net.laoyeye.yyblog.model.User;
//import javax.servlet.http.HttpServletRequest;
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import static java.lang.Boolean.FALSE;
//import static java.time.LocalDateTime.now;
//
///**
// * created by laoyeye on 2018/2/7 at 20:56
// */
//public class BlogSession implements Serializable {
//
//
//    private String id;
//    private String host;
//    private LocalDateTime startTimestamp = now();
//    private LocalDateTime lastAccessTime = now();
//    private long timeout = DEFAULT_TIMEOUT_MILLS;
//    private LocalDateTime expireTimestamp = now().plusSeconds(DEFAULT_TIMEOUT_MILLS / 1000);
//    private boolean expired = FALSE;
//    private User sessionUser;
//
//    public static final long DEFAULT_TIMEOUT_MILLS = 30 * 60 * 1000;
//
//    private static HttpServletRequest getRequest() {
//        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        return ra.getRequest();
//    }
//
//    /**
//     * 更新session
//     */
//    public void update() {
//        String info = "update session for id:[{}], at [{}] by url:[{}] with ip:[{}]";
//        log.info(LangUtils.string.placeholder(info, this.getId(), LocalDateTime.now(), getRequest().getRequestURL()), WebUtils.getRemoteAddr(getRequest()));
//        this.lastAccessTime = now();
//        this.expireTimestamp = lastAccessTime.plusSeconds(getTimeout() / 1000);
//        if (!host.equals(WebUtils.getRemoteAddr(getRequest()))) {
//            log.info("ip变动，存在非法访问情况");
//            this.expired = true;
//        }
//    }
//
//    /**
//     * 注销session，即把session变为过期状态
//     */
//    public void destroy() {
//        String info = "destroy session for id:[{}], at [{}]";
//        log.info(LangUtils.string.placeholder(info, this.getId(), LocalDateTime.now()));
//        this.expired = true;
//    }
//
//
//    public boolean isExpired() {
//        if (this.expired) {
//            return true;
//        }
//
//        long timeout = getTimeout();
//        if (timeout >= 0) {
//            LocalDateTime lastAccessTime = getLastAccessTime();
//            if (lastAccessTime == null) {
//                throw new IllegalStateException("最后访问时间为空");
//            }
//            LocalDateTime expire = getExpireTimestamp();
//            boolean isExpire = LocalDateTime.now().isAfter(lastAccessTime.plusSeconds(timeout / 1000));
//            log.info("当前时间：{}，最后访问时间：{}，过期时间：{}，session 是否过期：{}", LocalDateTime.now(), lastAccessTime, expire, isExpire);
//            if (isExpire) {
//                this.expired = true;
//            }
//            return isExpire;
//        }
//        this.expired = true;
//        return true;
//    }
//}
