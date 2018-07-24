package net.laoyeye.yyblog.web.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.laoyeye.yyblog.mapper.AboutMapper;
import net.laoyeye.yyblog.mapper.SettingMapper;
import net.laoyeye.yyblog.model.SettingDO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年2月9日
 * @website www.laoyeye.net
 */
@Api(value = "用户模块")
@Controller
@RequestMapping("/about")
public class AboutController {

    @Autowired
    private AboutMapper aboutMapper;
    @Autowired
    private SettingMapper settingMapper;
    
    @ApiOperation("获取用户信息")
    @GetMapping
    public String index(Model model) {
        List<SettingDO> settings = settingMapper.listAll();
        Map<String,Object> map = new HashMap<String,Object>();
        for (SettingDO setting : settings) {
            map.put(setting.getCode(), setting.getValue());
        }
        model.addAttribute("settings", map);
        model.addAttribute("abouts", aboutMapper.listAll());
        return "frontend/about";
    }
}
