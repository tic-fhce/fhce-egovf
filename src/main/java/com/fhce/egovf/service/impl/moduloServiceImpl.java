package com.fhce.egovf.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.egovf.dao.menuDao;
import com.fhce.egovf.dao.moduloDao;
import com.fhce.egovf.dao.moduloMenuUsuarioDao;
import com.fhce.egovf.dao.moduloUsuarioDao;
import com.fhce.egovf.dao.subMenuDao;
import com.fhce.egovf.dto.menuDtoObj;
import com.fhce.egovf.dto.moduloDtoResponse;
import com.fhce.egovf.dto.moduloResponse;
import com.fhce.egovf.dto.moduloUsuarioDtoRequest;
import com.fhce.egovf.dto.moduloUsuarioDtoResponse;
import com.fhce.egovf.model.menuModel;
import com.fhce.egovf.model.moduloMenuUsuarioModel;
import com.fhce.egovf.model.moduloModel;
import com.fhce.egovf.model.moduloUsuarioModel;
import com.fhce.egovf.model.subMenuModel;
import com.fhce.egovf.service.moduloService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class moduloServiceImpl implements moduloService{
	
	private final moduloDao moduloDao;
	private final ModelMapper modelMapper;
	private final moduloUsuarioDao moduloUsuarioDao;
	private final moduloMenuUsuarioDao moduloMenuUsuarioDao;
	private final menuDao menuDao;
	private final subMenuDao subMenuDao;
	@Transactional
	public List<moduloResponse>getModulos(){
		List<moduloResponse> modulos = this.moduloDao.findAll().stream()
				.map(modulo ->this.modelMapper.map(modulo, moduloResponse.class))
				.collect(Collectors.toList());
		return(modulos);
	}
	@Transactional
	public List<moduloDtoResponse>getListaModuloCif(Long cif){
		
		List<moduloUsuarioModel>listaUsuario = moduloUsuarioDao.findModuloUsuario(cif);
		
		List<moduloDtoResponse>lista=new ArrayList<moduloDtoResponse>();
		
		for(int i=0;i<listaUsuario.size();i++) {
			lista.add(this.modelMapper.map(this.moduloDao.getModulo(listaUsuario.get(i).getId_modulo()),moduloDtoResponse.class));
		}
		return(lista);
	}
	@Transactional
	public List<moduloDtoResponse>getModuloCif(Long cif){
		List<moduloUsuarioModel> moduloUsuario = this.moduloUsuarioDao.findModuloUsuario(cif);
		List<moduloModel>modulo = this.moduloDao.findAll();
		List<menuModel>menuModel = this.menuDao.findAll();
		
		List<moduloDtoResponse> menu = new ArrayList<moduloDtoResponse>();
		
		List<menuDtoObj> menuDtoObj;
		for (int i=0;i<moduloUsuario.size();i++) {
			for(int j=0;j<modulo.size();j++) {
				if(moduloUsuario.get(i).getId_modulo().longValue()==modulo.get(j).getId().longValue()) {
					menuDtoObj = new ArrayList<menuDtoObj>();
					for(int k = 0; k< menuModel.size();k++) {
						if(modulo.get(j).getId().longValue()==menuModel.get(k).getIdModulo().longValue()) {
							menuDtoObj.add(new menuDtoObj(menuModel.get(k).getId(),menuModel.get(k).getTitulo(),menuModel.get(k).getRuta(),menuModel.get(k).getIcono(),menuModel.get(k).getIdModulo(),menuModel.get(k).getDescripcion()));
						}
					}
					menu.add(new moduloDtoResponse(modulo.get(j).getId(),modulo.get(j).getNombre(),modulo.get(j).getImagen(),menuDtoObj,modulo.get(j).getTipo(),modulo.get(j).getDescripcion()));
				}
			}
		}
		return(menu);
	}
	
	@Transactional
	public List<moduloResponse>getModuloCifEmpleado(Long cif){
		List<moduloUsuarioModel> moduloUsuario = this.moduloUsuarioDao.findModuloUsuario(cif);
		List<moduloModel>modulo = this.moduloDao.findAll();
		
		List<moduloResponse> moduloResponse = new ArrayList<moduloResponse>();
		for (int i=0;i<moduloUsuario.size();i++) {
			for(int j=0;j<modulo.size();j++) {
				if(moduloUsuario.get(i).getId_modulo().longValue()==modulo.get(j).getId().longValue()) {
					moduloResponse.add(new moduloResponse(modulo.get(j).getId(),modulo.get(j).getNombre(),modulo.get(j).getRuta(),modulo.get(j).getImagen(),modulo.get(j).getTipo(),modulo.get(j).getDescripcion()));
				}
			}
		}
		return(moduloResponse);
	}
	
	@Transactional
	public moduloUsuarioDtoResponse addModulo(moduloUsuarioDtoRequest moduloUsuarioDtoRequest) {
		
		moduloUsuarioModel moduloUsuarioModel = new moduloUsuarioModel();
		moduloUsuarioModel.setCif(moduloUsuarioDtoRequest.getCif());
		moduloUsuarioModel.setId_modulo(moduloUsuarioDtoRequest.getId_modulo());
		moduloUsuarioModel.setEstado(moduloUsuarioDtoRequest.getEstado());
		this.moduloUsuarioDao.save(moduloUsuarioModel);
		return this.modelMapper.map(moduloUsuarioModel,moduloUsuarioDtoResponse.class); 
	}
	
	@Transactional 
	public moduloUsuarioDtoResponse updateModuloUsuario(moduloUsuarioDtoResponse moduloUsuarioDtoResponse) {
		
		List<moduloUsuarioModel>lista = this.moduloUsuarioDao.findModuloUsuario(moduloUsuarioDtoResponse.getCif()); //seleccionamos una lista del usuario y sus modulos
		moduloUsuarioModel aux;
		for(int i=0;i<lista.size();i++) {
			aux=lista.get(i);
			if(aux.getId_modulo() == moduloUsuarioDtoResponse.getId_modulo()) { // preguntamos si el id del modulo coinside con el id del usuario
				aux.setEstado(moduloUsuarioDtoResponse.getEstado());
				this.moduloUsuarioDao.save(aux);
			}
		}
		moduloModel moduloModel = this.moduloDao.getModulo(moduloUsuarioDtoResponse.getId_modulo()); // seleccionamos el modulo para obtener el Id del Menu
		moduloMenuUsuarioModel moduloMenuUsuarioModel = this.moduloMenuUsuarioDao.getIdModuloMenu(moduloUsuarioDtoResponse.getCif(),moduloModel.getId()); //seleccionamos el menu del usuario segun su mmodulo
		moduloMenuUsuarioModel.setEstado(moduloUsuarioDtoResponse.getEstado()); // cambiamos el estado
		this.moduloMenuUsuarioDao.save(moduloMenuUsuarioModel);
		
		return (moduloUsuarioDtoResponse);
		
	}

}
