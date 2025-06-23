package com.fhce.egovf.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.egovf.dto.moduloDtoResponse;
import com.fhce.egovf.dto.moduloResponse;
import com.fhce.egovf.dto.moduloUsuarioDtoRequest;
import com.fhce.egovf.dto.moduloUsuarioDtoResponse;
import com.fhce.egovf.service.moduloService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf/user/modulo")
@RequiredArgsConstructor
public class moduloController {
	
	private final moduloService moduloService;
	
	@GetMapping("/getModulos")
	public ResponseEntity<List<moduloResponse>> getListaModulos(@RequestParam (value="cif") Long cif){
		// correcto funcionando 
		try {
			return new ResponseEntity<>(this.moduloService.getModulos(cif),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getModuloCif")
	public ResponseEntity<List<moduloDtoResponse>> getModuloCif(@RequestParam (value="cif") Long cif){
		//Correcto funcionando
		try {
			return new ResponseEntity<>(this.moduloService.getModuloCif(cif),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getModuloCifEmpleado")
	public ResponseEntity<List<moduloResponse>> getModuloCifEmpleado(@RequestParam (value="cif") Long cif){
		//Correcto funcionando
		try {
			return new ResponseEntity<>(this.moduloService.getModuloCifEmpleado(cif),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getListaModuloCif")
	public ResponseEntity<List<moduloDtoResponse>> getListaModuloCif(@RequestParam (value="cif") Long cif){
		try {
			return new ResponseEntity<>(this.moduloService.getListaModuloCif(cif),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/addModulo")
	public ResponseEntity<moduloUsuarioDtoResponse> addModulo(@RequestBody moduloUsuarioDtoRequest moduloUsuarioDtoRequest){
		try {
			return new ResponseEntity<>(this.moduloService.addModulo(moduloUsuarioDtoRequest),HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/updateModuloUsuario")
	public ResponseEntity<moduloUsuarioDtoResponse> updateModuloUsuario(@RequestBody moduloUsuarioDtoResponse moduloUsuarioDtoResponse){
		try {
			return new ResponseEntity<>(this.moduloService.updateModuloUsuario(moduloUsuarioDtoResponse),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
