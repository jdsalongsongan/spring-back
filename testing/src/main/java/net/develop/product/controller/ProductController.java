package net.develop.product.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
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
import net.develop.product.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProductController {
	
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/products")
	public ResponseEntity<Object> getAllProducts(){
		try {
			Iterable<Product> products = productRepository.findAll();
			return new ResponseEntity<Object>(products, HttpStatus.OK);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Object> getProductById(@PathVariable("id") Long id){
		try {
			Product product = productRepository.findById(id).get();
			if(product != null) {
				return new ResponseEntity<Object>(product, HttpStatus.OK);
			}else {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/products")
	public ResponseEntity<Object> createProduct(@RequestBody Product product){
		try {
			Product savedProduct = productRepository.save(product);
			return new ResponseEntity<Object>(savedProduct, HttpStatus.OK);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
		try {
			product.setId(id);
			Product savedProduct = productRepository.save(product);
			return new ResponseEntity<Object>(savedProduct, HttpStatus.OK);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Long id){
		try {
			productRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
