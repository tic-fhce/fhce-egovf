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

import com.fhce.egovf.dto.personaDtoRequest;
import com.fhce.egovf.dto.personaDtoResponse;
import com.fhce.egovf.service.personaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf/user/persona")
@RequiredArgsConstructor
public class personaController {
	
	private final personaService personaService;
	
	@GetMapping("/getPersona")
	public ResponseEntity<personaDtoResponse> getPersona(@RequestParam (value="cif") Long cif){
		try {
			return new ResponseEntity<>(this.personaService.getPersona(cif),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getPersonas")
	public ResponseEntity<List<personaDtoResponse>> getPersonas(){
		try {
			return new ResponseEntity<>(this.personaService.getPersonas(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getListaPersonaCifCero")
	public ResponseEntity<List<personaDtoResponse>> getPersonaCifCero(){
		try {
			return new ResponseEntity<>(this.personaService.getListaPersonaCifCero(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/addPersona")
	public ResponseEntity<personaDtoResponse> addPersona(@RequestBody personaDtoRequest personaDtoRequest){
		try {
			return new ResponseEntity<>(this.personaService.addPersona(personaDtoRequest),HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/addOnlyPersona")
	public ResponseEntity<personaDtoResponse> addOnlyPersona(@RequestBody personaDtoRequest personaDtoRequest){
		try {
			return new ResponseEntity<>(this.personaService.addOnlyPersona(personaDtoRequest),HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/updatePersona")
	public ResponseEntity<personaDtoResponse> updatePersona(@RequestBody personaDtoResponse personaDtoResponse){
		try {
			return new ResponseEntity<>(this.personaService.updatePersona(personaDtoResponse),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
