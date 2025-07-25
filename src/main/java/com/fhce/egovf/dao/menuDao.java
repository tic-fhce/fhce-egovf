package com.fhce.egovf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fhce.egovf.model.menuModel;
import com.fhce.egovf.model.moduloModel;

public interface menuDao extends JpaRepository<menuModel, Long>{
	
	@Query(value = "select * from menu where _04idmodulo=?",nativeQuery=true)
	List<menuModel> getMenu(Long idmodulo);

}

