package net.laoyeye.yyblog.common;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 通用响应体
 * @author 小卖铺的老爷爷
 * @date 2017年9月18日
 * @website www.laoyeye.net
 */
public class YYBlogResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    public static YYBlogResult build(Integer code, String message, Object data) {
        return new YYBlogResult(code, message, data);
    }

    public static YYBlogResult ok(Object data) {
        return new YYBlogResult(data);
    }

    public static YYBlogResult ok() {
        return new YYBlogResult(null);
    }

    public YYBlogResult() {

    }

    public static YYBlogResult build(Integer code, String message) {
        return new YYBlogResult(code, message, null);
    }

    public YYBlogResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public YYBlogResult(Object data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.code == 200;
//    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为YYBlogResult对象
     * 
     * @param jsonData json数据
     * @param clazz YYBlogResult中的object类型
     * @return
     */
    public static YYBlogResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, YYBlogResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("message").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static YYBlogResult format(String json) {
        try {
            return MAPPER.readValue(json, YYBlogResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static YYBlogResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("message").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
