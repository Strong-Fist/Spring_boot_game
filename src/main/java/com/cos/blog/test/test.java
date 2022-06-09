package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class test {

	@GetMapping("auth/keycode")
	public String keycode() {
		return "1234";
	}
}
