package net.develop.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.develop.product.entity.Product;
import net.develop.product.entity.Users;
import net.develop.product.repository.UsersRepository;

@CrossOrigin(origins="http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UsersController {
	
	private Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private UsersRepository usersRepository;
	
	@GetMapping("/users")
	public ResponseEntity<Object> getAllUsers(){
		try {
			Iterable<Users> users = usersRepository.findAll();
			return new ResponseEntity<Object>(users, HttpStatus.OK);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable("id") Long id){
		try {
			Users user = usersRepository.findById(id).get();
			if(user != null) {
				return new ResponseEntity<Object>(user, HttpStatus.OK);
			}else {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody Users user){
		try {
			Users savedUser = usersRepository.save(user);
			return new ResponseEntity<Object>(savedUser, HttpStatus.OK);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @RequestBody Users user){
		try {
			user.setId(id);
			Users savedUser = usersRepository.save(user);
			return new ResponseEntity<Object>(savedUser, HttpStatus.OK);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id){
		try {
			usersRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}
}
