package com.example.demo.prodec.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.prodec.dao.SocialMediaDao;
import com.example.demo.prodec.model.MaestroSocialMedia;
import com.example.demo.prodec.model.SocialMedia;

@Service("socialMediaService")
@Transactional
public class SocialMediaServiceImpl implements SocialMediaService {

	@Autowired
	private SocialMediaDao _socialMedia;

	@Override
	public void saveSocialMedia(SocialMedia sm) {
		// TODO Auto-generated method stub
		_socialMedia.saveSocialMedia(sm);
	}

	@Override
	public List<SocialMedia> findAllSocialMedia() {
		// TODO Auto-generated method stub
		return _socialMedia.findAllSocialMedia();
	}

	@Override
	public void deletSocialMedia(Long id_socialMedia) {
		// TODO Auto-generated method stub
		_socialMedia.deletSocialMedia(id_socialMedia);
	}

	@Override
	public void updateSocialMedia(SocialMedia m) {
		// TODO Auto-generated method stub
		_socialMedia.updateSocialMedia(m);
	}

	@Override
	public SocialMedia findSocialMediaById(Long id_socialMedia) {
		// TODO Auto-generated method stub
		return _socialMedia.findSocialMediaById(id_socialMedia);
	}

	@Override
	public SocialMedia findSocialMediaByName(String name) {
		// TODO Auto-generated method stub
		return _socialMedia.findSocialMediaByName(name);
	}

	@Override
	public MaestroSocialMedia findSocialMediaByIdAndName(Long idSocialMedia, String nickname) {
		// TODO Auto-generated method stub
		return _socialMedia.findSocialMediaByIdAndName(idSocialMedia, nickname);
	}

}
