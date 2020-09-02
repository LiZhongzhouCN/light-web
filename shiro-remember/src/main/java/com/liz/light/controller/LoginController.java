package com.liz.light.controller;

import com.liz.light.base.CommonResult;
import com.liz.light.entity.User;
import com.liz.light.util.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	@ResponseBody
	public CommonResult login(String username, String password, Boolean rememberMe) {
		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return CommonResult.success();
		} catch (UnknownAccountException e) {
			return CommonResult.failed(e.getMessage());
		} catch (IncorrectCredentialsException e) {
			return CommonResult.failed(e.getMessage());
		} catch (LockedAccountException e) {
			return CommonResult.failed(e.getMessage());
		} catch (AuthenticationException e) {
			return CommonResult.failed("认证失败！");
		}
	}

	@RequestMapping("/")
	public String redirectIndex() {
		return "redirect:/index";
	}

	@GetMapping("/403")
	public String forbid() {
		return "403";
	}

	@RequestMapping("/index")
	public String index(Model model) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("user", user);
		return "index";
	}
}
