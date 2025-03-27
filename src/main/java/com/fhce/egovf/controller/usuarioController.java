package com.fhce.egovf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.egovf.dto.passwordDtoRequest;
import com.fhce.egovf.dto.usuarioDtoResponse;
import com.fhce.egovf.service.usuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/fhce-egovf/user/usuario")
@RequiredArgsConstructor
public class usuarioController {
	
	private final usuarioService usuarioService;
	@GetMapping ("/getListaCiudadano")
	public ResponseEntity<List<usuarioDtoResponse>>getListaCiudadano(){
		try {
			return new ResponseEntity<>(this.usuarioService.getListaUsuario(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getUsuario")
	public ResponseEntity<List<usuarioDtoResponse>>getUsuario(@RequestParam (value="cif") Long cif){
		try {
			return new ResponseEntity<>(this.usuarioService.getListaUsuario(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/updateUsuario")
	public ResponseEntity<usuarioDtoResponse>updateUsuario(@RequestBody usuarioDtoResponse usuarioDtoResponse){
		try {
			return new ResponseEntity<>(this.usuarioService.updateUsuario(usuarioDtoResponse),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/updatePass")
	public boolean updatePass(@RequestBody passwordDtoRequest passwordDtoRequest){
		try {
			return this.usuarioService.updatePass(passwordDtoRequest);
		}catch (Exception e) {
			return false;
		}
	}
	@PutMapping("/updatePassAdmin")
	public ResponseEntity<usuarioDtoResponse>updatePassAdmin(@RequestBody passwordDtoRequest passwordDtoRequest){
		try {
			return new ResponseEntity<>(this.usuarioService.updatePassAdmin(passwordDtoRequest),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
