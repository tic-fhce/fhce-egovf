package com.fhce.egovf.obj;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class moduloObj {
	
	private Long id;
	private String titulo;
	private String icono;
	private List<menuObj>menuObj;
	private int importancia;
	

	
}
