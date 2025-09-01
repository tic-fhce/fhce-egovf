package com.fhce.egovf.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class menuObj {
	
	private Long idMenu;
	private String titulo;
	private String ruta;
	private Long idModulo;
	private String descripcion;

	
	private Long idMenuUsuario;
	private int estado;
	
}
