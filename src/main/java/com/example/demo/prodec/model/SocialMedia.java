package com.example.demo.prodec.model;

import java.io.Serializable;
import java.util.Set;

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
@Table(name = "social_media")
public class SocialMedia implements Serializable {
	@Id
	@Column(name = "id_social_media")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idSocialMedia;

	@Column(name = "name")
	private String name;

	@Column(name = "icono")
	private String icono;

	@OneToMany
	@JoinColumn(name = "id_social_media")
	@JsonIgnore
	private Set<MaestroSocialMedia> maestroSocialMedias;

	public SocialMedia(String name, String icono) {
		super();
		this.name = name;
		this.icono = icono;
	}

	public SocialMedia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdSocialMedia() {
		return idSocialMedia;
	}

	public void setIdSocialMedia(long idSocialMedia) {
		this.idSocialMedia = idSocialMedia;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public Set<MaestroSocialMedia> getMaestroSocialMedias() {
		return maestroSocialMedias;
	}

	public void setMaestroSocialMedias(Set<MaestroSocialMedia> maestroSocialMedias) {
		this.maestroSocialMedias = maestroSocialMedias;
	}

}
