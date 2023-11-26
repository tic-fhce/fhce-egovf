package com.fhce.egovf.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.egovf.dao.personaDao;
import com.fhce.egovf.dao.usuarioDao;
import com.fhce.egovf.model.ciudadanoModel;
import com.fhce.egovf.model.personaModel;
import com.fhce.egovf.model.usuarioModel;

@RestController
@RequestMapping("/fhce-egovf") //RequestMapping for Develop
//@RequestMapping("/fhce") // RequestMapping for Production
public class personaController {
	
	@Autowired
	private personaDao personaDao;
	
	@Autowired
	private usuarioDao usuarioDao;
	
	@GetMapping("/getPerfil")
	public personaModel getPersona(@RequestParam (value="cif") Long cif) {
		personaModel persona= personaDao.getPerfil(cif);
		return(persona);
	}
	
	@PostMapping("/registrar")
	public List<Integer> registrar(@RequestBody personaModel personaModel) throws Exception {
		String c="00";
		Long aux=null;
		List<Integer> respuesta= new ArrayList();
		respuesta.add(0);
		respuesta.add(0);
		respuesta.add(0);
		
		respuesta.set(0, this.personaDao.getCi(personaModel.get_02ci(), personaModel.get_03complemento()).size());
		respuesta.set(1, this.personaDao.getCorreo(personaModel.get_10correo()).size());
		respuesta.set(2, this.personaDao.getCelular(personaModel.get_09cel()).size());
		
		int suma=0;
		for (int i=0;i<respuesta.size();i++)
			suma=suma+(int)respuesta.get(i);
		if (suma==0){
			
			for(int i=0;i<100;i++) {
				
				if (i<10)
					c="0"+Integer.toString(i);
				else 
					c=Integer.toString(i);
				
				aux=Long.parseLong(personaModel.cif(c));
				if(this.personaDao.getCif(aux).size()==0)
					break;
			}
			System.out.print(aux);
			personaModel.set_01cif(aux);
			this.personaDao.save(personaModel);
			
			usuarioModel usuarioModel=new usuarioModel();
			
			usuarioModel.set_01cif(personaModel.get_01cif());
			usuarioModel.set_02matricula((long) 0);
			usuarioModel.set_03ci(personaModel.get_02ci());
			usuarioModel.set_04complemento(personaModel.get_03complemento());
			usuarioModel.set_05correo(personaModel.get_10correo());
			usuarioModel.set_06celular(personaModel.get_09cel());
			usuarioModel.set_07pass(pass(personaModel.get_01cif().toString()));
			usuarioModel.set_08unidad("nn");
			usuarioModel.set_09dependiente("nn");
			usuarioModel.set_10sigla("Unidad");
			this.usuarioDao.save(usuarioModel);
			
		}
		return(respuesta);
		
	}
	
	@GetMapping("/listaPersona")
	public List<ciudadanoModel>listar(){
		
		List<personaModel>persona=this.personaDao.findAll();
		List<usuarioModel>usuario=this.usuarioDao.findAll();
		List<ciudadanoModel>ciudadano=new ArrayList<ciudadanoModel>();
		for(int i=0;i<persona.size();i++) {
			for(int j=0;j<usuario.size();j++) {
				if(persona.get(i).get_01cif().longValue()==usuario.get(j).get_01cif().longValue()) {
					personaModel personax=persona.get(i);
					usuarioModel usuariox=usuario.get(j);
					ciudadano.add(new ciudadanoModel((long) (i+1),personax.get_01cif(),personax.get_02ci(), personax.get_03complemento(),personax.get_04nombre(),personax.get_05paterno(),personax.get_06materno(),personax.get_09cel(),personax.get_10correo(),usuariox.get_08unidad(), usuariox.get_10sigla()));
					break;
				}
			}
		}
		return(ciudadano);
	}
	
	@GetMapping("/listaPersonas")
	public List<personaModel>listaPersonas(){
		return(this.personaDao.findAll());
	}
	
	public String pass(String password) throws Exception {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
		String sha256 = DatatypeConverter.printHexBinary(digest).toLowerCase();
		return sha256;
	}
	
	@PutMapping("/updatePersona")
	public void updatePersona(@RequestBody personaModel personaModel) {
		this.personaDao.save(personaModel);
		usuarioModel aux=usuarioDao.getUsuario(personaModel.get_01cif());
		aux.set_06celular(personaModel.get_09cel());
		this.usuarioDao.save(aux);
	}
}
