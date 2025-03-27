package com.fhce.egovf.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fhce.egovf.dto.passwordDtoRequest;
import com.fhce.egovf.dto.usuarioDtoResponse;
import com.fhce.egovf.model.usuarioModel;

public interface usuarioService {
	UserDetailsService userDetailsService();
	List<usuarioDtoResponse> getListaUsuario();
	usuarioDtoResponse getUsuario(Long cif);
	usuarioDtoResponse updateUsuario(usuarioDtoResponse usuarioDtoResponse);
	boolean updatePass(passwordDtoRequest passwordDtoRequest)throws Exception;
	usuarioDtoResponse updatePassAdmin(passwordDtoRequest passwordDtoRequest )throws Exception;

}
