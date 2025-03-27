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
@Table(name="permiso")
public class permisoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;

	@Column (name = "_01cif")
	private Long cif;
	
	@Column (name = "_02id_menu")
	private Long id_menu;
	
	@Column (name = "_03fecha")
	private String fecha;
	
	@Column (name = "_04estado")
	private int estado;

}
