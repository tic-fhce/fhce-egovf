package com.fhce.egovf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class usuarioModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;
	
	@Column
	private Long _01cif;
	
	@Column
	private Long _02matricula;
	
	@Column
	private int _03ci;
	@Column
	private String _04complemento;
	
	@Column
	private String _05correo;
	
	@Column
	private String _06celular;
	
	@Column
	private String _07pass;
	
	@Column
	private String _08unidad;
	
	@Column
	private String _09dependiente;
	
	@Column
	private String _10sigla;
	
	@Column
	private String _11foto;
	
	@Column
	private int _12empleado;
	
	

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

	public Long get_02matricula() {
		return _02matricula;
	}

	public void set_02matricula(Long _02matricula) {
		this._02matricula = _02matricula;
	}

	public int get_03ci() {
		return _03ci;
	}

	public void set_03ci(int _03ci) {
		this._03ci = _03ci;
	}

	public String get_04complemento() {
		return _04complemento;
	}

	public void set_04complemento(String _04complemento) {
		this._04complemento = _04complemento;
	}

	public String get_05correo() {
		return _05correo;
	}

	public void set_05correo(String _05correo) {
		this._05correo = _05correo;
	}

	public String get_06celular() {
		return _06celular;
	}

	public void set_06celular(String _06celular) {
		this._06celular = _06celular;
	}

	public String get_07pass() {
		return _07pass;
	}

	public void set_07pass(String _07pass) {
		this._07pass = _07pass;
	}

	public String get_08unidad() {
		return _08unidad;
	}

	public void set_08unidad(String _08unidad) {
		this._08unidad = _08unidad;
	}

	public String get_09dependiente() {
		return _09dependiente;
	}

	public void set_09dependiente(String _09dependiente) {
		this._09dependiente = _09dependiente;
	}

	public String get_10sigla() {
		return _10sigla;
	}

	public void set_10sigla(String _10sigla) {
		this._10sigla = _10sigla;
	}

	public String get_11foto() {
		return _11foto;
	}

	public void set_11foto(String _11foto) {
		this._11foto = _11foto;
	}

	public int get_12empleado() {
		return _12empleado;
	}

	public void set_12empleado(int _12empleado) {
		this._12empleado = _12empleado;
	}
	
}
