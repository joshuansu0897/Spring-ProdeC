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

@Entity
@Table(name = "maestro_social_media")
public class MaestroSocialMedia implements Serializable {

	@Id
	@Column(name = "id_maestro_social_media")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMaestroSocialMedia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_maestro")
	private Maestro maestro;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_social_media")
	private SocialMedia socialMedia;

	@Column(name = "nickname")
	private String nickname;

	public MaestroSocialMedia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MaestroSocialMedia(Maestro maestro, SocialMedia socialMedia, String nickname) {
		super();
		this.maestro = maestro;
		this.socialMedia = socialMedia;
		this.nickname = nickname;
	}

	public long getIdMaestroSocialMedia() {
		return idMaestroSocialMedia;
	}

	public void setIdMaestroSocialMedia(long idMaestroSocialMedia) {
		this.idMaestroSocialMedia = idMaestroSocialMedia;
	}

	public Maestro getMaestro() {
		return maestro;
	}

	public void setMaestro(Maestro maestro) {
		this.maestro = maestro;
	}

	public SocialMedia getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(SocialMedia socialMedia) {
		this.socialMedia = socialMedia;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
