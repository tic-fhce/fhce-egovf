package com.fhce.egovf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.egovf.model.moduloUsuarioModel;

public interface moduloUsuarioDao extends JpaRepository<moduloUsuarioModel,Long>{
	
	@Query(value = "select * from modulousuario where _01cif=?",nativeQuery=true)
	List<moduloUsuarioModel>findModuloUsuario(Long cif);

}
