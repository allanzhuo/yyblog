package net.laoyeye.yyblog.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import net.laoyeye.yyblog.annotation.Log;
import net.laoyeye.yyblog.common.Constant;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.SettingDO;
import net.laoyeye.yyblog.service.SettingService;
import net.laoyeye.yyblog.service.UploadService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年1月24日
 * @website www.laoyeye.net
 */
@Controller
@RequestMapping("/management/settings")
public class SettingsController {
    @Autowired
    private SettingService settingService;
    @Autowired
    private UploadService uploadService;
    
    @GetMapping
    public String index(Model model) {
        List<SettingDO> settings = settingService.listAll();
        Map<String, Object> attributeMap = new HashMap<String, Object>();
        for (SettingDO setting : settings) {
            attributeMap.put(setting.getCode(), setting.getValue());
        }
        model.addAllAttributes(attributeMap);
        return "management/settings";
    }

    @GetMapping("/qrcode")
    public String qrcode(Model model) {
        model.addAttribute("alipay", settingService.getValueByCode(Constant.ALIPAY));
        model.addAttribute("wechat", settingService.getValueByCode(Constant.WECHAT_PAY));
        return "management/qrcode";
    }

    @PostMapping("/edit")
    @ResponseBody
    public YYBlogResult editSettings(SettingDO setting) {
        
        return settingService.updateValueByCode(setting);
    }
    
    @Log("上传打赏码")
    @RequiresPermissions("sys:qrcode:upload")
    @PostMapping("/upload")
    @ResponseBody
    public YYBlogResult upload(@RequestParam(value = "file", required = false) MultipartFile file, String payType) {
        if (file != null) {
            return uploadService.uploadQrcode(file, payType);
        } else {
            return YYBlogResult.build(500, "上传文件为空！");
        }
    }
}
