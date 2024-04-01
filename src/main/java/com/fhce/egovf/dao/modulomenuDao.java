package com.fhce.egovf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.egovf.model.modulomenuModel;

public interface modulomenuDao extends JpaRepository<modulomenuModel,Long >{
	@Query(value = "select * from modulomenu where _01titulo=?",nativeQuery=true)
	modulomenuModel getModuloMenu(String titulo);

}
