package com.example.demo.prodec.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.prodec.model.Curso;
import com.example.demo.prodec.service.CursoService;

import Util.MessageType;

@Controller
@RequestMapping("/v0.1")
public class CursoController {

	@Autowired
	CursoService _cursoService;

	// Post
	@RequestMapping(value = "/cursos", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> postCursos(@RequestBody Curso sm, UriComponentsBuilder url) {
		if (sm.getName() == null || sm.getName().isEmpty()) {
			return new ResponseEntity(new MessageType("Datos insuficientes"), HttpStatus.NO_CONTENT);
		}

		if (_cursoService.getCursoName(sm.getName()) != null) {
			return new ResponseEntity(new MessageType("Ya existe un Curso con ese Nombre:" + sm.getName()),
					HttpStatus.NO_CONTENT);
		}

		_cursoService.saveCurso(sm);
		Curso m = _cursoService.getCursoName(sm.getName());
		HttpHeaders h = new HttpHeaders();
		h.setLocation(url.path("/v0.1/maestros/{id}").buildAndExpand(m.getIdCurso()).toUri());

		return new ResponseEntity<String>(h, HttpStatus.CREATED);
	}

	// Get
	@RequestMapping(value = "/cursos", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Curso>> getCursos(@RequestParam(value = "name", required = false) String name) {
		List<Curso> l = new ArrayList<>();
		if (name != null) {
			Curso sm = _cursoService.getCursoName(name);
			if (sm != null) {
				l.add(sm);
				return new ResponseEntity<List<Curso>>(l, HttpStatus.OK);
			}
			return new ResponseEntity(new MessageType("No existe una Curso con nombre " + name), HttpStatus.NOT_FOUND);
		}
		l = _cursoService.getAllCurso();
		if (l.isEmpty()) {
			return new ResponseEntity(new MessageType("No tienes Curso"), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Curso>>(l, HttpStatus.OK);

	}

	// Get
	@RequestMapping(value = "/cursos/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Curso> getMaestroId(@PathVariable("id") Long id_sm) {
		if (id_sm < 0) {
			return new ResponseEntity(new MessageType("Datos insuficientes"), HttpStatus.NO_CONTENT);
		}
		Curso l = _cursoService.getCursoID(id_sm);
		if (l == null) {
			return new ResponseEntity(new MessageType("No existe Curso con el ID:" + id_sm), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Curso>(l, HttpStatus.OK);
	}

	// Update
	@RequestMapping(value = "/cursos/{id}", method = RequestMethod.PATCH, headers = "Accept=application/json")
	public ResponseEntity<Curso> updateMaestro(@PathVariable("id") Long id_sm, @RequestBody Curso sm) {
		if (id_sm <= 0) {
			return new ResponseEntity(new MessageType("Datos insuficientes"), HttpStatus.NO_CONTENT);
		}
		Curso l = _cursoService.getCursoID(id_sm);
		if (l == null) {
			return new ResponseEntity(new MessageType("No existe Curso que se quiere hacer PATCH"),
					HttpStatus.NO_CONTENT);
		}
		l.setName(sm.getName());
		l.setMaestro(sm.getMaestro());
		l.setProyecto(sm.getProyecto());
		l.setTemario(sm.getTemario());
		_cursoService.updateCurso(l);
		return new ResponseEntity<Curso>(l, HttpStatus.CREATED);
	}

	// Delete
	@RequestMapping(value = "/cursos/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deleteSocialMedias(@PathVariable("id") Long id_sm) {
		if (id_sm <= 0) {
			return new ResponseEntity(new MessageType("Datos insuficientes"), HttpStatus.NO_CONTENT);
		}
		Curso l = _cursoService.getCursoID(id_sm);
		if (l == null) {
			return new ResponseEntity(new MessageType("No existe Curso que se quiere borrar"), HttpStatus.NO_CONTENT);
		}
		_cursoService.deleteCurso(id_sm);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
