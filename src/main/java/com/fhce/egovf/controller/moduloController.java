package com.fhce.egovf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.egovf.dao.moduloDao;
import com.fhce.egovf.dao.moduloUsuarioDao;
import com.fhce.egovf.model.moduloModel;
import com.fhce.egovf.model.moduloUsuarioModel;

@RestController
//@RequestMapping("fhce-egovf") //RequestMapping for Develop
@RequestMapping("/fhce") // RequestMapping for Production
public class moduloController {
	
	@Autowired
	private moduloDao moduloDao;
	
	@Autowired
	private moduloUsuarioDao moduloUsuarioDao;
	
	@GetMapping("/listarAll")
	public List<moduloModel>listarAll(){
		return(this.moduloDao.findAll());
	}
	
	@GetMapping("/listarModuloCif")
	public List<moduloModel>listarModuloCif(@RequestParam (value="cif") Long cif){
		
		List<moduloUsuarioModel>listaUsuario=moduloUsuarioDao.findModuloUsuario(cif);
		List<moduloModel>lista=new ArrayList<moduloModel>();
		for(int i=0;i<listaUsuario.size();i++) {
			lista.add(this.moduloDao.findModulo(listaUsuario.get(i).get_02id_modulo()));
		}
		return(lista);
	}
}
