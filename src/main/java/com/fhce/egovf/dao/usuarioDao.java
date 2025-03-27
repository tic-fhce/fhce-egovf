package com.fhce.egovf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.egovf.model.usuarioModel;

public interface usuarioDao extends JpaRepository<usuarioModel, Long>{
	
	@Query(value = "select * from usuario where _01cif=? and _07pass=?",nativeQuery=true)
	List<usuarioModel>getCif(Long cif,String pass);

	@Query(value = "select * from usuario where _02matricula=? and _07pass=?",nativeQuery=true)
	List<usuarioModel>getMatricula(Long matricula,String pass);
	
	@Query(value = "select * from usuario where _03ci=? and _04complemento=? and _07pass=?",nativeQuery=true)
	List<usuarioModel>getCi(Long ci,String complemento,String pass);
	
	@Query(value = "select * from usuario where _05correo=? and _07pass=?",nativeQuery=true)
	List<usuarioModel>getCorreo(String correo,String pass);
	
	@Query(value = "select * from usuario where _06celular=? and _07pass=?",nativeQuery=true)
	List<usuarioModel>getCelular(String celular,String pass);
	
	@Query(value = "select * from usuario where _01cif=?",nativeQuery=true)
	usuarioModel getUsuario(Long cif);

	@Query(value = "select * from usuario where _01cif=? ",nativeQuery=true)
	List<usuarioModel>getCif(Long cif);
	
	//@Query(value = "select * from usuario where _02matricula=? and _07pass=?",nativeQuery=true)
	//List<usuarioModel>getMatricula(Long matricula,String pass);
	
	@Query(value = "select * from usuario where _02matricula=?",nativeQuery=true)
	List<usuarioModel>getMatricula(Long matricula);
	
	//@Query(value = "select * from usuario where _03ci=? and _04complemento=? and _07pass=?",nativeQuery=true)
	//List<usuarioModel>getCi(Long ci,String complemento,String pass);
	
	@Query(value = "select * from usuario where _03ci=? and _04complemento=?",nativeQuery=true)
	List<usuarioModel>getCi(Long ci,String complemento);
	
	
	//@Query(value = "select * from usuario where _05correo=? and _07pass=?",nativeQuery=true)
	//List<usuarioModel>getCorreo(String correo,String pass);
	
	@Query(value = "select * from usuario where _05correo=?",nativeQuery=true)
	List<usuarioModel>getCorreo(String correo);
	
	//@Query(value = "select * from usuario where _06celular=? and _07pass=?",nativeQuery=true)
	//List<usuarioModel>getCelular(String celular,String pass);
	
	@Query(value = "select * from usuario where _06celular=?",nativeQuery=true)
	List<usuarioModel>getCelular(String celular);
	
	//@Query(value = "select * from usuario where _01cif=?",nativeQuery=true)
	//usuarioModel getUsuario(Long cif);
	
}
