package com.fhce.egovf.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class responsableObj {
	
	private Long id;
	private Long cif;
	private String nombre;
	private String ci;
	private String celular;

}
