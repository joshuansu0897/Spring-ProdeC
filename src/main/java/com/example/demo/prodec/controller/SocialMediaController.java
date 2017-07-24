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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.prodec.model.SocialMedia;
import com.example.demo.prodec.service.SocialMediaService;

import Util.MessageType;

@Controller
@RequestMapping("/v0.1")
public class SocialMediaController {

	@Autowired
	SocialMediaService _socialMediaService;

	// Get
	@RequestMapping(value = "/socialMedias", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<SocialMedia>> getSocialMedias(
			@RequestParam(value = "name", required = false) String name) {
		List<SocialMedia> l = new ArrayList<>();
		if (name != null) {
			SocialMedia sm = _socialMediaService.findSocialMediaByName(name);
			if (sm != null) {
				l.add(sm);
				return new ResponseEntity<List<SocialMedia>>(l, HttpStatus.OK);
			}
			return new ResponseEntity(new MessageType("No existe una SocialMedia con nombre " + name),
					HttpStatus.NOT_FOUND);
		}
		l = _socialMediaService.findAllSocialMedia();
		if (l.isEmpty()) {
			return new ResponseEntity(new MessageType("No tienes SocialMEdia"), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SocialMedia>>(l, HttpStatus.OK);

	}

	// Get
	@RequestMapping(value = "/socialMedias/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<SocialMedia> getSocialMediaId(@PathVariable("id") Long id_sm) {
		if (id_sm < 0) {
			return new ResponseEntity(new MessageType("Datos insuficientes"), HttpStatus.NO_CONTENT);
		}
		SocialMedia l = _socialMediaService.findSocialMediaById(id_sm);
		if (l == null) {
			return new ResponseEntity(new MessageType("No existe SocialMedia con el ID:" + id_sm),
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<SocialMedia>(l, HttpStatus.OK);
	}

	// Post
	@RequestMapping(value = "/socialMedias", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> postSocialMedias(@RequestBody SocialMedia sm, UriComponentsBuilder url) {
		if (sm.getName() == null || sm.getName().isEmpty()) {
			return new ResponseEntity(new MessageType("Datos insuficientes"), HttpStatus.NO_CONTENT);
		}

		if (_socialMediaService.findSocialMediaByName(sm.getName()) != null) {
			return new ResponseEntity(new MessageType("Ya existe un SocialMedia con ese Nombre:" + sm.getName()),
					HttpStatus.NO_CONTENT);
		}

		_socialMediaService.saveSocialMedia(sm);
		SocialMedia smMostrar = _socialMediaService.findSocialMediaByName(sm.getName());
		HttpHeaders h = new HttpHeaders();
		h.setLocation(url.path("/v0.1/socialMedias/{id}").buildAndExpand(smMostrar.getIdSocialMedia()).toUri());

		return new ResponseEntity<String>(h, HttpStatus.CREATED);
	}

	// Update
	@RequestMapping(value = "/socialMedias/{id}", method = RequestMethod.PATCH, headers = "Accept=application/json")
	public ResponseEntity<SocialMedia> updateSocialMedias(@PathVariable("id") Long id_sm, @RequestBody SocialMedia sm) {
		if (id_sm <= 0) {
			return new ResponseEntity(new MessageType("Datos insuficientes"), HttpStatus.NO_CONTENT);
		}
		SocialMedia l = _socialMediaService.findSocialMediaById(id_sm);
		if (l == null) {
			return new ResponseEntity(new MessageType("No existe SocialMedia que se quiere hacer PATCH"),
					HttpStatus.NO_CONTENT);
		}
		l.setName(sm.getName());
		l.setIcono(sm.getIcono());
		l.setIdSocialMedia(sm.getIdSocialMedia());
		l.setMaestroSocialMedias(sm.getMaestroSocialMedias());
		_socialMediaService.updateSocialMedia(l);
		return new ResponseEntity<SocialMedia>(l, HttpStatus.CREATED);
	}

	// Delete
	@RequestMapping(value = "/socialMedias/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deleteSocialMedias(@PathVariable("id") Long id_sm) {
		if (id_sm <= 0) {
			return new ResponseEntity(new MessageType("Datos insuficientes"), HttpStatus.NO_CONTENT);
		}
		SocialMedia l = _socialMediaService.findSocialMediaById(id_sm);
		if (l == null) {
			return new ResponseEntity(new MessageType("No existe SocialMedia que se quiere borrar"),
					HttpStatus.NO_CONTENT);
		}
		_socialMediaService.deletSocialMedia(id_sm);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
