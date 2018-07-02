package net.laoyeye.yyblog.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.laoyeye.yyblog.enums.ResultEnum;

import java.util.Collection;
import java.util.List;

/**
 * Ajax 请求返回统一 VO
 * @author yangjian
 * @since 2017/7/25.
 */
public class NkResult<T> {

    /**
     * 错误代码,成功返回 000, 否则返回其他
     */
    private String code;

    /**
     * 返回提示信息
     */
    private String message;

    /**
     * 数据列表
     */
    private Collection<T> items;

    /**
     * 单条数据
     */
    private T item;

    /**
     * 附带信息
     */
    private Object extra;

    /**
     * 总记录数
     */
    private Integer count=0;

    /**
     * 页码
     */
    private Integer page = 0;

    /**
     * 每页记录数
     */
    private Integer pageSize = 0;

    public NkResult() {
    }

    /**
     * 推荐使用这个构造方法,方便统一修改
     * @param resultEnum
     */
    public NkResult(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getValue();
    }

    public NkResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public NkResult(String code, String message, List<T> items) {
        this.code = code;
        this.message = message;
        this.items = items;
    }

    public NkResult(String code, String message, T item) {
        this.code = code;
        this.message = message;
        this.item = item;
    }

    public NkResult(T item){
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getValue();
        this.item = item;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<T> getItems() {
        return items;
    }

    public void setItems(Collection<T> items) {
        this.items = items;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    /**
     * 返回一个成功的 VO
     * @return
     */
    public static NkResult success() {
        return new NkResult(ResultEnum.SUCCESS);
    }

    /**
     * 返回一个失败的 VO
     * @return
     */
    public static NkResult fail() {
        return new NkResult(ResultEnum.FAIL);
    }

    /**
     * 返回 ResultVo 实例
     * @return
     */
    public static NkResult instance(String code, String message) {
        return new NkResult(code, message);
    }

    /**
     * 返回 ResultVo 实例
     * @return
     */
    public static NkResult instance(ResultEnum resultEnum) {
        return new NkResult(resultEnum.getCode(), resultEnum.getValue());
    }

    /**
     * 返回 ResultVo 实例
     * @return
     */
    public static NkResult instance() {
        return new NkResult();
    }

    /**
     * 正确值判断
     * @return
     */
    public boolean isSuccess(){
        return ResultEnum.SUCCESS.getCode() == this.code;
    }

    /**
     *  输出 json 字符串
     * @return
     */
    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}