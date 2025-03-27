package com.fhce.egovf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ciudadanoDtoResponse {
	private Long idPersona;
	private String nombre;
	private String paterno;
	private String materno;
	private String fecha;
	private int sexo;
	
	private Long idUsuario;
	private Long cif;
	private Long matricula;
	private String ci;
	private int ci_com;
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
