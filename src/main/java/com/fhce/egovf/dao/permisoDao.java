package com.fhce.egovf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fhce.egovf.model.permisoModel;

public interface permisoDao extends JpaRepository<permisoModel, Long> {
	@Query(value = "select * from permiso where _01cif=?",nativeQuery=true)
	List<permisoModel>getPermisos(Long cif);
}
