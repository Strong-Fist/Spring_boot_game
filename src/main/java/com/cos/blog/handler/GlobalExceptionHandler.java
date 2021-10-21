package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice // 모든 Exception이 걸리면 이 class로 온다
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	// @ExceptionHandler는 value에 설정된 Exception만 받는다
	// 다른 Exception을 받고 싶으면 다른 Exception을 설정하면 된다
	// value에 Exception을 설정하면 모든 Exception을 받게 된다
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}
}
