package com.fhce.egovf.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.egovf.dao.menuDao;
import com.fhce.egovf.dao.menuUsuarioDao;
import com.fhce.egovf.dao.moduloMenuUsuarioDao;
import com.fhce.egovf.dao.modulomenuDao;
import com.fhce.egovf.dao.permisoDao;
import com.fhce.egovf.dao.usuarioDao;
import com.fhce.egovf.model.loginModel;
import com.fhce.egovf.model.menuModel;
import com.fhce.egovf.model.menuUsuarioModel;
import com.fhce.egovf.model.moduloMenuUsuarioModel;
import com.fhce.egovf.model.modulomenuModel;
import com.fhce.egovf.model.usuarioModel;
import com.fhce.egovf.obj.menuObj;
import com.fhce.egovf.obj.moduloObj;
import com.fhce.egovf.obj.sesionObj;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/fhce-egovf") //RequestMapping for Develop
//@RequestMapping("/fhce") // RequestMapping for Production
public class login {
	@Autowired
	private usuarioDao usuarioDao;
	
	@Autowired
	private permisoDao permisoDao;
	
	
	@PostMapping ("/loginUsuario")
	public sesionObj loginToken(@RequestBody loginModel loginModel) throws Exception {
		
		sesionObj sesionObj = new sesionObj();
		
		//Creamos la lista del usuario logueado de acuerdo al id de identificacion
		List<usuarioModel>listaUsuarioModel = new ArrayList<usuarioModel>();
		String pass = pass(loginModel.get_07pass());
		switch(loginModel.getId()) {
			case 1:
				listaUsuarioModel=usuarioDao.getCif(loginModel.get_01cif(),pass);
				break;
			case 2:
				listaUsuarioModel=usuarioDao.getMatricula(loginModel.get_02matricula(),pass);
				break;
			case 3:
				listaUsuarioModel=usuarioDao.getCi(loginModel.get_03ci(),loginModel.get_04complemento(), pass);
				break;
			case 4:
				listaUsuarioModel=usuarioDao.getCorreo(loginModel.get_05correo(),pass);
				break;
			case 5:
				listaUsuarioModel=usuarioDao.getCelular(loginModel.get_06celular(), pass);
				break;
		}
		
		if(listaUsuarioModel.size()>0) { // si la lista es mayor a 0 el usuartio existe y creamos sus credenciales de acceso
			String token=getJwtToken(listaUsuarioModel.get(0).get_01cif().toString());
			
			sesionObj.setCif(listaUsuarioModel.get(0).get_01cif().toString());
			sesionObj.setCorreo(listaUsuarioModel.get(0).get_05correo());
			sesionObj.setCelular(listaUsuarioModel.get(0).get_06celular());
			sesionObj.setPass(pass);
			sesionObj.setToken(token);
			sesionObj.setUnidad(listaUsuarioModel.get(0).get_08unidad());
			sesionObj.setSigla(listaUsuarioModel.get(0).get_10sigla());
			sesionObj.setFoto(listaUsuarioModel.get(0).get_11foto());
			
			return sesionObj;
		}
		else {
			System.out.println("Retornamos Nullo");
			return null;
		}
		
	}

	private String getJwtToken(String get_01correo) {
		int min = 100;
		int duracion=min*60*1000;
		String secretKey="j@mes";
		List<GrantedAuthority> grantedAuthorities=AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		String token=Jwts
				.builder()
				.setId("j@m3s")
				.setSubject(get_01correo)
				.claim("authorities",grantedAuthorities.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList())
				)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+duracion))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		
		return token;
	}
	public String pass(String password) throws Exception {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
		String sha256 = DatatypeConverter.printHexBinary(digest).toLowerCase();
		return sha256;
	}

}
