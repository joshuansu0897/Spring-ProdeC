package com.example.demo.prodec.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "curso")
public class Curso implements Serializable {

	@Id
	@Column(name = "id_curso")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCurso;

	@Column(name = "name")
	private String name;

	@Column(name = "temario")
	private String temario;

	@Column(name = "proyecto")
	private String proyecto;

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_maestro")
	@JsonIgnore 
	private Maestro maestro;

	public Curso(String name, String temario, String proyecto) {
		super();
		this.name = name;
		this.temario = temario;
		this.proyecto = proyecto;
	}

	public Curso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(long idCurso) {
		this.idCurso = idCurso;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemario() {
		return temario;
	}

	public void setTemario(String temario) {
		this.temario = temario;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public Maestro getMaestro() {
		return maestro;
	}

	public void setMaestro(Maestro maestro) {
		this.maestro = maestro;
	}

}
