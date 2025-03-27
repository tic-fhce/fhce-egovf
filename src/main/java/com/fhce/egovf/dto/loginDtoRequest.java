package com.fhce.egovf.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class loginDtoRequest {
	private int id;
	private Long cif;
	private Long matricula;
	private Long ci;
	private String complemento;
	private String correo;
	private String celular;
	private String pass;

}