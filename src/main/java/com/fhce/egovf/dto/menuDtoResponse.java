package com.fhce.egovf.dto;

import lombok.Data;

@Data
public class menuDtoResponse {
	private Long id; 
	private String titulo;
	private String ruta;
	private Long id_modulomenu;

}
