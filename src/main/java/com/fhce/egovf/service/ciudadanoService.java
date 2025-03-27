package com.fhce.egovf.service;

import java.util.List;

import com.fhce.egovf.dto.ciudadanoDtoResponse;

public interface ciudadanoService {
	List<ciudadanoDtoResponse> getListaCiudadano();
	
	ciudadanoDtoResponse getEgovf(Long cif);
	
	List<ciudadanoDtoResponse>getListaEmpleado();

}
