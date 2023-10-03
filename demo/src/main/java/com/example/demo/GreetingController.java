package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// use annotation to configure java class to be a rest controller
@RestController
public class GreetingController {

	// instance variables
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	// declare an object GreetingComponent
	private GreetingComponent gc;
	
	// inject GreetingComponent object within constructor
	@Autowired
	public GreetingController(GreetingComponent gc) {
		this.gc = gc;
	}
	
	// use annotation configure to method to handle get request
	// method also configure to map to request parameter (if any)
	@GetMapping(path = {"/greeting","/greet"})
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
	    return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	// add a another method to call greeting  components's method getMessage()
	@GetMapping("/testgreeting")
	public ResponseEntity<String> getMessage() {
		return ResponseEntity.ok(gc.getMessage());
	}

}
