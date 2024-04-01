package com.fhce.egovf.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.egovf.dao.menuUsuarioDao;
import com.fhce.egovf.dao.personaDao;
import com.fhce.egovf.dao.usuarioDao;
import com.fhce.egovf.model.menuUsuarioModel;
import com.fhce.egovf.model.usuarioModel;
import com.fhce.egovf.obj.passObj;

@RestController
@RequestMapping("/fhce-egovf") //RequestMapping for Develop
//@RequestMapping("/fhce") // RequestMapping for Production
public class usuarioController {
	
	@Autowired
	private usuarioDao usuarioDao;
	
	@Autowired
	private personaDao personaDao;
	
	@Autowired
	private menuUsuarioDao menuUsuarioDao;
	
	@GetMapping("/listarUsuario")
	public List<usuarioModel> listar(){
		return usuarioDao.findAll();
	}
	@GetMapping("/getUsuario")
	public usuarioModel getUsuario(Long cif) {
		usuarioModel usuarioModel=usuarioDao.getUsuario(cif);
		return(usuarioModel);
	}
	@PutMapping("/updateUsuario")
	public void updateUsuario(@RequestBody usuarioModel usuarioModel) {
		
		if(usuarioModel.get_12empleado() == 2) {
			menuUsuarioModel menuUsuarioModel = new menuUsuarioModel();
			menuUsuarioModel.set_01cif(usuarioModel.get_01cif());
			menuUsuarioModel.set_02idmenu((long)7);
			menuUsuarioModel.set_03estado(1);
			
			this.menuUsuarioDao.save(menuUsuarioModel);
		}
		usuarioModel.set_12empleado(1);
		this.usuarioDao.save(usuarioModel);
	}
	
	@PutMapping("/updatePass")
	public boolean updatePass(@RequestBody passObj passObj)throws Exception {
		usuarioModel usuario = this.usuarioDao.getUsuario(passObj.getCif());
		if(usuario.get_07pass().equals(pass(passObj.getPass1()))) {
			usuario.set_07pass(pass(passObj.getPass3()));
			this.usuarioDao.save(usuario);
			return (true);
		}
		else
			return(false);
	}
	@PutMapping("/updatePassAdmin")
	public boolean updatePassAdmin(@RequestBody passObj passObj)throws Exception {
		usuarioModel usuario = this.usuarioDao.getUsuario(passObj.getCif());
		usuario.set_07pass(pass(passObj.getPass1()));
		this.usuarioDao.save(usuario);
		return (true);
	}
	
	public String pass(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
		String sha256 = DatatypeConverter.printHexBinary(digest).toLowerCase();
		return sha256;
	}
}
