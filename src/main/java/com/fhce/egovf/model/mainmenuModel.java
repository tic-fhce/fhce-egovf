package com.fhce.egovf.model;

import java.util.ArrayList;
import java.util.List;

public class mainmenuModel {
	
	private Long id;
	private String titulo;
	private String obs;
	private int index;
	private List<subModel> subModel;
		
	public mainmenuModel(Long id, String titulo, String obs, int index) {
		this.id=id;
		this.titulo = titulo;
		this.obs = obs;
		this.index = index;
		this.subModel = new ArrayList<subModel>();
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public List<subModel> getSubModel() {
		return subModel;
	}
	public void setSubModel(List<subModel> subModel) {
		this.subModel = subModel;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void addSubModel(subModel subModel) {
		this.subModel.add(subModel);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
