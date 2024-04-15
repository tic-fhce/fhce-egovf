package com.fhce.egovf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.egovf.dao.menuDao;
import com.fhce.egovf.dao.menuUsuarioDao;
import com.fhce.egovf.dao.moduloMenuUsuarioDao;
import com.fhce.egovf.dao.modulomenuDao;
import com.fhce.egovf.dao.permisoDao;
import com.fhce.egovf.dao.usuarioDao;
import com.fhce.egovf.model.menuModel;
import com.fhce.egovf.model.menuUsuarioModel;
import com.fhce.egovf.model.moduloMenuUsuarioModel;
import com.fhce.egovf.model.modulomenuModel;
import com.fhce.egovf.model.permisoModel;
import com.fhce.egovf.obj.menuObj;
import com.fhce.egovf.obj.moduloObj;

@RestController
@RequestMapping("/fhce-egovf/menu") //RequestMapping for Develop
//@RequestMapping("/fhce/menu") // RequestMapping for Production
public class menuController {
	
	@Autowired
	private menuDao menuDao;
	
	@Autowired
	private modulomenuDao modulomenuDao;
	
	@Autowired
	private permisoDao permisoDao;
	
	@Autowired
	private usuarioDao usuarioDao;
	
	@Autowired
	private moduloMenuUsuarioDao moduloMenuUsuarioDao;
	
	@Autowired
	private menuUsuarioDao menuUsuarioDao;
	
	@GetMapping("/getMenu")
	public List<moduloObj> getMenu(@RequestParam (value="cif") Long cif){
		
		List<modulomenuModel>listaModuloMenu = this.modulomenuDao.findAll(); // llamamos todos los modulos del menu
		List<modulomenuModel>listaModuloMenuAux = new ArrayList<modulomenuModel>(); // Creamos lista Auxiliar apara hacer la discriminacion
		
		List<menuModel>listaMenuModel = this.menuDao.findAll(); // llamamos todo el menu
		List<menuModel>listaMenuModelAux = new ArrayList<menuModel>(); // creamos lista Auxiliar para hacer la discriminacion
 		
		
		List<moduloObj>menu = new ArrayList<moduloObj>(); //Creamos una lista para el Objeto Modulo
		moduloObj moduloObj; // creamos un Objeto modulo
		List<menuObj>menuObj; // creamos un Objeto menu
		
		//Seleccionamos los modulos permitidos para el usuario
		List<moduloMenuUsuarioModel> moduloMenuUsuarioModel = this.moduloMenuUsuarioDao.getCif(cif); 
		moduloMenuUsuarioModel moduloMenuUsuarioModelAux;
		for(int i=0; i<moduloMenuUsuarioModel.size(); i++) {
			moduloMenuUsuarioModelAux = moduloMenuUsuarioModel.get(i);
			for(int j=0;j<listaModuloMenu.size();j++) {
				if(moduloMenuUsuarioModelAux.get_02idmodulomenu().longValue() == listaModuloMenu.get(j).getId().longValue())
					listaModuloMenuAux.add(listaModuloMenu.get(j));
			}
		}
		
		//Seleccionamos el menu permitido para el usuario 
		List<menuUsuarioModel>menuUsuarioModel = this.menuUsuarioDao.getCif(cif);
		menuUsuarioModel usuarioMenuModelAux;
		for(int i=0;i<menuUsuarioModel.size();i++) {
			usuarioMenuModelAux = menuUsuarioModel.get(i);
			for(int j=0;j<listaMenuModel.size();j++) {
				if(usuarioMenuModelAux.get_02idmenu().longValue() == listaMenuModel.get(j).getId().longValue()) {
					listaMenuModelAux.add(listaMenuModel.get(j));
				}
			}
		}
		
		
		//Creamos el Menu final para el usuario
		for(int i=0;i<listaModuloMenuAux.size();i++) {
			menuObj = new ArrayList<menuObj>();
			for (int j=0 ;j<listaMenuModelAux.size();j++) {
				if(listaModuloMenuAux.get(i).getId().longValue() == listaMenuModelAux.get(j).get_03id_modulomenu().longValue()) {
					menuObj.add(new menuObj(listaMenuModelAux.get(j).getId(),listaMenuModelAux.get(j).get_01titulo(),listaMenuModelAux.get(j).get_02ruta(),listaMenuModelAux.get(j).getId(),1));
				}
			}
			moduloObj = new moduloObj(listaModuloMenuAux.get(i).getId(),listaModuloMenuAux.get(i).get_01titulo(),listaModuloMenuAux.get(i).get_02icono(),menuObj);
			menu.add(moduloObj);
		}
		
		for(int i=0;i<menu.size();i++) {
			System.out.println(menu.get(i).getTitulo());
			for(int j=0;j<menu.get(i).getMenuObj().size();j++)
				System.out.println("==>"+menu.get(i).getMenuObj().get(j).getTitulo());
		}
		
		return (menu);
		
	}
	
	@GetMapping("/getMenuModulo")
	public moduloObj getMenuModulo(@RequestParam (value="cif") Long cif,@RequestParam (value="titulo") String titulo){
		
		modulomenuModel moduloMenuModel = this.modulomenuDao.getModuloMenu(titulo); // llamamos solo el modulo Requerido
		
		List<menuModel>listaMenuModel = this.menuDao.findAll(); // llamamos todo el menu
		List<menuModel>listaMenuModelAux = new ArrayList<menuModel>(); // creamos lista Auxiliar para hacer la discriminacion
		
		List<menuUsuarioModel>estados = new ArrayList<menuUsuarioModel>();
		List<menuObj>menuObj = new ArrayList<menuObj>(); // creamos un Objeto menu
		
		//Seleccionamos el menu permitido para el usuario 
		List<menuUsuarioModel>menuUsuarioModel = this.menuUsuarioDao.getMenuUsuario(cif);
		
		menuUsuarioModel menuUsuarioModelAux;
		
		for(int i=0;i<menuUsuarioModel.size();i++) {
			
			menuUsuarioModelAux = menuUsuarioModel.get(i);
			for(int j=0;j<listaMenuModel.size();j++) {
				if(menuUsuarioModelAux.get_02idmenu().longValue() == listaMenuModel.get(j).getId().longValue()) {
					listaMenuModelAux.add(listaMenuModel.get(j));
					estados.add(menuUsuarioModelAux);
				}
			}
		}
		
		//Creamos el Menu final para el usuario
		
		for (int j=0 ;j<listaMenuModelAux.size();j++) {
			if(moduloMenuModel.getId().longValue() == listaMenuModelAux.get(j).get_03id_modulomenu().longValue()) {
				menuObj.add(new menuObj(estados.get(j).getId(),listaMenuModelAux.get(j).get_01titulo(),listaMenuModelAux.get(j).get_02ruta(),estados.get(j).get_02idmenu(),estados.get(j).get_03estado()));
			}
		}		
		
		moduloObj menu = new moduloObj(moduloMenuModel.getId(),moduloMenuModel.get_01titulo(),moduloMenuModel.get_02icono(),menuObj); //Creamos una lista para el Objeto Modulo
		
		return (menu);
		
	}
	@PutMapping("/updateMenuUsuario")
	public void updateMenuUsuario(@RequestBody menuUsuarioModel menuUsuarioModel){
		this.menuUsuarioDao.save(menuUsuarioModel);
	}
	
	@GetMapping("/getListarMenu")
	public List<menuModel> getListarMenu() {
		List<menuModel>listaMenuModel = menuDao.findAll();
		
		return(listaMenuModel);
	}
	
	@GetMapping("/getListarPernicion")
	public List<menuModel> getListarPernicion(@RequestParam (value="cif") Long cif) {
		List<permisoModel>permisoModel=permisoDao.getPermisos(cif);
		List<menuModel>listaMenuModel = menuDao.findAll();
		List<menuModel>menuModelFinal= new ArrayList<menuModel>();
		for(int i=0;i<permisoModel.size();i++) {
			for(int j=0;j<listaMenuModel.size();j++) {
				if(permisoModel.get(i).get_02id_menu()==listaMenuModel.get(j).getId())
					menuModelFinal.add(listaMenuModel.get(j));
			}
		}
		return(menuModelFinal);
	}
	
	@PostMapping("/agregarPernicion")
	public void agregarPernicion(@RequestBody permisoModel permisoModel) {
		this.permisoDao.save(permisoModel);
	}
	
	@PostMapping("/addPerfil")
	public void addPerfil(@RequestBody menuUsuarioModel menuUsuarioModel) {
		this.menuUsuarioDao.save(menuUsuarioModel);
	}
	
	@PostMapping("/addMenuUsuario")
	public void addMenuUsuario(@RequestBody menuUsuarioModel menuUsuarioModel) {
		this.menuUsuarioDao.save(menuUsuarioModel);
	}
}
