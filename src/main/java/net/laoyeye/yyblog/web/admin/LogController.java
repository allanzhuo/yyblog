package net.laoyeye.yyblog.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.laoyeye.yyblog.annotation.Log;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.query.LogQuery;
import net.laoyeye.yyblog.service.LogService;

/**
 * log控制器
 * @author 小卖铺的老爷爷
 * @date 2018年6月25日
 * @website www.laoyeye.net
 */
@Controller
@RequestMapping("/management/log")
public class LogController{
    @Autowired
    private LogService logService;

    @GetMapping
    public String logIndex() {
        return "management/log";
    }

    @ResponseBody
    @PostMapping("/list")
    public DataGridResult list(LogQuery query) {
        // 查询列表数据
        DataGridResult result = logService.list(query);
        return result;
    }

    /**
     * 删除
     */
    @Log("删除日志")
    @RequiresPermissions("sys:log:delete")
    @PostMapping("/remove/{id}")
    @ResponseBody
    public YYBlogResult remove(@PathVariable("id") Long id) {
        if (logService.remove(id) > 0) {
            return YYBlogResult.ok();
        }
        return YYBlogResult.build(403, "删除任务失败！");
    }

    /**
     * 删除
     */
    @Log("批量删除日志")
    @RequiresPermissions("sys:log:delete")
    @PostMapping("/removeBatch")
    @ResponseBody
    public YYBlogResult removeBatch(@RequestParam("ids[]") Long[] ids) {
        if (logService.removeBatch(ids) > 0) {
            return YYBlogResult.ok();
        }
        return YYBlogResult.build(403, "删除任务失败！");
    }
    
}
