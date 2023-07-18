package com.fhce.egovf.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.egovf.dao.menuDao;
import com.fhce.egovf.dao.permisoDao;
import com.fhce.egovf.dao.usuarioDao;
import com.fhce.egovf.model.menuModel;
import com.fhce.egovf.model.permisoModel;

@RestController
@RequestMapping("/fhce-egovf") //RequestMapping for Develop
//@RequestMapping("/fhce") // RequestMapping for Production
public class menuController {
	
	@Autowired
	private menuDao menuDao;
	
	@Autowired
	private permisoDao permisoDao;
	
	@Autowired
	private usuarioDao usuarioDao;
	
	@GetMapping("/getListarMenu")
	public List<menuModel> getListarMenu() {
		List<menuModel>listaMenuModel = menuDao.findAll();
		listaMenuModel.sort(Comparator.comparing(menuModel::get_04subnivel));
		for (int i =0;i<listaMenuModel.size();i++)
			System.out.println(listaMenuModel.get(i).get_04subnivel());
		return(listaMenuModel);
	}
	
	@GetMapping("/getListarPernicion")
	public List<menuModel> getListarPernicion(@RequestParam (value="cif") Long cif) {
		List<permisoModel>permisoModel=permisoDao.getPermisos(cif);
		List<menuModel>listaMenuModel = menuDao.findAll();
		listaMenuModel.sort(Comparator.comparing(menuModel::get_04subnivel));
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
}
