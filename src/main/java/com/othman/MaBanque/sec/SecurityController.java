package com.othman.MaBanque.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/operation";
	}
	
	@RequestMapping("/403")
	public String AccessDenied() {
		return "403";
	}

}
