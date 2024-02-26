package com.fhce.egovf.model;

import java.util.List;

public class sesionModel {
	
	private String token;
	private String cif;
	private String correo;
	private String celular;
	private String pass;
	private String unidad;
	private String sigla;
	private String foto;
	
	private List<mainmenuModel> menu;
	
	public sesionModel() {
		
	}
	
	public String getToken() {
		return token;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<mainmenuModel> getMenu() {
		return menu;
	}

	public void setMenu(List<mainmenuModel> menu) {
		this.menu = menu;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
