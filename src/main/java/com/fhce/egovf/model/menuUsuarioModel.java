package com.fhce.egovf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menuusuario")
public class menuUsuarioModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private Long _01cif;
	
	@Column
	private Long _02idmenu;
	
	@Column
	private int _03estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long get_01cif() {
		return _01cif;
	}

	public void set_01cif(Long _01cif) {
		this._01cif = _01cif;
	}

	public int get_03estado() {
		return _03estado;
	}

	public void set_03estado(int _03estado) {
		this._03estado = _03estado;
	}

	public Long get_02idmenu() {
		return _02idmenu;
	}

	public void set_02idmenu(Long _02idmenu) {
		this._02idmenu = _02idmenu;
	}
	
	
}
