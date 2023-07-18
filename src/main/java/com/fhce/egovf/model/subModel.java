package com.fhce.egovf.model;

public class subModel {
	private Long id;
	private String titulo;
	private String ruta;
	private String obs;
	private String imagen;
	
	public subModel(Long id,String titulo, String ruta, String obs,String imagen) {
		this.id=id;
		this.titulo = titulo;
		this.ruta = ruta;
		this.obs = obs;
		this.imagen = imagen;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
}
