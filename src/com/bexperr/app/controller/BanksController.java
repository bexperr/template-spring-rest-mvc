package com.bexperr.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class BanksController {
	
	@GetMapping
	public ResponseEntity<String> helloWorld(){
		return new ResponseEntity<>("Hello World",HttpStatus.OK);
	}

}
