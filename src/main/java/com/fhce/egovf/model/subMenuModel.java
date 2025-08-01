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
@Table(name="submenu")
public class subMenuModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	 
	@Column (name="_01titulo")
	private String titulo;
	
	@Column (name="_02ruta")
	private String ruta;
	
	@Column (name="_03icono")
	private String icono;
	
	@Column (name="_04idmenu")
	private Long idMenu;
	

}