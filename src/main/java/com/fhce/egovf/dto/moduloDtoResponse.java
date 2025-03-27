package com.fhce.egovf.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class moduloDtoResponse {
	private Long id;
	private String titulo;
	private String icono;
	private List<menuDtoObj>menuDtoObj;
	private int importancia;
	private String descripcion;
}
