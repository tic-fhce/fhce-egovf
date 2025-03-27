package com.fhce.egovf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.egovf.dto.loginDtoRequest;
import com.fhce.egovf.dto.loginDtoResponse;
import com.fhce.egovf.model.usuarioModel;
import com.fhce.egovf.service.authenticationService;
import com.fhce.egovf.service.usuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/fhce")
@RequiredArgsConstructor

public class loginController {
	private final usuarioService userService;
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
