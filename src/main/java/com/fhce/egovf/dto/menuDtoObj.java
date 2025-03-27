package com.fhce.egovf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class menuDtoObj {
	private Long id;
	private String titulo;
	private String ruta;
	private String icono;
	private Long idModulo;
	private String descripcion;

}
