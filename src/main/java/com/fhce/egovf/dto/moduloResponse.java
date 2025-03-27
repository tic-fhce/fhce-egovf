package com.fhce.egovf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class moduloResponse {
	private Long id;
	private String nombre;
	private String ruta;
	private String imagen;
	private int tipo;
	private String descripcion;
}
