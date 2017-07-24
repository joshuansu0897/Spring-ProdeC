package com.example.demo.prodec.dao;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.prodec.model.Maestro;
import com.example.demo.prodec.model.MaestroSocialMedia;

@Repository
@Transactional
public class MaestroDaoImpl extends AbstractSession implements MaestroDao {

	public MaestroDaoImpl() {
		super();
	}

	public void saveMaestro(Maestro m) {
		getSession().save(m);
	}

	public List<Maestro> findAllMaestro() {
		return getSession().createQuery("from Maestro").list();
	}

	public void updateMaestro(Maestro m) {
		getSession().update(m);
	}

	public Maestro findMaestroById(Long id_maestro) {
		return getSession().get(Maestro.class, id_maestro);
	}

	public Maestro findMaestroByName(String name) {
		return (Maestro) getSession().createQuery("from Maestro where name = :name").setParameter("name", name)
				.uniqueResult();
	}

	public void deletMaestro(Long id_maestro) {
		Maestro m = findMaestroById(id_maestro);
		if (m != null) {
			Iterator<MaestroSocialMedia> i = m.getMaestroSocialMedias().iterator();

			while (i.hasNext()) {
				MaestroSocialMedia msm = i.next();
				i.remove();
				getSession().delete(msm);
			}
			m.getMaestroSocialMedias().clear();
			getSession().delete(m);
		}

	}

}
