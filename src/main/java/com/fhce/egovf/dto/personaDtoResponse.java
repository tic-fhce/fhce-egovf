package com.fhce.egovf.dto;

import lombok.Data;

@Data
public class personaDtoResponse {
	private Long id;
	private Long cif;
	private int ci;
	private String complemento;
	private String nombre;
	private String paterno;
	private String materno;
	private String fecha;
	private int sexo;
	private String cel;
	private String correo;

}
