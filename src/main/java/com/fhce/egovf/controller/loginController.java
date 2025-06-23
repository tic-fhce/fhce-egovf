package com.fhce.egovf.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.egovf.dto.loginDtoRequest;
import com.fhce.egovf.dto.loginDtoResponse;
import com.fhce.egovf.service.authenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/fhce")
@RequiredArgsConstructor

public class loginController {
	private final authenticationService authenticationService;
	
	@PostMapping ("/login")
	public ResponseEntity<loginDtoResponse>login(@RequestBody loginDtoRequest loginDtoRequest){
		try {
			return new ResponseEntity<>(this.authenticationService.login(loginDtoRequest),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
