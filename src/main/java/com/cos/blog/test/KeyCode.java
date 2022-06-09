package com.cos.blog.test;
import java.util.ArrayList;
import java.util.Random;


public class KeyCode {
	
	public void keycode(){
		Random random = new Random();
		int number;
		ArrayList keycode = new ArrayList();
		
		for(int i = 0; i<17;i++) {
			int a  = random.nextInt(3);
		
		
		switch (a) {
		case 0: //숫자
			number = random.nextInt(9) + 1;
			keycode.add(number);
			break;
			
		case 1: //소문자
			number = random.nextInt(122 - 97) + 97;
			keycode.add((char)number);
			break;
			
		case 2: // 대문자
			number = random.nextInt(91 - 65) + 65;
			keycode.add((char)number);
			break;
			}
		}

	}
}
