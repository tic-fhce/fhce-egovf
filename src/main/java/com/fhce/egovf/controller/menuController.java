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

import com.fhce.egovf.dto.menuDtoResponse;
import com.fhce.egovf.dto.menuUsuarioDtoRequest;
import com.fhce.egovf.dto.menuUsuarioDtoResponse;
import com.fhce.egovf.dto.moduloDtoResponse;
import com.fhce.egovf.dto.permisoDtoRequest;
import com.fhce.egovf.dto.permisoDtoResponse;
import com.fhce.egovf.service.menuService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fhce-egovf/user/menu")
@RequiredArgsConstructor
public class menuController {
	
	private final menuService menuService;
	
	@GetMapping("/getMenu")
	public ResponseEntity<List<moduloDtoResponse>> getMenu(@RequestParam (value="cif") Long cif){
		try {
			return new ResponseEntity<>(this.menuService.getMenu(cif),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getMenuModulo")
	public ResponseEntity<moduloDtoResponse> getMenuModulo(@RequestParam (value="cif") Long cif,@RequestParam (value="titulo") String titulo){
		try {
			return new ResponseEntity<>(this.menuService.getMenuModulo(cif,titulo),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updateMenuUsuario")
	public ResponseEntity<menuUsuarioDtoResponse> updateMenuUsuario(@RequestBody  menuUsuarioDtoResponse menuUsuarioDtoResponse){
		try {
			return new ResponseEntity<>(this.menuService.updateMenuUsuario(menuUsuarioDtoResponse),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getListarMenu")
	public ResponseEntity<List<menuDtoResponse>> getListarMenu(){
		try {
			return new ResponseEntity<>(this.menuService.getListarMenu(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getListarPernicion")
	public ResponseEntity<List<menuDtoResponse>> getListarPernicion(@RequestParam (value="cif") Long cif){
		try {
			return new ResponseEntity<>(this.menuService.getListarPernicion(cif),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/addPernicion")
	public ResponseEntity<permisoDtoResponse> addPernicion(@RequestBody permisoDtoRequest permisoDtoRequest){
		try {
			return new ResponseEntity<>(this.menuService.addPernicion(permisoDtoRequest),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/addMenuUsuario")
	public ResponseEntity<menuUsuarioDtoResponse> addMenuUsuario(@RequestBody menuUsuarioDtoRequest menuUsuarioDtoRequest){
		try {
			return new ResponseEntity<>(this.menuService.addMenuUsuario(menuUsuarioDtoRequest),HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
