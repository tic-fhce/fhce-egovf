package com.fhce.egovf.dto;

import lombok.Data;

@Data
public class usuarioDtoResponse {
	private Long id;
	private Long cif;
	private Long matricula;
	private int ci;
	private String complemento;
	private String correo;
	private String celular;
	private String pass;
	private String unidad;
	private String dependiente;
	private String sigla;
	private String foto;
	private int empleado;

}
