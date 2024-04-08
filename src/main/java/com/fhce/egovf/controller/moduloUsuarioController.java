package com.fhce.egovf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.egovf.dao.menuUsuarioDao;
import com.fhce.egovf.dao.moduloDao;
import com.fhce.egovf.dao.moduloMenuUsuarioDao;
import com.fhce.egovf.dao.moduloUsuarioDao;
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
	@PutMapping("/updateModuloUsuario")
	public void updateModuloUsuario(@RequestBody moduloUsuarioModel moduloUsuarioModel) {
		
		List<moduloUsuarioModel>lista = this.moduloUsuarioDao.findModuloUsuario(moduloUsuarioModel.get_01cif()); //seleccionamos una lista del usuario y sus modulos
		moduloUsuarioModel aux;
		for(int i=0;i<lista.size();i++) {
			aux=lista.get(i);
			if(aux.get_02id_modulo() == moduloUsuarioModel.get_02id_modulo()) { // preguntamos si el id del modulo coinside con el id del usuario
				aux.set_03estado(moduloUsuarioModel.get_03estado());
				this.moduloUsuarioDao.save(aux);
			}
		}
		moduloModel moduloModel = this.moduloDao.getModulo(moduloUsuarioModel.get_02id_modulo()); // seleccionamos el modulo para obtener el Id del Menu
		moduloMenuUsuarioModel moduloMenuUsuarioModel = this.moduloMenuUsuarioDao.getIdModuloMenu(moduloUsuarioModel.get_01cif(),moduloModel.get_04idmodulomenu()); //seleccionamos el menu del usuario segun su mmodulo
		moduloMenuUsuarioModel.set_03estado(moduloUsuarioModel.get_03estado()); // cambiamos el estado
		this.moduloMenuUsuarioDao.save(moduloMenuUsuarioModel);
		
	}
	
}
