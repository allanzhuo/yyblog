package net.laoyeye.yyblog.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.query.UserQuery;
import net.laoyeye.yyblog.service.UserService;

/**
 * created by laoyeye on 2018/1/23 at 18:00
 */
@Controller
@RequestMapping("/management/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping
	public String index() {
		return "management/users";
	}

	@GetMapping("/list")
	@ResponseBody
	public DataGridResult list(UserQuery userQuery) {
		//查找非管理员
		DataGridResult result = userService.listPageUser(userQuery);
		return result;
	}

	@PostMapping("/edit/enable")
	@ResponseBody
	public YYBlogResult editEnable(Long id, Boolean enable) {
		YYBlogResult result = userService.updateEnableById(id, enable);
		return result;
	}
}
