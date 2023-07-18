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

import com.fhce.egovf.dao.personaDao;
import com.fhce.egovf.dao.usuarioDao;
import com.fhce.egovf.model.passModel;
import com.fhce.egovf.model.usuarioModel;

@RestController
@RequestMapping("/fhce-egovf") //RequestMapping for Develop
//@RequestMapping("/fhce") // RequestMapping for Production
public class usuarioController {
	
	@Autowired
	private usuarioDao usuarioDao;
	
	@Autowired
	private personaDao personaDao;
	
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
		this.usuarioDao.save(usuarioModel);
	}
	
	@PutMapping("/updatePass")
	public boolean updatePass(@RequestBody passModel passModel)throws Exception {
		usuarioModel usuario=new usuarioModel();
		if(passModel.get_07pass().equals(pass(passModel.get_08pass()))) {
			usuario.setId(passModel.getId());
			usuario.set_01cif(passModel.get_01cif());
			usuario.set_02matricula(passModel.get_02matricula());
			usuario.set_03ci(passModel.get_03ci());
			usuario.set_04complemento(passModel.get_04complemento());
			usuario.set_05correo(passModel.get_05correo());
			usuario.set_06celular(passModel.get_06celular());
			usuario.set_07pass(pass(passModel.get_09pass()));
			this.usuarioDao.save(usuario);
			return (true);
		}
		else
			return(false);
	}
	@PutMapping("/updatePassAdmin")
	public boolean updatePassAdmin(@RequestBody passModel passModel)throws Exception {
		usuarioModel usuario=new usuarioModel();
		usuario.setId(passModel.getId());
		usuario.set_01cif(passModel.get_01cif());
		usuario.set_02matricula(passModel.get_02matricula());
		usuario.set_03ci(passModel.get_03ci());
		usuario.set_04complemento(passModel.get_04complemento());
		usuario.set_05correo(passModel.get_05correo());
		usuario.set_06celular(passModel.get_06celular());
		usuario.set_07pass(pass(passModel.get_09pass()));
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
