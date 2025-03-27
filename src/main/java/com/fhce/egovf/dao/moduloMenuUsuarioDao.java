package com.fhce.egovf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.egovf.model.moduloMenuUsuarioModel;

public interface moduloMenuUsuarioDao extends JpaRepository<moduloMenuUsuarioModel,Long >{
	
	@Query(value = "select * from modulomenuusuario where _01cif = ? and _03estado = 1",nativeQuery=true)
	List<moduloMenuUsuarioModel>getCif (Long cif);
	
	@Query(value = "select * from modulomenuusuario where _01cif = ? and _02idmodulomenu = ?",nativeQuery=true)
	moduloMenuUsuarioModel getIdModuloMenu (Long cif,Long idmodulomenu);

}


