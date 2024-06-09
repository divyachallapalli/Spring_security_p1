package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerTest {
	
	List<String> userList= new ArrayList<>();

	@GetMapping(path = "/sayHello")
	public String sayHello() {
		return "Hello world";
	}
	
	

	@GetMapping(path = "/showVisitors")
	public String showVisitors() {
		if(userList.isEmpty()) return "No Visitors";
		else {
			return userList.toString();
		}
	}
	
	
	@PostMapping(path="/addToList")
	public String showYourName(@RequestParam("name") String name) {
		userList.add(name);
		return "visitor added";
	}
}
