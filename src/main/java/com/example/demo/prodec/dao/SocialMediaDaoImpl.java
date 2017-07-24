package com.example.demo.prodec.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.prodec.model.MaestroSocialMedia;
import com.example.demo.prodec.model.SocialMedia;

@Repository
@Transactional
public class SocialMediaDaoImpl extends AbstractSession implements SocialMediaDao {

	@Override
	public void saveSocialMedia(SocialMedia sm) {
		getSession().save(sm);
	}

	@Override
	public List<SocialMedia> findAllSocialMedia() {
		return getSession().createQuery("from SocialMedia").list();
	}

	@Override
	public void deletSocialMedia(Long id_socialMedia) {
		SocialMedia sm = findSocialMediaById(id_socialMedia);
		if (sm != null) {
			getSession().delete(sm);
		}
	}

	@Override
	public void updateSocialMedia(SocialMedia m) {
		getSession().update(m);
	}

	@Override
	public SocialMedia findSocialMediaById(Long id_socialMedia) {
		// TODO Auto-generated method stub
		return getSession().get(SocialMedia.class, id_socialMedia);
	}

	@Override
	public SocialMedia findSocialMediaByName(String name) {
		return (SocialMedia) getSession().createQuery("from SocialMedia where name = :name").setParameter("name", name)
				.uniqueResult();
	}

	@Override
	public MaestroSocialMedia findSocialMediaByIdAndName(Long idSocialMedia, String nickname) {

		List<Object[]> obj = getSession()
				.createQuery("from MaestroSocialMedia msm join msm.socialMedia sm "
						+ "where sm.idSocialMedia = : idSM and msm.nickname = :nn")
				.setParameter("idSM", idSocialMedia).setParameter("nn", nickname).list();

		if (!obj.isEmpty()) {
			for (Object[] ob1 : obj) {
				for (Object o2 : ob1) {
					if (o2 instanceof MaestroSocialMedia) {
						return (MaestroSocialMedia) o2;
					}
				}
			}
		}
		return null;
	}

}
