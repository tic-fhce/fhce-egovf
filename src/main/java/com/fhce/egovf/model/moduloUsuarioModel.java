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
@Table(name="modulousuario")

public class moduloUsuarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;

	@Column (name = "_01cif")
	private Long cif;
	
	@Column (name = "_02id_modulo")
	private Long id_modulo;
	
	@Column (name = "_03estado")
	private int estado;
	
	@Column (name = "_04cif") // quien registro el modulo
	private Long quien;
	
	@Column (name = "_05fecha") // cuando se registro el modulo
	private String fecha;
	
	@Column (name = "_06fecha") // cuando se modifico el modulo
	private String fechamodificacion;

}

