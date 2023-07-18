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
	private int _03nivel;
	
	@Column
	private int _04subnivel;

	@Column
	private String _05obs;
	
	@Column
	private String _06imagen;
	
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
	public int get_03nivel() {
		return _03nivel;
	}
	public void set_03nivel(int _03nivel) {
		this._03nivel = _03nivel;
	}
	public int get_04subnivel() {
		return _04subnivel;
	}
	public void set_04subnivel(int _04subnivel) {
		this._04subnivel = _04subnivel;
	}
	public String get_05obs() {
		return _05obs;
	}
	public void set_05obs(String _05obs) {
		this._05obs = _05obs;
	}
	public String get_06imagen() {
		return _06imagen;
	}
	public void set_06imagen(String _06imagen) {
		this._06imagen = _06imagen;
	}
	
}
