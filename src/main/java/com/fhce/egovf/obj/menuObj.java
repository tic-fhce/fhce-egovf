package com.fhce.egovf.obj;

public class menuObj {
	private Long id;
	private String titulo;
	private String ruta;
	private Long idmenu;
	private int estado;
	
	public menuObj(Long id,String titulo, String ruta,Long idmenu, int estado) {
		this.id = id;
		this.titulo = titulo;
		this.ruta = ruta;
		this.idmenu = idmenu;
		this.estado = estado;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Long getIdmenu() {
		return idmenu;
	}

	public void setIdmenu(Long idmenu) {
		this.idmenu = idmenu;
	}
	
	
}
