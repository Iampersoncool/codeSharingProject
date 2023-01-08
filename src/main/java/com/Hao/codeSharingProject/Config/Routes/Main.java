package com.Hao.codeSharingProject.Config.Routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main {
	@GetMapping("/")
	public String IndexPage() {
		return "index";
	}
}
