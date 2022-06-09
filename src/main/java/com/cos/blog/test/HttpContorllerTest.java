package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

	// 사용자가 요청 -> 응답(HRML 파일)
	//@Controller
	@RestController
	// 사용자가 요청 -> 응답(Datepublic)
	class HttpContorllerTest {
		
		private static final String TAG= "HttpController Test : ";
		
		@GetMapping("/http/lombok") 
		public String lombokTest() {
			Member m = Member.builder().username("ssar").email("ssar@nate.com").password("1234").build();
			System.out.println(TAG+"getter : "+ m.getUsername());
			m.setUsername("cos");
			System.out.println(TAG+"getter : " + m.getUsername());
			return "lombok text 완료";
		}
//	
		//웹브라우져에서는 Get방식밖에 안됨
		//http://localhost:8080/http/get (select)
		@GetMapping("/auth/http/get")
		public String getTest(Member m) { //id=1&username=ssar&password=1234&email=ssar@naver.com
			return "get 요청" + m.getId() + "," + m.getUsername() +","   + m.getPassword() + "," + m.getEmail(); 
			// @RequstParam은 하나씩만 받을수 있다 ex) @RequestParam int id, @RequestParam String username
			// but 한번에 받을려면 클래스를 상속하면 된다 ex) Member m
		}
		
		
		//http://localhost:8080/http/post (insert)
		//html에서 from테크로 요청하는 것이랑 같음
		@PostMapping("/http/post") // text/plain, application/json
		public String postTest(@RequestBody Member m) { // Message Converter (스트링부트)
			return "post 요청 : " + m.getId() + "," + m.getUsername() +","   + m.getPassword() + "," + m.getEmail();
			// Body을 요청받을때는 @ResquestBody로 받는다\
			// text로 자료를 수정하기 어려우니 JSON으로 받는다 그떄 Member m으로 받을수 있다
		}
		
		//http://localhost:8080/http/put (update)
		@PutMapping("/http/put")
		public String putTest(@RequestBody Member m) {
			return "put 요청" + m.getId() + "," + m.getUsername() +","   + m.getPassword() + "," + m.getEmail(); 
		}
		
		//http://localhost:8080/http/delete (delete)
		@DeleteMapping("/http/delete")
		public String deleteTest(@RequestBody Member m) {
			return "delete 요청" + m.getId() + "," + m.getUsername() +","   + m.getPassword() + "," + m.getEmail(); 
		} 
	}
