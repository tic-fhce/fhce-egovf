package com.fhce.egovf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="modulomenu")
public class modulomenuModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private String _01titulo;
	
	@Column
	private String _02icono;
	
	@Column
	private int _03importancia;

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

	public String get_02icono() {
		return _02icono;
	}

	public void set_02icono(String _02icono) {
		this._02icono = _02icono;
	}

	public int get_03importancia() {
		return _03importancia;
	}

	public void set_03importancia(int _03importancia) {
		this._03importancia = _03importancia;
	}
	

}
