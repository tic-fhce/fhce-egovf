package com.fhce.egovf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="modulousuario")
public class moduloUsuarioModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private Long _01cif;
	
	@Column
	private Long _02id_modulo;

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
	public Long get_02id_modulo() {
		return _02id_modulo;
	}
	public void set_02id_modulo(Long _02id_modulo) {
		this._02id_modulo = _02id_modulo;
	}
}