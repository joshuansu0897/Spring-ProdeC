package com.example.demo.prodec.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "maestro")
public class Maestro implements Serializable {

	@Id
	@Column(name = "id_maestro")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMaestro;

	@Column(name = "name")
	private String name;

	@Column(name = "avatar")
	private String avatar;

	@OneToMany(mappedBy = "maestro")
	@JsonIgnore 
	private Set<Curso> cursos;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_maestro")
	@JsonIgnore
	private Set<MaestroSocialMedia> maestroSocialMedias;

	public Maestro(String name, String avatar) {
		super();
		this.name = name;
		this.avatar = avatar;
	}

	public Maestro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdMaestro() {
		return idMaestro;
	}

	public void setIdMaestro(long idMaestro) {
		this.idMaestro = idMaestro;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Set<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}

	public Set<MaestroSocialMedia> getMaestroSocialMedias() {
		return maestroSocialMedias;
	}

	public void setMaestroSocialMedias(Set<MaestroSocialMedia> maestroSocialMedias) {
		this.maestroSocialMedias = maestroSocialMedias;
	}

}
