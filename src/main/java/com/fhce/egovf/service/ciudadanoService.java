package com.fhce.egovf.service;

import java.util.List;

import com.fhce.egovf.dto.ciudadanoDtoResponse;

public interface ciudadanoService {
	List<ciudadanoDtoResponse> getListaCiudadano();
	List<ciudadanoDtoResponse> getListaCiudadanoPublico();
	ciudadanoDtoResponse getEgovf(Long cif);
	
	List<ciudadanoDtoResponse>getListaEmpleado();

}
