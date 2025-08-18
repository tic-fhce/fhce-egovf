package com.fhce.egovf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.egovf.dto.ciudadanoDtoResponse;
import com.fhce.egovf.obj.responsableObj;
import com.fhce.egovf.service.ciudadanoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/fhce-egovf/user/ciudadano")
@RequiredArgsConstructor

public class ciudadanoController {
	
	private final ciudadanoService  ciudadanoService;
	
	@GetMapping ("/getListaCiudadano")
	public ResponseEntity<List<ciudadanoDtoResponse>>getListaCiudadano(){
		try {
			return new ResponseEntity<>(this.ciudadanoService.getListaCiudadano(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping ("/getListaCiudadanoPublico")
	public ResponseEntity<List<ciudadanoDtoResponse>>getListaCiudadanoPublico(){
		try {
			return new ResponseEntity<>(this.ciudadanoService.getListaCiudadanoPublico(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getEgovf")
	public ResponseEntity <ciudadanoDtoResponse> getEgovf(@RequestParam (value="cif") Long cif) {
		try {
			return new ResponseEntity<>(this.ciudadanoService.getEgovf(cif),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getListaEmpleado")
	public ResponseEntity <List<ciudadanoDtoResponse>> getListaEmpleado() {
		try {
			return new ResponseEntity<>(this.ciudadanoService.getListaEmpleado(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getListaResponsable")
	public ResponseEntity <List<responsableObj>> getListaResponsable() {
		try {
			return new ResponseEntity<>(this.ciudadanoService.getListaResponsable(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
