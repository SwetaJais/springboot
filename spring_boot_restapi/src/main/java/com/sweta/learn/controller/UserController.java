package com.sweta.learn.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sweta.learn.beans.User;
import com.sweta.learn.beans.UserRepository;
import com.sweta.learn.beans.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userservice;

	//register user
	@PostMapping("/register")
	public User addUser(@RequestBody User user) {

		return userRepository.save(user);
	}
	
	//softdeleting user
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") int userId) {

		userRepository.deleteById((long) userId);
	
	}
	//harddeleting user
	@DeleteMapping("/harddelete/{userId}")
	@Transactional
	public void hardDeleteUser(@PathVariable("userId") long userId) {

		userRepository.hardDelete(userId);
		
	}
	
//get specific user
	@GetMapping("/{userId}")
	public Optional<User> userById(@PathVariable("userId") int userId) {

		return userRepository.findById((long) userId);
	}
	


	//update
	@PutMapping("/update")
	public User updateUser(@RequestBody User user) {

		return userRepository.save(user);
	}
	
	//search by name 
	@GetMapping("/search")
	public List<User> findAll(@RequestParam Optional<String> name)
	{
		return userRepository.findByName(name.orElse("_"));
	}
	
	//sort by dob or joiningdate
	
	@GetMapping("/sortUser/{pageNumber}/{pageSize}/{sortProperty}")
	public Page<User> UserPagination(@PathVariable Integer pageNumber,@PathVariable Integer pageSize,
			@PathVariable String sortProperty )
	{
		return userservice.getUserPagination(pageNumber, pageSize,sortProperty);
	}
	
	
}
