package com.fhce.egovf.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fhce.egovf.dao.usuarioDao;
import com.fhce.egovf.dto.loginDtoRequest;
import com.fhce.egovf.dto.loginDtoResponse;
import com.fhce.egovf.dto.roleDto;
import com.fhce.egovf.dto.userDto;
import com.fhce.egovf.model.usuarioModel;
import com.fhce.egovf.service.authenticationService;
import com.fhce.egovf.service.jwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class authenticationServiceImpl implements authenticationService{
	
	private final usuarioDao usuarioDao;
	private final jwtService jwtService;

	
	public loginDtoResponse login(loginDtoRequest loginDtoRequest){
		String password ="";
		try {
			password = pass(loginDtoRequest.getPass());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginDtoResponse loginDtoResponse = new loginDtoResponse();
		loginDtoResponse.setCif("0");
		
		//Creamos la lista del usuario logueado de acuerdo al id de identificacion
		List<usuarioModel>listaUsuarioModel = new ArrayList<usuarioModel>();
		switch(loginDtoRequest.getId()) {
			case 1:
				listaUsuarioModel = this.usuarioDao.getCif(loginDtoRequest.getCif(),password);
				break;
			case 2:
				listaUsuarioModel = this.usuarioDao.getMatricula(loginDtoRequest.getMatricula(),password);
				break;
			case 3:
				listaUsuarioModel = this.usuarioDao.getCi(loginDtoRequest.getCi(),loginDtoRequest.getComplemento(), password);
				break;
			case 4:
				listaUsuarioModel = this.usuarioDao.getCorreo(loginDtoRequest.getCorreo(),password);
				break;
			case 5:
				listaUsuarioModel = this.usuarioDao.getCelular(loginDtoRequest.getCelular(), password);
				break;
		}
		
		if(listaUsuarioModel.size()>0) { // si la lista es mayor a 0 el usuartio existe y creamos sus credenciales de acceso
			
			loginDtoResponse.setCif(listaUsuarioModel.get(0).getCif().toString());
			loginDtoResponse.setCorreo(listaUsuarioModel.get(0).getCorreo());
			loginDtoResponse.setCelular(listaUsuarioModel.get(0).getCelular());
			loginDtoResponse.setPass(password);
			loginDtoResponse.setUnidad(listaUsuarioModel.get(0).getUnidad());
			loginDtoResponse.setSigla(listaUsuarioModel.get(0).getSigla());
			loginDtoResponse.setFoto(listaUsuarioModel.get(0).getFoto());
			loginDtoResponse.setUsername(listaUsuarioModel.get(0).getCorreo());
			
			userDto user = new userDto();
			user.setUsername(loginDtoResponse.getUsername());
			user.setPassword(password);
			user.setRoleDto(roleDto.USER);
			var jwt = jwtService.generateToken(user);
			var refresh = jwtService.generateRefreshToken(new HashMap<>(),user);
			loginDtoResponse.setToken(jwt);
			loginDtoResponse.setRefresh(refresh);
		}
				
		return loginDtoResponse;
	}
	public String pass(String password) throws Exception {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
		StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            hexString.append(String.format("%02x", b));
        }
		return hexString.toString();
	}

}
