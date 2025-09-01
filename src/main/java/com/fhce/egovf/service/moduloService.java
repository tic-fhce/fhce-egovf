package com.fhce.egovf.service;

import java.util.List;

import com.fhce.egovf.dto.moduloDtoResponse;
import com.fhce.egovf.dto.moduloResponse;
import com.fhce.egovf.dto.moduloUsuarioDtoRequest;
import com.fhce.egovf.dto.moduloUsuarioDtoResponse;
import com.fhce.egovf.obj.moduloObj;

public interface moduloService {
	List<moduloResponse>getModulos(Long cif);
	
	List<moduloDtoResponse>getListaModuloCif(Long cif);
	
	moduloUsuarioDtoResponse addModulo(moduloUsuarioDtoRequest moduloUsuarioDtoRequest);
	
	moduloUsuarioDtoResponse updateModuloUsuario(moduloUsuarioDtoResponse moduloUsuarioDtoResponse);
	
	List<moduloDtoResponse>getModuloCif(Long cif);
	
	List<moduloResponse>getModuloCifEmpleado(Long cif);
	
	moduloObj getModuloMenu(Long cif,Long idModulo);

}
