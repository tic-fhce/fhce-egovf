package com.fhce.egovf.dto;

import lombok.Data;

@Data
public class personaDtoRequest {
	private Long cif;
	private int ci;
	private String complemento;
	private String nombre;
	private String paterno;
	private String materno;
	private String fecha;
	private int sexo;
	private String cel;
	private String correo;
	
	public String cif(String c) {
		String auxcif=this.sexo+""+this.fecha.replace("/","")+c;
		return(auxcif);
	}

}
