package com.fhce.egovf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.egovf.dao.moduloUsuarioDao;
import com.fhce.egovf.model.moduloUsuarioModel;

@RestController
@RequestMapping("fhce-egovf") //RequestMapping for Develop
//@RequestMapping("/fhce") // RequestMapping for Production
public class moduloUsuarioController {

	@Autowired
	private moduloUsuarioDao moduloUsuarioDao;
	
	@PostMapping("/agregarModulo")
	public void agregarModulo(@RequestBody moduloUsuarioModel moduloUsuarioModel) {
		this.moduloUsuarioDao.save(moduloUsuarioModel);
	}
}
