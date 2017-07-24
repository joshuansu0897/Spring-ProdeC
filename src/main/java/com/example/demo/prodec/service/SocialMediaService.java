package com.example.demo.prodec.service;

import java.util.List;

import com.example.demo.prodec.model.MaestroSocialMedia;
import com.example.demo.prodec.model.SocialMedia;

public interface SocialMediaService {

	void saveSocialMedia(SocialMedia sm);

	List<SocialMedia> findAllSocialMedia();

	void deletSocialMedia(Long id_socialMedia);

	void updateSocialMedia(SocialMedia m);

	SocialMedia findSocialMediaById(Long id_socialMedia);

	SocialMedia findSocialMediaByName(String name);

	MaestroSocialMedia findSocialMediaByIdAndName(Long idSocialMedia, String nickname);
}
