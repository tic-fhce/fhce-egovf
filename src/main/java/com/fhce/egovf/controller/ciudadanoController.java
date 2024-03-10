package com.fhce.egovf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.egovf.dao.personaDao;
import com.fhce.egovf.dao.usuarioDao;
import com.fhce.egovf.model.ciudadanoModel;
import com.fhce.egovf.model.personaModel;
import com.fhce.egovf.model.usuarioModel;
import com.fhce.egovf.obj.ciudadanoObj;
import com.fhce.egovf.obj.usuarioObj;

@RestController
@RequestMapping("/fhce-egovf") //RequestMapping for Develop
//@RequestMapping("/fhce") // RequestMapping for Production
public class ciudadanoController {
	
	@Autowired
	private personaDao personaDao;
	
	@Autowired
	private usuarioDao usuarioDao;
	
	@GetMapping("/getEgovf")
	public ciudadanoObj getEgovf(@RequestParam (value="cif") Long cif) {
		
		usuarioModel usuarioModel = usuarioDao.getUsuario(cif);
		personaModel personaModel = personaDao.getPerfil(cif);
		
		ciudadanoObj ciudadanoObj = new ciudadanoObj();
		
		ciudadanoObj.setIdPersona(personaModel.getId());
		ciudadanoObj.setNombre(personaModel.get_04nombre());
		ciudadanoObj.setPaterno(personaModel.get_05paterno());
		ciudadanoObj.setMaterno(personaModel.get_06materno());
		ciudadanoObj.setFecha(personaModel.get_07fecha());
		ciudadanoObj.setSexo(personaModel.get_08sexo());
		
		ciudadanoObj.setIdUsuario(usuarioModel.getId());
		ciudadanoObj.setCif(usuarioModel.get_01cif());
		ciudadanoObj.setMatricula(usuarioModel.get_02matricula());
		ciudadanoObj.setCi(usuarioModel.get_03ci()+" "+usuarioModel.get_04complemento());
		ciudadanoObj.setCi_com(usuarioModel.get_03ci());
		ciudadanoObj.setComplemento(usuarioModel.get_04complemento());
		ciudadanoObj.setCorreo(usuarioModel.get_05correo());
		ciudadanoObj.setCelular(usuarioModel.get_06celular());
		ciudadanoObj.setPass(usuarioModel.get_07pass());
		ciudadanoObj.setUnidad(usuarioModel.get_08unidad());
		ciudadanoObj.setDependiente(usuarioModel.get_09dependiente());
		ciudadanoObj.setSigla(usuarioModel.get_10sigla());
		ciudadanoObj.setFoto(usuarioModel.get_11foto());
		
		return(ciudadanoObj);
	}
	
	@GetMapping("/listarCiudadano")
	public List<ciudadanoObj>listarCiudadano(){
		
		List<personaModel>persona=this.personaDao.findAll();
		List<usuarioModel>usuario=this.usuarioDao.findAll();
		List<ciudadanoObj>ciudadano=new ArrayList<ciudadanoObj>();
		for(int i=0;i<persona.size();i++) {
			for(int j=0;j<usuario.size();j++) {
				if(persona.get(i).get_01cif().longValue() == usuario.get(j).get_01cif().longValue()) {
					personaModel personaModel=persona.get(i);
					usuarioModel usuarioModel=usuario.get(j);
					
					ciudadanoObj ciudadanoObj = new ciudadanoObj();
					
					ciudadanoObj.setIdPersona(personaModel.getId());
					ciudadanoObj.setNombre(personaModel.get_04nombre());
					ciudadanoObj.setPaterno(personaModel.get_05paterno());
					ciudadanoObj.setMaterno(personaModel.get_06materno());
					ciudadanoObj.setFecha(personaModel.get_07fecha());
					ciudadanoObj.setSexo(personaModel.get_08sexo());
					
					ciudadanoObj.setIdUsuario(usuarioModel.getId());
					ciudadanoObj.setCif(usuarioModel.get_01cif());
					ciudadanoObj.setMatricula(usuarioModel.get_02matricula());
					ciudadanoObj.setCi(usuarioModel.get_03ci()+" "+usuarioModel.get_04complemento());
					ciudadanoObj.setCi_com(usuarioModel.get_03ci());
					ciudadanoObj.setComplemento(usuarioModel.get_04complemento());
					ciudadanoObj.setCorreo(usuarioModel.get_05correo());
					ciudadanoObj.setCelular(usuarioModel.get_06celular());
					ciudadanoObj.setPass(usuarioModel.get_07pass());
					ciudadanoObj.setUnidad(usuarioModel.get_08unidad());
					ciudadanoObj.setDependiente(usuarioModel.get_09dependiente());
					ciudadanoObj.setSigla(usuarioModel.get_10sigla());
					ciudadanoObj.setFoto(usuarioModel.get_11foto());
					
					ciudadano.add(ciudadanoObj);
					
					break;
				}
			}
		}
		return(ciudadano);
	}
	
	@GetMapping("/getListaEmpleado")
	public List<ciudadanoObj>getListaEmpleado(){//Funcion que lista a los Ciudadanos que son empleados
		
		List<personaModel>persona=this.personaDao.findAll();
		List<usuarioModel>usuario=this.usuarioDao.findAll();
		List<ciudadanoObj>ciudadano=new ArrayList<ciudadanoObj>();
		for(int i=0;i<persona.size();i++) {
			for(int j=0;j<usuario.size();j++) {
				if(persona.get(i).get_01cif().longValue() == usuario.get(j).get_01cif().longValue() && usuario.get(j).get_12empleado() == 1) {
					personaModel personaModel=persona.get(i);
					usuarioModel usuarioModel=usuario.get(j);
					
					ciudadanoObj ciudadanoObj = new ciudadanoObj();
					
					ciudadanoObj.setIdPersona(personaModel.getId());
					ciudadanoObj.setNombre(personaModel.get_04nombre());
					ciudadanoObj.setPaterno(personaModel.get_05paterno());
					ciudadanoObj.setMaterno(personaModel.get_06materno());
					ciudadanoObj.setFecha(personaModel.get_07fecha());
					ciudadanoObj.setSexo(personaModel.get_08sexo());
					
					ciudadanoObj.setIdUsuario(usuarioModel.getId());
					ciudadanoObj.setCif(usuarioModel.get_01cif());
					ciudadanoObj.setMatricula(usuarioModel.get_02matricula());
					ciudadanoObj.setCi(usuarioModel.get_03ci()+" "+usuarioModel.get_04complemento());
					ciudadanoObj.setCi_com(usuarioModel.get_03ci());
					ciudadanoObj.setComplemento(usuarioModel.get_04complemento());
					ciudadanoObj.setCorreo(usuarioModel.get_05correo());
					ciudadanoObj.setCelular(usuarioModel.get_06celular());
					ciudadanoObj.setPass(usuarioModel.get_07pass());
					ciudadanoObj.setUnidad(usuarioModel.get_08unidad());
					ciudadanoObj.setDependiente(usuarioModel.get_09dependiente());
					ciudadanoObj.setSigla(usuarioModel.get_10sigla());
					ciudadanoObj.setFoto(usuarioModel.get_11foto());
					
					ciudadano.add(ciudadanoObj);
					
					break;
				}
			}
		}
		return(ciudadano);
	}

}
