package com.fhce.egovf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="modulo")
public class moduloModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private String _01nombre;
	
	@Column
	private String _02ruta;
	
	@Column
	private String _03imagen;
	
	@Column
	private Long _04idmodulomenu;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String get_01nombre() {
		return _01nombre;
	}

	public void set_01nombre(String _01nombre) {
		this._01nombre = _01nombre;
	}

	public String get_02ruta() {
		return _02ruta;
	}

	public void set_02ruta(String _02ruta) {
		this._02ruta = _02ruta;
	}

	public String get_03imagen() {
		return _03imagen;
	}

	public void set_03imagen(String _03imagen) {
		this._03imagen = _03imagen;
	}

	public Long get_04idmodulomenu() {
		return _04idmodulomenu;
	}

	public void set_04idmodulomenu(Long _04idmodulomenu) {
		this._04idmodulomenu = _04idmodulomenu;
	}
	
}

