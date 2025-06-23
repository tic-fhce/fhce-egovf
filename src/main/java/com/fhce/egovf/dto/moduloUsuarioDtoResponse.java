package com.fhce.egovf.dto;

import lombok.Data;

@Data
public class moduloUsuarioDtoResponse {
	private Long id;
	private Long cif;
	private Long id_modulo;
	private int estado;
	private Long quien;
	private String fecha;
	private String fechamodificacion;

}
