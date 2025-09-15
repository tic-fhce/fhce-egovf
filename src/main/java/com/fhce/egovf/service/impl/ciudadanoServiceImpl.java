package com.fhce.egovf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fhce.egovf.dao.personaDao;
import com.fhce.egovf.dao.usuarioDao;
import com.fhce.egovf.dto.ciudadanoDtoResponse;
import com.fhce.egovf.model.personaModel;
import com.fhce.egovf.model.usuarioModel;
import com.fhce.egovf.obj.responsableObj;
import com.fhce.egovf.service.ciudadanoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ciudadanoServiceImpl implements ciudadanoService{
	//private final ModelMapper modelMapper;
	private final usuarioDao usuarioDao;
	private final personaDao personaDao;
	
	@Transactional
	public ciudadanoDtoResponse getEgovf(Long cif) {
		usuarioModel usuarioModel = usuarioDao.getUsuario(cif);
		personaModel personaModel = personaDao.getPerfil(cif);
		
		ciudadanoDtoResponse ciudadanoDtoResponse = new ciudadanoDtoResponse(personaModel.getId(),personaModel.getNombre(),
				personaModel.getPaterno(),personaModel.getMaterno(),personaModel.getFecha(),personaModel.getSexo(),
				usuarioModel.getId(),usuarioModel.getCif(),usuarioModel.getMatricula(),usuarioModel.getCi()+" "+usuarioModel.getComplemento(),
				usuarioModel.getCi(),usuarioModel.getComplemento(),usuarioModel.getCorreo(),usuarioModel.getCelular(),
				usuarioModel.getPass(),usuarioModel.getUnidad(),usuarioModel.getDependiente(),usuarioModel.getSigla(),
				usuarioModel.getFoto(),usuarioModel.getEmpleado());
		
		return(ciudadanoDtoResponse);
	}
	
	@Transactional
	public List<ciudadanoDtoResponse> getListaCiudadano() {
		
		List<personaModel>persona=this.personaDao.getPersona();
		List<usuarioModel>usuario=this.usuarioDao.findAll();
		
		List<ciudadanoDtoResponse>ciudadanos = new ArrayList<ciudadanoDtoResponse>();
		for(int i=0;i<persona.size();i++) {
			for(int j=0;j<usuario.size();j++) {
				if(persona.get(i).getCif().longValue() == usuario.get(j).getCif().longValue()) {
					personaModel personaModel=persona.get(i);
					usuarioModel usuarioModel=usuario.get(j);			
					
					ciudadanoDtoResponse ciudadanoDtoResponse = new ciudadanoDtoResponse(personaModel.getId(),personaModel.getNombre(),
							personaModel.getPaterno(),personaModel.getMaterno(),personaModel.getFecha(),personaModel.getSexo(),
							usuarioModel.getId(),usuarioModel.getCif(),usuarioModel.getMatricula(),usuarioModel.getCi()+" "+usuarioModel.getComplemento(),
							usuarioModel.getCi(),usuarioModel.getComplemento(),usuarioModel.getCorreo(),usuarioModel.getCelular(),
							usuarioModel.getPass(),usuarioModel.getUnidad(),usuarioModel.getDependiente(),usuarioModel.getSigla(),
							usuarioModel.getFoto(),usuarioModel.getEmpleado());
					
					ciudadanos.add(ciudadanoDtoResponse);
					break;
				}
			}
		}
		return(ciudadanos);
	}
	
	@Transactional
	public List<ciudadanoDtoResponse> getListaCiudadanoPublico() {
		
		List<personaModel>persona=this.personaDao.getPersona();
		List<usuarioModel>usuario=this.usuarioDao.findAll();
		
		List<ciudadanoDtoResponse>ciudadanos = new ArrayList<ciudadanoDtoResponse>();
		for(int i=0;i<persona.size();i++) {
			for(int j=0;j<usuario.size();j++) {
				if((persona.get(i).getCif().longValue() == usuario.get(j).getCif().longValue()) && !"UTIC.png".equals(usuario.get(j).getFoto())) {
					personaModel personaModel=persona.get(i);
					usuarioModel usuarioModel=usuario.get(j);			
					
					ciudadanoDtoResponse ciudadanoDtoResponse = new ciudadanoDtoResponse(personaModel.getId(),personaModel.getNombre(),
							personaModel.getPaterno(),personaModel.getMaterno(),personaModel.getFecha(),personaModel.getSexo(),
							usuarioModel.getId(),usuarioModel.getCif(),usuarioModel.getMatricula(),usuarioModel.getCi()+" "+usuarioModel.getComplemento(),
							usuarioModel.getCi(),usuarioModel.getComplemento(),usuarioModel.getCorreo(),usuarioModel.getCelular(),
							usuarioModel.getPass(),usuarioModel.getUnidad(),usuarioModel.getDependiente(),usuarioModel.getSigla(),
							usuarioModel.getFoto(),usuarioModel.getEmpleado());
					
					ciudadanos.add(ciudadanoDtoResponse);
					break;
				}
			}
		}
		return(ciudadanos);
	}
	
	@Transactional
	public List<ciudadanoDtoResponse> getListaEmpleado() {
		List<personaModel>persona=this.personaDao.getPersona();
		List<usuarioModel>usuario=this.usuarioDao.getEmpleado(1);
		List<ciudadanoDtoResponse>ciudadano=new ArrayList<ciudadanoDtoResponse>();
		for(int i=0;i<persona.size();i++) {
			for(int j=0;j<usuario.size();j++) {
				if(persona.get(i).getCif().longValue() == usuario.get(j).getCif().longValue() && usuario.get(j).getEmpleado() == 1) {
					personaModel personaModel=persona.get(i);
					usuarioModel usuarioModel=usuario.get(j);
					
					ciudadanoDtoResponse ciudadanoDtoResponse = new ciudadanoDtoResponse(personaModel.getId(),personaModel.getNombre(),
							personaModel.getPaterno(),personaModel.getMaterno(),personaModel.getFecha(),personaModel.getSexo(),
							usuarioModel.getId(),usuarioModel.getCif(),usuarioModel.getMatricula(),usuarioModel.getCi()+" "+usuarioModel.getComplemento(),
							usuarioModel.getCi(),usuarioModel.getComplemento(),usuarioModel.getCorreo(),usuarioModel.getCelular(),
							usuarioModel.getPass(),usuarioModel.getUnidad(),usuarioModel.getDependiente(),usuarioModel.getSigla(),
							usuarioModel.getFoto(),usuarioModel.getEmpleado());
					
					ciudadano.add(ciudadanoDtoResponse);
					break;
				}
			}
		}
		return ciudadano;
		
	}
	
	@Transactional
	public List<responsableObj> getListaResponsable() {
		List<personaModel>persona=this.personaDao.findAll();
		
		List<responsableObj>ciudadanos=new ArrayList<responsableObj>();
		responsableObj responsableObj;
		for(int i=0;i<persona.size();i++) {
			responsableObj = new responsableObj();
			responsableObj.setId(persona.get(i).getId());
			responsableObj.setCif(persona.get(i).getCif());
			responsableObj.setNombre(persona.get(i).getNombre()+" "+persona.get(i).getPaterno()+" "+persona.get(i).getMaterno());
			responsableObj.setCi(persona.get(i).getCi()+" "+persona.get(i).getComplemento());
			responsableObj.setCelular(persona.get(i).getCel());
			ciudadanos.add(responsableObj);
		}
		return ciudadanos;
	}
}
