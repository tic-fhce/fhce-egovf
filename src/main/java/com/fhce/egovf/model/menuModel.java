package com.fhce.egovf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class menuModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private String _01titulo;
	
	@Column
	private String _02ruta;
	
	@Column
	private Long _03id_modulomenu;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String get_01titulo() {
		return _01titulo;
	}

	public void set_01titulo(String _01titulo) {
		this._01titulo = _01titulo;
	}

	public String get_02ruta() {
		return _02ruta;
	}

	public void set_02ruta(String _02ruta) {
		this._02ruta = _02ruta;
	}

	public Long get_03id_modulomenu() {
		return _03id_modulomenu;
	}

	public void set_03id_modulomenu(Long _03id_modulomenu) {
		this._03id_modulomenu = _03id_modulomenu;
	}
	
	
	
}
