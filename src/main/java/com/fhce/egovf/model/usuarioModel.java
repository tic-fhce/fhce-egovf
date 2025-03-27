package com.fhce.egovf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name="usuario")
public class usuarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true,nullable = false)
	private Long id;

	@Column (name = "_01cif")
	private Long cif;
	
	@Column (name = "_02matricula")
	private Long matricula;
	
	@Column (name = "_03ci")
	private int ci;
	
	@Column (name = "_04complemento")
	private String complemento;
	
	@Column (name = "_05correo")
	private String correo;
	
	@Column (name = "_06celular")
	private String celular;
	
	@Column (name = "_07pass")
	private String pass;
	
	@Column (name = "_08unidad")
	private String unidad;
	
	@Column (name = "_09dependiente")
	private String dependiente;
	
	@Column (name = "_10sigla")
	private String sigla;
	
	@Column (name = "_11foto")
	private String foto;
	
	@Column (name = "_12empleado")
	private int empleado;

	public usuarioModel(Long cif, Long matricula, int ci, String complemento, String correo, String celular,
			String pass, String unidad, String dependiente, String sigla, String foto, int empleado) {
		this.cif = cif;
		this.matricula = matricula;
		this.ci = ci;
		this.complemento = complemento;
		this.correo = correo;
		this.celular = celular;
		this.pass = pass;
		this.unidad = unidad;
		this.dependiente = dependiente;
		this.sigla = sigla;
		this.foto = foto;
		this.empleado = empleado;
	}
	

	
}
