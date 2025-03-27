package com.fhce.egovf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="persona")

public class personaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column (name = "_01cif")
	private Long cif;
	
	@Column (name = "_02ci")
	private int ci;
	
	@Column (name = "_03complemento")
	private String complemento;
	
	@Column (name = "_04nombre")
	private String nombre;
	
	@Column (name = "_05paterno")
	private String paterno;
	
	@Column (name = "_06materno")
	private String materno;
	
	@Column (name = "_07fecha")
	private String fecha;
	
	@Column (name = "_08sexo")
	private int sexo;
	
	@Column (name = "_09cel")
	private String cel;
	
	@Column (name = "_10correo")
	private String correo;

}
