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
import com.fhce.egovf.dao.permisoDao;
import com.fhce.egovf.dao.usuarioDao;
import com.fhce.egovf.model.loginModel;
import com.fhce.egovf.model.mainmenuModel;
import com.fhce.egovf.model.menuModel;
import com.fhce.egovf.model.permisoModel;
import com.fhce.egovf.model.sesionModel;
import com.fhce.egovf.model.subModel;
import com.fhce.egovf.model.usuarioModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
//@RequestMapping("/fhce-egovf") //RequestMapping for Develop
@RequestMapping("/fhce") // RequestMapping for Production
public class login {
	@Autowired
	private usuarioDao usuarioDao;
	
	@Autowired
	private menuDao menuDao;
	
	@Autowired
	private permisoDao permisoDao;
	
	@PostMapping ("/loginUsuario")
	public sesionModel loginToken(@RequestBody loginModel loginModel) throws Exception {
		List<usuarioModel>listaUsuarioModel= new ArrayList<usuarioModel>();
		String pass=pass(loginModel.get_07pass());
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
		
		if(listaUsuarioModel.size()>0) {
			System.out.print("usuario existe");
			String token=getJwtToken(listaUsuarioModel.get(0).get_01cif().toString());
			sesionModel sesion=new sesionModel();
			sesion.setCif(listaUsuarioModel.get(0).get_01cif().toString());
			sesion.setCorreo(listaUsuarioModel.get(0).get_05correo());
			sesion.setCelular(listaUsuarioModel.get(0).get_06celular());
			sesion.setPass(pass);
			sesion.setToken(token);
			
			List<menuModel>menuModel=menuDao.findAll();
			List<permisoModel> permisoModel=permisoDao.getPermisos(listaUsuarioModel.get(0).get_01cif());
			
			List<menuModel>menuModelAux=new ArrayList<menuModel>();
			List<mainmenuModel> mainMenu=new ArrayList<mainmenuModel>();
			for(int i=0;i<permisoModel.size();i++) {
				for(int j=0;j<menuModel.size();j++) {
					if(menuModel.get(j).getId()==permisoModel.get(i).get_02id_menu())
						menuModelAux.add(menuModel.get(j));
				}
			}
			for(int i=0;i<menuModelAux.size();i++) {				
				if(menuModelAux.get(i).get_03nivel()==0) {
					mainmenuModel menu=new mainmenuModel(menuModelAux.get(i).getId(),menuModelAux.get(i).get_01titulo(),menuModelAux.get(i).get_05obs(),menuModelAux.get(i).get_04subnivel());
					mainMenu.add(menu);
				}
			}
			for(int i=0;i<mainMenu.size();i++) {
				for(int j=0;j<menuModelAux.size();j++) {
					if(mainMenu.get(i).getIndex()==menuModelAux.get(j).get_04subnivel() && menuModelAux.get(j).get_03nivel()>0) {
						mainMenu.get(i).addSubModel(new subModel(menuModelAux.get(j).getId(),menuModelAux.get(j).get_01titulo(),menuModelAux.get(j).get_02ruta(),menuModelAux.get(j).get_05obs()));						
					}
				}
			}
			
			sesion.setMenu(mainMenu);
			return sesion;
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
