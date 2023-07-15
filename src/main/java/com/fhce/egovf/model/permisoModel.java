package com.fhce.egovf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="permiso")
public class permisoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private Long _01cif;
	
	@Column
	private Long _02id_menu;
	
	@Column
	private String _03fecha;
	
	@Column
	private int _04estado;
	
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
	public Long get_02id_menu() {
		return _02id_menu;
	}
	public void set_02id_menu(Long _02id_menu) {
		this._02id_menu = _02id_menu;
	}
	public String get_03fecha() {
		return _03fecha;
	}
	public void set_03fecha(String _03fecha) {
		this._03fecha = _03fecha;
	}
	public int get_04estado() {
		return _04estado;
	}
	public void set_04estado(int _04estado) {
		this._04estado = _04estado;
	}
}
