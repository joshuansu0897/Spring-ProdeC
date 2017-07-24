package com.example.demo.prodec.dao;

import java.util.List;

import com.example.demo.prodec.model.Curso;

public interface CursoDao {

	void saveCurso(Curso c);

	void updateCurso(Curso c);

	void deleteCurso(Long id_curso);

	List<Curso> getAllCurso();

	Curso getCursoID(Long id_curso);

	Curso getCursoName(String name);

	List<Curso> getCursoMaestroID(Long idMaestro);
}
