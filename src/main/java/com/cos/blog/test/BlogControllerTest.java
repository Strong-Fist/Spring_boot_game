//스프링이 IoC(제어의 역전)를 할때 스캔을 하는데 기본값이 com.cos.blog의 이하를 스캔후 heap영역에다가 올려준다
package com.cos.blog.test;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//스프일이 com.cos.blog패키지 이하를 스캔해서 모든파일을 메모리에 new하는 것이 아니구요.
 //특정 어노테이션이 붇어있는 클래스 파일들을 new해서(IoC) 스프링컨테이너에 관리해줍니다.
@RestController
public class BlogControllerTest {
	
	//http://localhost:8080/test/hello
	@GetMapping("/test/hello")  
	public String hello() { 
		return "<h1>hello spring boot</h1>";
	}

}
