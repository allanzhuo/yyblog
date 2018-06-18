package net.laoyeye.yyblog.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * url编码解码工具类
 * @author 小卖铺的老爷爷
 * @date 2018年4月24日
 * @website www.laoyeye.net
 */
public class UrlUtils {
	  /**
     * url解码,UTF-8
     *
     * @param input
     * @return
     */
    public static String urlDecode(String input) {
        return urlDecode(input, Charset.defaultCharset().displayName());
    }

    /**
     * URL解码
     *
     * @param input
     * @param encoding
     * @return
     */
    public static String urlDecode(String input, String encoding) {
        try {
            return URLDecoder.decode(input, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    /**
     * URL编码,UTF-8模式
     *
     * @param input
     * @return
     */
    public static String urlEncode(String input) {
        return urlEncode(input, Charset.defaultCharset().displayName());
    }

    /**
     * URL编码
     *
     * @param input
     * @param encoding
     * @return
     */
    public static String urlEncode(String input, String encoding) {
        try {
            return URLEncoder.encode(input, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }
    
	/**
     * 解析出url参数中的键值对 如 "Action=del&id=123"，解析出Action:del,id:123存入map中
     * 
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String,String> urlRequest(String strUrlParam){

        Map<String,String> mapRequest = new HashMap<String,String>();

        String[] arrSplit = null;

        // 每个键值为一组
        arrSplit = strUrlParam.split("[&]");
        for(String strSplit : arrSplit){
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            // 解析出键值
            if(arrSplitEqual.length > 1){
                // 正确解析
                mapRequest.put(arrSplitEqual[0],arrSplitEqual[1]);

            } else{
                if(arrSplitEqual[0] != ""){
                    // 只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0],"");
                }
            }
        }
        return mapRequest;
    }
 }
