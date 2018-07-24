package net.laoyeye.yyblog.model;

import java.io.Serializable;
/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年4月21日
 * @website www.laoyeye.net
 */
public class SettingDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String code;
    private String value;
    private String remark;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }


}
