package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일리턴 기본경로 : src/main/resoures/static
		// 리턴명 : /home.html
		// 풀경로 : src/main/resoures/static/home.html
		return "/home.html"; 
	}
	
	@GetMapping("/temp/img")
	public String templmg() {
		return "/a.jpg"; 
	}
	
	//jsp는 브라우저가 인식하지 못함
	@GetMapping("/temp/jsp")
	public String tempJsp() {
	    // prefix: /WEB-INF/views/
	    // suffix: .jsp
		// 풀네임 : /WEB-INF/views/test.jsp
		
		return "Test"; 
	}

}
