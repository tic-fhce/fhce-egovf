package com.fhce.egovf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.egovf.dao.menuUsuarioDao;
import com.fhce.egovf.dao.moduloDao;
import com.fhce.egovf.dao.moduloMenuUsuarioDao;
import com.fhce.egovf.dao.moduloUsuarioDao;
import com.fhce.egovf.model.menuUsuarioModel;
import com.fhce.egovf.model.moduloMenuUsuarioModel;
import com.fhce.egovf.model.moduloModel;
import com.fhce.egovf.model.moduloUsuarioModel;

@RestController
@RequestMapping("fhce-egovf/moduloUsuario") //RequestMapping for Develop
//@RequestMapping("/fhce") // RequestMapping for Production
public class moduloUsuarioController {
	
	@Autowired
	private moduloDao moduloDao;

	@Autowired
	private moduloUsuarioDao moduloUsuarioDao;
	
	@Autowired
	private moduloMenuUsuarioDao moduloMenuUsuarioDao;
	
	@Autowired
	private menuUsuarioDao menuUsuarioDao;
	
	@PostMapping("/agregarModulo")
	public void agregarModulo(@RequestBody moduloUsuarioModel moduloUsuarioModel) {
		
		moduloModel moduloModel = this.moduloDao.getModulo(moduloUsuarioModel.get_02id_modulo());
		
		moduloMenuUsuarioModel moduloMenuUsuarioModel = new moduloMenuUsuarioModel();
		moduloMenuUsuarioModel.set_01cif(moduloUsuarioModel.get_01cif());
		moduloMenuUsuarioModel.set_02idmodulomenu(moduloModel.get_04idmodulomenu());
		moduloMenuUsuarioModel.set_03estado(1);
		
		this.moduloMenuUsuarioDao.save(moduloMenuUsuarioModel);
		this.moduloUsuarioDao.save(moduloUsuarioModel);
	}
}
