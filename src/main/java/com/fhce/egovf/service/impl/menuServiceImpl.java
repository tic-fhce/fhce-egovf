package com.fhce.egovf.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;
import com.fhce.egovf.dao.menuDao;
import com.fhce.egovf.dao.menuUsuarioDao;
import com.fhce.egovf.dao.moduloMenuDao;
import com.fhce.egovf.dao.moduloMenuUsuarioDao;
import com.fhce.egovf.dao.permisoDao;
import com.fhce.egovf.dto.menuDtoResponse;
import com.fhce.egovf.dto.menuUsuarioDtoRequest;
import com.fhce.egovf.dto.menuUsuarioDtoResponse;
import com.fhce.egovf.dto.moduloDtoResponse;
import com.fhce.egovf.dto.permisoDtoRequest;
import com.fhce.egovf.dto.permisoDtoResponse;
import com.fhce.egovf.model.menuModel;
import com.fhce.egovf.model.menuUsuarioModel;
import com.fhce.egovf.model.permisoModel;
import com.fhce.egovf.service.menuService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class menuServiceImpl implements menuService{
	
	private final menuDao menuDao;
	private final menuUsuarioDao menuUsuarioDao;
	private final ModelMapper modelMapper;
	private final permisoDao permisoDao;
	
	public List<moduloDtoResponse> getMenu(Long cif){
		
		/*List<moduloMenuModel>listaModuloMenu = this.moduloMenuDao.findAll(); // llamamos todos los modulos del menu
		List<moduloMenuModel>listaModuloMenuAux = new ArrayList<moduloMenuModel>(); // Creamos lista Auxiliar para hacer la discriminacion
		
		List<menuModel>listaMenuModel = this.menuDao.findAll(); // llamamos todo el menu
		List<menuModel>listaMenuModelAux = new ArrayList<menuModel>(); // creamos lista Auxiliar para hacer la discriminacion
 		
		
		List<moduloDtoResponse>menu = new ArrayList<moduloDtoResponse>(); //Creamos una lista para el Objeto Modulo
		moduloDtoResponse moduloDtoResponse; // creamos un Objeto modulo
		List<menuDtoObj>menuDtoResponse; // creamos un Objeto menu		
		
		//Seleccionamos los modulos permitidos para el usuario
		List<moduloMenuUsuarioModel> moduloMenuUsuarioModel = this.moduloMenuUsuarioDao.getCif(cif); 
		moduloMenuUsuarioModel moduloMenuUsuarioModelAux;
		for(int i=0; i<moduloMenuUsuarioModel.size(); i++) {
			moduloMenuUsuarioModelAux = moduloMenuUsuarioModel.get(i);
			for(int j=0;j<listaModuloMenu.size();j++) {
				if(moduloMenuUsuarioModelAux.getIdmodulomenu().longValue() == listaModuloMenu.get(j).getId().longValue())
					listaModuloMenuAux.add(listaModuloMenu.get(j));
			}
		}
		
		//Seleccionamos el menu permitido para el usuario 
		List<menuUsuarioModel>menuUsuarioModel = this.menuUsuarioDao.getCif(cif);
		menuUsuarioModel usuarioMenuModelAux;
		for(int i=0;i<menuUsuarioModel.size();i++) {
			usuarioMenuModelAux = menuUsuarioModel.get(i);
			for(int j=0;j<listaMenuModel.size();j++) {
				if(usuarioMenuModelAux.getIdmenu().longValue() == listaMenuModel.get(j).getId().longValue()) {
					listaMenuModelAux.add(listaMenuModel.get(j));
				}
			}
		}
		
		
		//Creamos el Menu final para el usuario
		for(int i=0;i<listaModuloMenuAux.size();i++) {
			menuDtoResponse = new ArrayList<menuDtoObj>();
			for (int j=0 ;j<listaMenuModelAux.size();j++) {
				if(listaModuloMenuAux.get(i).getId().longValue() == listaMenuModelAux.get(j).getId_modulomenu().longValue()) {
					menuDtoResponse.add(new menuDtoObj(listaMenuModelAux.get(j).getId(),listaMenuModelAux.get(j).getTitulo(),listaMenuModelAux.get(j).getRuta(),listaMenuModelAux.get(j).getId(),1));
				}
			}
			moduloDtoResponse = new moduloDtoResponse(listaModuloMenuAux.get(i).getId(),listaModuloMenuAux.get(i).getTitulo(),listaModuloMenuAux.get(i).getIcono(),menuDtoResponse,listaModuloMenuAux.get(i).getImportancia(),"");
			menu.add(moduloDtoResponse);
		}
		
		
		for(int i=0;i<menu.size()-1;i++) { // ordemanos los modulos del menu por la importancia
			for(int j=i+1;j<menu.size();j++) {
				if(menu.get(i).getImportancia()>menu.get(j).getImportancia()) {
					moduloDtoResponse menuAux = menu.get(i);
					menu.set(i, menu.get(j));
					menu.set(j, menuAux);
				}
			}
		}
		
		for(int i=0;i<menu.size();i++) {
			System.out.println(menu.get(i).getTitulo());
			for(int j=0;j<menu.get(i).getMenuDtoObj().size();j++)
				System.out.println("==>"+menu.get(i).getMenuDtoObj().get(j).getTitulo());
		}*/
		
		return (null);
		
	}
	public moduloDtoResponse getMenuModulo(Long cif, String titulo) {
		/*moduloMenuModel moduloMenuModel = this.moduloMenuDao.getModuloMenu(titulo); // llamamos solo el modulo Requerido
		
		List<menuModel>listaMenuModel = this.menuDao.findAll(); // llamamos todo el menu
		List<menuModel>listaMenuModelAux = new ArrayList<menuModel>(); // creamos lista Auxiliar para hacer la discriminacion
		
		List<menuUsuarioModel>estados = new ArrayList<menuUsuarioModel>();
		List<menuDtoObj>menuDtoResponse = new ArrayList<menuDtoObj>(); // creamos un Objeto menu
		
		//Seleccionamos el menu permitido para el usuario 
		List<menuUsuarioModel>menuUsuarioModel = this.menuUsuarioDao.getMenuUsuario(cif);
		
		menuUsuarioModel menuUsuarioModelAux;
		
		for(int i=0;i<menuUsuarioModel.size();i++) {
			
			menuUsuarioModelAux = menuUsuarioModel.get(i);
			for(int j=0;j<listaMenuModel.size();j++) {
				if(menuUsuarioModelAux.getIdmenu().longValue() == listaMenuModel.get(j).getId().longValue()) {
					listaMenuModelAux.add(listaMenuModel.get(j));
					estados.add(menuUsuarioModelAux);
				}
			}
		}
		
		//Creamos el Menu final para el usuario
		
		for (int j=0 ;j<listaMenuModelAux.size();j++) {
			if(moduloMenuModel.getId().longValue() == listaMenuModelAux.get(j).getId_modulomenu().longValue()) {
				menuDtoResponse.add(new menuDtoObj(estados.get(j).getId(),listaMenuModelAux.get(j).getTitulo(),listaMenuModelAux.get(j).getRuta(),estados.get(j).getIdmenu(),estados.get(j).getEstado()));
			}
		}		
		
		moduloDtoResponse menu = new moduloDtoResponse(moduloMenuModel.getId(),moduloMenuModel.getTitulo(),moduloMenuModel.getIcono(),menuDtoResponse,moduloMenuModel.getImportancia(),""); //Creamos una lista para el Objeto Modulo
		*/
		return (null);
	}
	
	@Transactional
	public menuUsuarioDtoResponse updateMenuUsuario(menuUsuarioDtoResponse menuUsuarioDtoResponse) {
		menuUsuarioModel menuUsuarioModel = modelMapper.map(menuUsuarioDtoResponse,menuUsuarioModel.class);
		this.menuUsuarioDao.save(menuUsuarioModel);
		return modelMapper.map(menuUsuarioModel, menuUsuarioDtoResponse.class);
	}
	@Transactional
	public List<menuDtoResponse> getListarMenu() {
		List<menuDtoResponse>listaMenuModel = this.menuDao.findAll().stream()
				.map(menu -> this.modelMapper.map(menu, menuDtoResponse.class))
				.collect(Collectors.toList());
		return(listaMenuModel);
	}
	
	@Transactional
	public List<menuDtoResponse> getListarPernicion(Long cif) {
		List<permisoModel>permisoModel = this.permisoDao.getPermisos(cif);
		List<menuModel>listaMenuModel = this.menuDao.findAll();
		
		List<menuDtoResponse>menuModelFinal= new ArrayList<menuDtoResponse>();
		
		for(int i=0;i<permisoModel.size();i++) {
			for(int j=0;j<listaMenuModel.size();j++) {
				if(permisoModel.get(i).getId_menu()==listaMenuModel.get(j).getId())
					menuModelFinal.add(this.modelMapper.map(listaMenuModel.get(j),menuDtoResponse.class));
			}
		}
		return(menuModelFinal);
	}
	@Transactional
	public permisoDtoResponse addPernicion (permisoDtoRequest permisoDtoRequest) {
		permisoModel permisoModel = this.modelMapper.map(permisoDtoRequest, permisoModel.class);
		this.permisoDao.save(permisoModel);
		return this.modelMapper.map(permisoModel, permisoDtoResponse.class);
	}
	
	@Transactional
	public menuUsuarioDtoResponse addMenuUsuario (menuUsuarioDtoRequest menuUsuarioDtoRequest) {
		
		menuUsuarioModel menuUsuarioModel = new menuUsuarioModel();
		menuUsuarioModel.setCif(menuUsuarioDtoRequest.getCif());
		menuUsuarioModel.setIdmenu(menuUsuarioDtoRequest.getIdmenu());
		menuUsuarioModel.setEstado(menuUsuarioDtoRequest.getEstado());
		
		this.menuUsuarioDao.save(menuUsuarioModel);
		
		return this.modelMapper.map(menuUsuarioModel, menuUsuarioDtoResponse.class);
	}
}
