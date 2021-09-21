package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class Member {
	private  int id; 
	private  String username;
	private  String password;
	private  String email;
	
	@Builder //내가 데이터베이스에서 넣고 싶은거랑 내가 직접넣고싶은거랑 구분해서 넣을수 있음
					// 숫서에 상관없이 값을 넣을수 있음
					// 생성자(new)를 통해 넣을때는 실수를 할 일이 없다 
	public Member(int id, String username, String password, String email) {
		this.id = id; 
		this.username = username;    
		this.password = password;     
		this.email = email;
	}
	
	
}
