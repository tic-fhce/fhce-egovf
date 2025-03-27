package com.fhce.egovf.dto;

import lombok.Data;

@Data
public class permisoDtoRequest {
	private Long cif;
	private Long id_menu;
	private String fecha;
	private int estado;

}
