package com.cos.blog.test;


import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;



// html파일이 아니라 date를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입(DI) - 메모리를 올림 
	private UserRepository userRepository; 
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id){
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) { 
			//Exception을 하나하나 잡기 귀찮으면 IllegalArgumentException말고 최고 부모인 Exception을 사용해도된다
			//하지만 정확하게 Exception을 잡기위해서는 하나하나 해봐야한다
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return  "삭제되었습니다." + id;
	}
	
	// save함수는 id를 전달하지 않으면 insert를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요.
	// email, password
	
	@Transactional //데이터베이스에 데이터 추가, 갱신, 삭제, 등으로 이루어진 작업을 처리하던 중 오류가 발생했을 떄 모든 작업들을 원상태로 되돌릴 수 있다
								// 모든작업들이 성공해야지만 최종적으로 데이터베이스에 반영하도록 한다.
								// 즉 오류발생시 자동 롤백함
								// 함수종료시 자동 commit이 됨
								// 영속성컨텍트에 1차 캐시에 영속화 된 것들과 비교해서 변경을 감지해서 자동으로 flush해줌 
								// => 더티체킹
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requsetUser) {
		// json를 받기 위해서는 @RequestBody라는 어노테이션이 필요함
		// 어노테이션 : 자바 소스 코드에 추가하여 사용할수 있는 메타데이터의 일종
		// 메타데이터 : 데이터를 설명하기 위한 데이터 EX) 도서관에서 책을 찾기위한 번호 (가 E123)
		// json 데이터를 요청 => Java Object(MessageConverter의 Jackson라이브러리가  변환해서 받아줘요
		System.out.println("id : " + id);
		System.out.println("password : " + requsetUser.getPassword());
		System.out.println("email : " + requsetUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});// 영속화
		
		user.setPassword(requsetUser.getPassword());
		user.setEmail(requsetUser.getEmail());		

		//userRepository.save(user);
		
		// 더티 체킹
		return user;
	}
	
	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll(); //findAll() 모든 데이터를 가져온다
	}
	
	// 한페이지당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction =Sort.Direction.DESC) Pageable pageable){ // direction = 방향
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users; 
	}
	
	// {id} 주소로 파마레터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id){
		//findBy() => 데이터베이스에서 데이터를 가져옴
		// user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아니야?
		// 그럼 return null이 리턴이 되거나.. 그럼 프로그램에 문제가 있지 않겠니?
		// Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해!!
		
//     //람다식 => 어던것을 가져와야하는지 몰라도 된다
//		User user = userRepository.findById(id).orElseThrow(()-> {
//				return new IllegalArgumentException("해당 유저는 없습니다.id : " + id);
//		});
		
		//interface는 new로 가져올수 없기때문에 익명클래스를 만들어서 가져와야한다
		//IllegalArgumentException => 잘못된 어떤 인수가 들어왔다(예외처리)
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다.");
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환 (웹르라우저가 이해할 수 있는 테이터) -> json(Gson라이브러리)
		// 스프링부스트 = MessagetConverter아는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 된다면 MessageConverter가 Jackson라이브러리 호출해서
		// user오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		return user;
	}
	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) { //key = value (약속된 규칙) 
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail()); 
		System.out.println("role : " + user.getRole());  
		System.out.println("creatDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);  
		userRepository.save(user); //insert할때 사용하는 것이 save(), update할때 사용안함(요청받은 데이터 제외한 모든 데이터를 null로 만듬)
		return "회원가입이 되었습니다."; 
	}
	
}
