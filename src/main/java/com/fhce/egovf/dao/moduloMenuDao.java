package com.fhce.egovf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.egovf.model.moduloMenuModel;

public interface moduloMenuDao extends JpaRepository<moduloMenuModel,Long >{
	@Query(value = "select * from modulomenu where _01titulo=?",nativeQuery=true)
	moduloMenuModel getModuloMenu(String titulo);

}
