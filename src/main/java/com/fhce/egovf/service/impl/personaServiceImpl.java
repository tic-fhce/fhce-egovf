package com.fhce.egovf.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.egovf.dao.menuUsuarioDao;
import com.fhce.egovf.dao.moduloMenuUsuarioDao;
import com.fhce.egovf.dao.personaDao;
import com.fhce.egovf.dao.usuarioDao;
import com.fhce.egovf.dto.personaDtoRequest;
import com.fhce.egovf.dto.personaDtoResponse;
import com.fhce.egovf.model.menuUsuarioModel;
import com.fhce.egovf.model.moduloMenuUsuarioModel;
import com.fhce.egovf.model.personaModel;
import com.fhce.egovf.model.usuarioModel;
import com.fhce.egovf.service.personaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class personaServiceImpl implements personaService{
	
	private final personaDao personaDao; 
	private final ModelMapper modelMapper;
	private final menuUsuarioDao menuUsuarioDao;
	private final moduloMenuUsuarioDao moduloMenuUsuarioDao;
	private final usuarioDao usuarioDao;
	
	@Transactional
	public personaDtoResponse getPersona(Long cif) {
		personaModel personaModel = this.personaDao.getPerfil(cif);
		return(this.modelMapper.map(personaModel, personaDtoResponse.class));
	}
	
	@Transactional
	public List<personaDtoResponse>getPersonas(){
		List<personaDtoResponse>personas = this.personaDao.findAll().stream()
				.map(persona -> this.modelMapper.map(persona, personaDtoResponse.class))
				.collect(Collectors.toList());
		
		return(personas);
	}
	
	@Transactional
	public personaDtoResponse addPersona(personaDtoRequest personaDtoRequest) throws Exception {
		String c="00";
		Long aux=null;
		
		for(int i=0;i<100;i++) {	
			if (i<10)
				c="0"+Integer.toString(i);
			else 
				c=Integer.toString(i);
			aux=Long.parseLong(personaDtoRequest.cif(c));
			if(this.personaDao.getCif(aux).size()==0)
				break;
		}
		personaDtoRequest.setCif(aux);
		personaModel personaModel = this.modelMapper.map(personaDtoRequest, personaModel.class);
		this.personaDao.save(personaModel);
		
		usuarioModel usuarioModel = new usuarioModel(personaModel.getCif(),(long)0,personaModel.getCi(),personaModel.getComplemento()
				,personaModel.getCorreo(),personaModel.getCel(),pass(personaModel.getCif().toString()),"nn","nn","Unidad","https://fhcevirtual.umsa.bo/egovf-img/imagenes/user.png",0);
		
		moduloMenuUsuarioModel moduloMenuUsuarioModel = new moduloMenuUsuarioModel();
		moduloMenuUsuarioModel.setCif(personaModel.getCif());
		moduloMenuUsuarioModel.setIdmodulomenu((long)3);
		moduloMenuUsuarioModel.setEstado(1);
		
		menuUsuarioModel menuUsuarioModel = new menuUsuarioModel();
		menuUsuarioModel.setCif(personaModel.getCif());
		menuUsuarioModel.setIdmenu((long)5);
		menuUsuarioModel.setEstado(1);
		
		this.menuUsuarioDao.save(menuUsuarioModel);
		this.moduloMenuUsuarioDao.save(moduloMenuUsuarioModel);
		
		this.usuarioDao.save(usuarioModel);

		return(this.modelMapper.map(personaModel, personaDtoResponse.class));
		
	}
	
	public personaDtoResponse updatePersona(personaDtoResponse personaDtoResponse) {
		
		personaModel personaModel = this.modelMapper.map(personaDtoResponse, personaModel.class);
		this.personaDao.save(personaModel);
		
		usuarioModel usuarioModel = this.usuarioDao.getUsuario(personaModel.getCif());
		usuarioModel.setCelular(personaModel.getCel());
		this.usuarioDao.save(usuarioModel);
		
		return (this.modelMapper.map(personaModel, personaDtoResponse.getClass()));
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
