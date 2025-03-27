package com.fhce.egovf.dto;

import lombok.Data;

@Data
public class passwordDtoRequest {
	private Long id;
	private Long cif;
	private String pass1;
	private String pass2;
	private String pass3;

}
