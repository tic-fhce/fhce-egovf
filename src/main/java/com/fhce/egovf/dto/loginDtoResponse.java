package com.fhce.egovf.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class loginDtoResponse {
	private String cif;
	private String correo;
	private String celular;
	private String pass;
	private String unidad;
	private String sigla;
	private String foto;
	private String token;
	private String refresh;
	private String username;
}