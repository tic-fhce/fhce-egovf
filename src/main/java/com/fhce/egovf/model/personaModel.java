package com.fhce.egovf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="persona")

public class personaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private Long _01cif;
	
	@Column
	private int _02ci;
	@Column
	private String _03complemento;
	@Column
	private String _04nombre;
	@Column
	private String _05paterno;
	@Column
	private String _06materno;
	@Column
	private String _07fecha;
	@Column
	private int _08sexo;
	@Column
	private String _09cel;
	
	@Column
	private String _10correo;
	
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
	public String get_07fecha() {
		return _07fecha;
	}
	public void set_07fecha(String _07fecha) {
		this._07fecha = _07fecha;
	}
	public int get_08sexo() {
		return _08sexo;
	}
	public void set_08sexo(int _08sexo) {
		this._08sexo = _08sexo;
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
	public String cif(String c) {
		String auxcif=this._08sexo+""+this._07fecha.replace("/","")+c;
		return(auxcif);
	}
}
