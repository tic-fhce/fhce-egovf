package com.fhce.egovf.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.egovf.dao.menuDao;
import com.fhce.egovf.dao.menuUsuarioDao;
import com.fhce.egovf.dao.moduloMenuUsuarioDao;
import com.fhce.egovf.dao.moduloUsuarioDao;
import com.fhce.egovf.dao.personaDao;
import com.fhce.egovf.dao.usuarioDao;
import com.fhce.egovf.dto.personaDtoRequest;
import com.fhce.egovf.dto.personaDtoResponse;
import com.fhce.egovf.model.menuModel;
import com.fhce.egovf.model.menuUsuarioModel;
import com.fhce.egovf.model.moduloUsuarioModel;
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
	private final moduloUsuarioDao moduloUsuarioDao; 
	private final menuDao menuDao;
	
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
		
		LocalDateTime fechaHoraActual = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = fechaHoraActual.format(formatter);
        
		String c="00";
		Long aux=null;
		//Creamos el codigo CIF de la persona
		for(int i=0;i<100;i++) {	
			if (i<10)
				c="0"+Integer.toString(i);
			else 
				c=Integer.toString(i);
			aux=Long.parseLong(personaDtoRequest.cif(c));
			if(this.personaDao.getCif(aux).size()==0)
				break;
		}
		
		//Creamos a la persona
		personaDtoRequest.setCif(aux);
		personaModel personaModel = new personaModel(); 
		personaModel.setCif(aux);
		personaModel.setCi(personaDtoRequest.getCi());
		personaModel.setComplemento(personaDtoRequest.getComplemento());
		personaModel.setNombre(personaDtoRequest.getNombre());
		personaModel.setPaterno(personaDtoRequest.getPaterno());
		personaModel.setMaterno(personaDtoRequest.getMaterno());
		personaModel.setFecha(personaDtoRequest.getFecha());
		personaModel.setSexo(personaDtoRequest.getSexo());
		personaModel.setCel(personaDtoRequest.getCel());
		personaModel.setCorreo(personaDtoRequest.getCorreo());
		this.personaDao.save(personaModel);
		
		//Creamos al Usuario
		usuarioModel usuarioModel = new usuarioModel(personaModel.getCif(),(long)0,personaModel.getCi(),personaModel.getComplemento()
				,personaModel.getCorreo(),personaModel.getCel(),pass(personaModel.getCif().toString()),"nn","nn","Unidad","user.png",0);
		
		this.usuarioDao.save(usuarioModel);
		
		//creamos el modulo Ciudadano para el usuario por defecto
		moduloUsuarioModel moduloUsuarioModel = new moduloUsuarioModel();
		moduloUsuarioModel.setCif(personaModel.getCif());
		moduloUsuarioModel.setId_modulo((long)6);
		moduloUsuarioModel.setFecha(fechaFormateada);
		moduloUsuarioModel.setEstado(1);
		moduloUsuarioModel.setFechamodificacion(fechaFormateada);
		moduloUsuarioModel.setQuien(aux);
		this.moduloUsuarioDao.save(moduloUsuarioModel);
		
		List<menuModel>menuModel = this.menuDao.getMenu(moduloUsuarioModel.getId_modulo());
		menuUsuarioModel menuUsuarioModel;
		
		//creamos el menu correspondiente al modulo 
		for(int i=0;i<menuModel.size();i++) {
			menuUsuarioModel = new menuUsuarioModel();
			menuUsuarioModel.setCif(personaModel.getCif());
			menuUsuarioModel.setIdmenu(menuModel.get(i).getId());
			menuUsuarioModel.setEstado(1);
			this.menuUsuarioDao.save(menuUsuarioModel);
		}
		
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
