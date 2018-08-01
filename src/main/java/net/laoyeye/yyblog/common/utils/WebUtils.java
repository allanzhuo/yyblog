package net.laoyeye.yyblog.common.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import net.laoyeye.yyblog.model.UserDO;
import net.laoyeye.yyblog.model.bo.IpInfoBO;

/**
 * 博客工具类
 * @author 小卖铺的老爷爷
 * @date 2018年4月13日
 * @website www.laoyeye.net
 */
public class WebUtils {

    /**
     * 删除不必要的信息，避免暴露过多信息
     *
     * @param user
     * @return
     */
    public static Map<String, Object> toMap(UserDO user) {
        if (user == null) {
            return null;
        }
        return Maps.hashMap("id", user.getId()
                , "nickname", user.getNickname()
                , "avatar", user.getAvatar());
    }

    public static String getIpAddress(HttpServletRequest request)
    {
      String ip;
      try
      {
              ip = request.getHeader("x-forwarded-for");
        if ((StringUtils.isEmpty(ip)) || ("unknown".equalsIgnoreCase(ip))) {
          ip = request.getHeader("Proxy-Client-IP");
        }
        if ((StringUtils.isEmpty(ip)) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
          ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((StringUtils.isEmpty(ip)) || ("unknown".equalsIgnoreCase(ip))) {
          ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if ((StringUtils.isEmpty(ip)) || ("unknown".equalsIgnoreCase(ip))) {
          ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if ((StringUtils.isEmpty(ip)) || ("unknown".equalsIgnoreCase(ip))) {
          ip = request.getRemoteAddr();
        }
      }
      catch (Exception e)
      {
        ip = request.getRemoteAddr();
      }
      return ip;
    }
    /* public static Map<String, Object> settingMap(List<XParam> xParams) {
        return xParams.stream()
                .filter(xParam -> !xParam.getName().equals("app_id") && !xParam.getName().equals("app_key"))
                .collect(Collectors.toMap(XParam::getName, XParam::getValue));
    }*/

    public static IpInfoBO getIpInfo(String ip) {
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
        String resp = HttpClientUtil.doGet(url);
        return JSONUtils.jsonToBean(resp, IpInfoBO.class);
    }

    public static String getIpCnInfo(IpInfoBO ipInfo) {
        String temp = ipInfo.getData().getCountry() + ipInfo.getData().getRegion() + ipInfo.getData().getCity();
        if (!ipInfo.getData().getCounty().toLowerCase().contains("x")) {
            return temp + ipInfo.getData().getCounty();
        } else {
            return temp;
        }
    }

/*    public static Map<String, Object> toLowerKeyMap(Map<String, Object> originMap) {
        if (CollectionUtils.isEmpty(originMap)) {
            return null;
        }
        Map<String, Object> newMap = new TreeMap<>();
        originMap.forEach((k, v) -> newMap.put(k.toLowerCase(), v));
        return newMap;
    }

    public static List<Map<String, Object>> toLowerKeyListMap(List<Map<String, Object>> originListMap) {
        if (CollectionUtils.isEmpty(originListMap)) {
            return null;
        }
        List<Map<String, Object>> newListMap = new LinkedList<>();
        originListMap.forEach(m -> newListMap.add(toLowerKeyMap(m)));
        return newListMap;
    }*/
}
