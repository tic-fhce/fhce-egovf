package com.fhce.egovf.service;

import java.util.List;

import com.fhce.egovf.dto.menuDtoResponse;
import com.fhce.egovf.dto.menuUsuarioDtoRequest;
import com.fhce.egovf.dto.menuUsuarioDtoResponse;
import com.fhce.egovf.dto.moduloDtoResponse;
import com.fhce.egovf.dto.permisoDtoRequest;
import com.fhce.egovf.dto.permisoDtoResponse;

public interface menuService {
	
	List<moduloDtoResponse> getMenu(Long cif);
	
	moduloDtoResponse getMenuModulo(Long cif, String titulo);
	
	menuUsuarioDtoResponse updateMenuUsuario(menuUsuarioDtoResponse menuUsuarioDtoResponse);
	
	List<menuDtoResponse> getListarMenu();
	List<menuDtoResponse> getListarPernicion(Long cif);
	
	permisoDtoResponse addPernicion(permisoDtoRequest permisoDtoRequest);
	menuUsuarioDtoResponse addMenuUsuario (menuUsuarioDtoRequest menuUsuarioDtoRequest);

}
