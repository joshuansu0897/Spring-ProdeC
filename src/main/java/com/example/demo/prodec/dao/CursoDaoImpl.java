package com.example.demo.prodec.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.prodec.model.Curso;

@Repository
@Transactional
public class CursoDaoImpl extends AbstractSession implements CursoDao {

	@Override
	public void saveCurso(Curso c) {
		getSession().save(c);
	}

	@Override
	public void updateCurso(Curso c) {
		getSession().update(c);
	}

	@Override
	public void deleteCurso(Long id_curso) {
		Curso c = getCursoID(id_curso);
		if (c != null) {
			getSession().delete(c);
		}
	}

	@Override
	public List<Curso> getAllCurso() {
		return getSession().createQuery("from Curso").list();
	}

	@Override
	public Curso getCursoID(Long id_curso) {
		return getSession().get(Curso.class, id_curso);
	}

	@Override
	public Curso getCursoName(String name) {
		return (Curso) getSession().createQuery("from Curso where name = :name")
				.setParameter("name", name)
				.uniqueResult();
	}

	@Override
	public List<Curso> getCursoMaestroID(Long idMaestro) {
		return (List<Curso>) getSession()
				.createQuery("from Curso c join c.maestro t where t.idMaestro = :idMaestro")
				.setParameter("idMaestro", idMaestro).list();
	}

}
