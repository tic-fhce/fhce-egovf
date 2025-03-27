package com.fhce.egovf.service;

import java.util.List;

import com.fhce.egovf.dto.moduloDtoResponse;
import com.fhce.egovf.dto.moduloResponse;
import com.fhce.egovf.dto.moduloUsuarioDtoRequest;
import com.fhce.egovf.dto.moduloUsuarioDtoResponse;
import com.fhce.egovf.model.moduloModel;

public interface moduloService {
	List<moduloResponse>getModulos();
	
	List<moduloDtoResponse>getListaModuloCif(Long cif);
	
	moduloUsuarioDtoResponse addModulo(moduloUsuarioDtoRequest moduloUsuarioDtoRequest);
	
	moduloUsuarioDtoResponse updateModuloUsuario(moduloUsuarioDtoResponse moduloUsuarioDtoResponse);
	
	List<moduloDtoResponse>getModuloCif(Long cif);
	
	List<moduloResponse>getModuloCifEmpleado(Long cif);

}
