package com.fhce.egovf.model;

import javax.persistence.Column;

public class ciudadanoModel {
	private Long id;
	private Long _01cif;
	private int _02ci;
	private String _03complemento;
	private String _04nombre;
	private String _05paterno;
	private String _06materno;
	private String _09cel;
	private String _10correo;
	private String _08unidad;
	private String _10sigla;
	
	public ciudadanoModel(Long id,Long _01cif, int _02ci, String _03complemento, String _04nombre, String _05paterno,
			String _06materno, String _09cel, String _10correo, String _08unidad, String _10sigla) {
		this.id=id;
		this._01cif = _01cif;
		this._02ci = _02ci;
		this._03complemento = _03complemento;
		this._04nombre = _04nombre;
		this._05paterno = _05paterno;
		this._06materno = _06materno;
		this._09cel = _09cel;
		this._10correo = _10correo;
		this._08unidad = _08unidad;
		this._10sigla = _10sigla;
	}
	
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
	public int get_02ci() {
		return _02ci;
	}
	public void set_02ci(int _02ci) {
		this._02ci = _02ci;
	}
	public String get_03complemento() {
		return _03complemento;
	}
	public void set_03complemento(String _03complemento) {
		this._03complemento = _03complemento;
	}
	public String get_04nombre() {
		return _04nombre;
	}
	public void set_04nombre(String _04nombre) {
		this._04nombre = _04nombre;
	}
	public String get_05paterno() {
		return _05paterno;
	}
	public void set_05paterno(String _05paterno) {
		this._05paterno = _05paterno;
	}
	public String get_06materno() {
		return _06materno;
	}
	public void set_06materno(String _06materno) {
		this._06materno = _06materno;
	}
	public String get_09cel() {
		return _09cel;
	}
	public void set_09cel(String _09cel) {
		this._09cel = _09cel;
	}
	public String get_10correo() {
		return _10correo;
	}
	public void set_10correo(String _10correo) {
		this._10correo = _10correo;
	}
	public String get_08unidad() {
		return _08unidad;
	}
	public void set_08unidad(String _08unidad) {
		this._08unidad = _08unidad;
	}
	public String get_10sigla() {
		return _10sigla;
	}
	public void set_10sigla(String _10sigla) {
		this._10sigla = _10sigla;
	}
	
	

}
