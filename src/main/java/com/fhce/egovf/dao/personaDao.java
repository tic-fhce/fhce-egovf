package com.fhce.egovf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.fhce.egovf.model.personaModel;

public interface personaDao extends JpaRepository<personaModel,Long>{
	
	@Query(value = "select * from persona where _10correo=?",nativeQuery=true)
	List<personaModel>getCorreo(String correo);
	
	@Query (value="select * from persona where _09cel = ?", nativeQuery=true)
	List<personaModel>getCelular(String celular);
	
	@Query (value ="select * from persona where _02ci=? and _03complemento =?",nativeQuery=true)
	List<personaModel>getCi(int ci, String c);
	
	@Query (value="select * from persona where _01cif= ? ", nativeQuery=true)
	List<personaModel>getCif(Long cif);
	
	@Query (value="select * from persona where _01cif= ? ", nativeQuery=true)
	personaModel getPerfil(Long cif);
	
	@Query (value="select * from persona where _01cif>0 ", nativeQuery=true)
	List<personaModel> getPersona();
	
	@Query (value="select * from persona where _01cif = 0 ", nativeQuery=true)
	List<personaModel> getPersonaCifCero();
}

