package com.fhce.egovf.obj;

import java.util.List;

public class moduloObj {
	
	private Long id;
	private String titulo;
	private String icono;
	private List<menuObj>menuObj;
	private int importancia;
	
	public moduloObj(Long id,String titulo,String icono, List<com.fhce.egovf.obj.menuObj> menuObj,int importancia) {
		this.id = id;
		this.titulo = titulo;
		this.icono = icono;
		this.menuObj = menuObj;
		this.importancia = importancia;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public List<menuObj> getMenuObj() {
		return menuObj;
	}
	public void setMenuObj(List<menuObj> menuObj) {
		this.menuObj = menuObj;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getImportancia() {
		return importancia;
	}
	public void setImportancia(int importancia) {
		this.importancia = importancia;
	}
	
}
