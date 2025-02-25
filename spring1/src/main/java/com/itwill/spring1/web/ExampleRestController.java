package com.itwill.spring1.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring1.dto.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ExampleRestController {
	
	@GetMapping("/rest3")
	public String rest3() {
		log.debug("rest3()");
		
		return "안녕하세요, REST!";
	}
	
	@GetMapping("/rest4")
	public List<User> rest4() {
		log.debug("rest4()");
		
		List <User> list = new ArrayList<User>();
		User user1 = User.builder().username("이지해").age(19).build();
		User user2 = User.builder().username("이지호").age(20).build();
		list.add(user1);
		list.add(user2);
		
		return list;
	}
}
