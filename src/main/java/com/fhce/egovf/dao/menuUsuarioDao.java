package com.fhce.egovf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.egovf.model.menuUsuarioModel;

public interface menuUsuarioDao extends JpaRepository<menuUsuarioModel, Long>{
	
	@Query(value = "select * from menuusuario where _01cif = ? and _03estado = 1",nativeQuery=true)
	List<menuUsuarioModel>getCif (Long cif);
	
	@Query(value = "select * from menuusuario where _01cif = ? ",nativeQuery=true)
	List<menuUsuarioModel>getMenuUsuario (Long cif);

}
