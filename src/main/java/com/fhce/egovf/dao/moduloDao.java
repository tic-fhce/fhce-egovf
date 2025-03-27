package com.fhce.egovf.dao;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.egovf.model.moduloModel;
import com.fhce.egovf.model.moduloUsuarioModel;


public interface moduloDao extends JpaRepository<moduloModel,Long > {

	@Query(value = "select * from modulo where id=?",nativeQuery=true)
	moduloModel getModulo(Long id);
}

