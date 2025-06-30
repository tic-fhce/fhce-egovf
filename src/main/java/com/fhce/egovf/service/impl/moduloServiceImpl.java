package com.fhce.egovf.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.egovf.dao.menuDao;
import com.fhce.egovf.dao.menuUsuarioDao;
import com.fhce.egovf.dao.moduloDao;
import com.fhce.egovf.dao.moduloMenuUsuarioDao;
import com.fhce.egovf.dao.moduloUsuarioDao;
import com.fhce.egovf.dto.menuDtoObj;
import com.fhce.egovf.dto.moduloDtoResponse;
import com.fhce.egovf.dto.moduloResponse;
import com.fhce.egovf.dto.moduloUsuarioDtoRequest;
import com.fhce.egovf.dto.moduloUsuarioDtoResponse;
import com.fhce.egovf.model.menuModel;
import com.fhce.egovf.model.menuUsuarioModel;
import com.fhce.egovf.model.moduloMenuUsuarioModel;
import com.fhce.egovf.model.moduloModel;
import com.fhce.egovf.model.moduloUsuarioModel;
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
	private final menuUsuarioDao menuUsuarioDao;
	
	@Transactional
	public List<moduloResponse>getModulos(Long cif){
		
		List<moduloResponse> modulos = this.moduloDao.findAll().stream()
				.map(modulo ->this.modelMapper.map(modulo, moduloResponse.class))
				.collect(Collectors.toList());
		
		List<moduloUsuarioModel>umodulos = this.moduloUsuarioDao.findModuloUsuario(cif);
		
		List<moduloResponse> selector = new ArrayList<moduloResponse>();
		boolean verificador = false;
		for (int i=0;i<modulos.size();i++) {
			verificador = true;
			for(int j=0;j<umodulos.size();j++) {
				if(modulos.get(i).getId().longValue()==umodulos.get(j).getId_modulo().longValue())
					verificador = false;
					break;
			}
			if(verificador) {
				selector.add(modulos.get(i));
			}
		}
		
		return(selector);
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
		
		List<moduloUsuarioModel> moduloUsuario = this.moduloUsuarioDao.findModuloUsuario(cif);//Seleccionamos todos los modulos correspondientes al CIF
		
		List<moduloModel>modulo = this.moduloDao.findAll();//Selecionamos todos los Modulos 
		
		List<menuModel>menuModel = this.menuDao.findAll();//Seleccionamos todos los Menus
		
		List<menuUsuarioModel>menuUsuario = this.menuUsuarioDao.getMenuUsuario(cif); // Seleccionamos todos los menus permitidos al CIF
		
		List<moduloDtoResponse> menu = new ArrayList<moduloDtoResponse>();
		
		List<menuDtoObj> menuDtoObj;
		for (int i=0;i<moduloUsuario.size();i++) {
			for(int j=0;j<modulo.size();j++) {
				if(moduloUsuario.get(i).getId_modulo().longValue() == modulo.get(j).getId().longValue()) {
					//preguntamos si el usuario tiene el modulo correspondiente
					menuDtoObj = new ArrayList<menuDtoObj>();
					
					for(int k = 0; k< menuModel.size();k++) {
						for(int l=0;l<menuUsuario.size();l++) {
							if((modulo.get(j).getId().longValue() == menuModel.get(k).getIdModulo().longValue()) && menuModel.get(k).getId().longValue() == menuUsuario.get(l).getIdmenu().longValue() ) {
								//preguntamos si se encuantra en el modulo el menu y el usuario
								menuDtoObj.add(new menuDtoObj(menuModel.get(k).getId(),menuModel.get(k).getTitulo(),menuModel.get(k).getRuta(),menuModel.get(k).getIcono(),menuModel.get(k).getIdModulo(),menuModel.get(k).getDescripcion()));
							}
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
		
		LocalDateTime fechaHoraActual = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = fechaHoraActual.format(formatter);
        
		// Agregamos al Ciudadano en la tabla moduloUsuario
		moduloUsuarioModel moduloUsuarioModel = new moduloUsuarioModel();
		moduloUsuarioModel.setCif(moduloUsuarioDtoRequest.getCif());
		moduloUsuarioModel.setId_modulo(moduloUsuarioDtoRequest.getId_modulo());
		moduloUsuarioModel.setEstado(moduloUsuarioDtoRequest.getEstado());
		moduloUsuarioModel.setFecha(fechaFormateada);
		moduloUsuarioModel.setFechamodificacion(fechaFormateada);
		moduloUsuarioModel.setQuien(moduloUsuarioDtoRequest.getQuien());
		this.moduloUsuarioDao.save(moduloUsuarioModel);
		return this.modelMapper.map(moduloUsuarioModel,moduloUsuarioDtoResponse.class); 
	}
	
	@Transactional 
	public moduloUsuarioDtoResponse updateModuloUsuario(moduloUsuarioDtoResponse moduloUsuarioDtoResponse) {
		LocalDateTime fechaHoraActual = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = fechaHoraActual.format(formatter);
        
		List<moduloUsuarioModel>lista = this.moduloUsuarioDao.findModuloUsuario(moduloUsuarioDtoResponse.getCif()); //seleccionamos una lista del usuario y sus modulos
		moduloUsuarioModel aux;
		for(int i=0;i<lista.size();i++) {
			aux=lista.get(i);
			aux.setEstado(moduloUsuarioDtoResponse.getEstado());
			aux.setQuien(moduloUsuarioDtoResponse.getQuien());
			aux.setFechamodificacion(fechaFormateada);
			this.moduloUsuarioDao.save(aux);
		}
		return (moduloUsuarioDtoResponse);
		
	}

}
