package com.example.demo.prodec.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.prodec.dao.CursoDao;
import com.example.demo.prodec.model.Curso;

@Service("curseService")
@Transactional
public class CursoServiceImpl implements CursoService {

	@Autowired
	private CursoDao _cursoDao;

	@Override
	public void saveCurso(Curso c) {
		// TODO Auto-generated method stub
		_cursoDao.saveCurso(c);
	}

	@Override
	public void updateCurso(Curso c) {
		// TODO Auto-generated method stub
		_cursoDao.updateCurso(c);
	}

	@Override
	public void deleteCurso(Long id_curso) {
		// TODO Auto-generated method stub
		_cursoDao.deleteCurso(id_curso);
	}

	@Override
	public List<Curso> getAllCurso() {
		// TODO Auto-generated method stub
		return _cursoDao.getAllCurso();
	}

	@Override
	public Curso getCursoID(Long id_curso) {
		// TODO Auto-generated method stub
		return _cursoDao.getCursoID(id_curso);
	}

	@Override
	public Curso getCursoName(String name) {
		// TODO Auto-generated method stub
		return _cursoDao.getCursoName(name);
	}

	@Override
	public List<Curso> getCursoMaestroID(Long idMaestro) {
		// TODO Auto-generated method stub
		return _cursoDao.getCursoMaestroID(idMaestro);
	}

}
