package com.example.demo.prodec.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.prodec.model.Maestro;
import com.example.demo.prodec.model.MaestroSocialMedia;
import com.example.demo.prodec.service.MaestroService;

import Util.MessageType;

@Controller
@RequestMapping("/v0.1")
public class MaestroController {

	@Autowired
	MaestroService _maestroService;

	// Post
	@RequestMapping(value = "/maestros", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> postMaestro(@RequestBody Maestro sm, UriComponentsBuilder url) {
		if (sm.getName() == null || sm.getName().isEmpty()) {
			return new ResponseEntity(new MessageType("Datos insuficientes"), HttpStatus.NO_CONTENT);
		}

		if (_maestroService.findMaestroByName(sm.getName()) != null) {
			return new ResponseEntity(new MessageType("Ya existe un Maestro con ese Nombre:" + sm.getName()),
					HttpStatus.NO_CONTENT);
		}

		_maestroService.saveMaestro(sm);
		Maestro m = _maestroService.findMaestroByName(sm.getName());
		HttpHeaders h = new HttpHeaders();
		h.setLocation(url.path("/v0.1/maestros/{id}").buildAndExpand(m.getIdMaestro()).toUri());

		return new ResponseEntity<String>(h, HttpStatus.CREATED);
	}

	// Get
	@RequestMapping(value = "/maestros", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Maestro>> getMaestros(@RequestParam(value = "name", required = false) String name) {
		List<Maestro> l = new ArrayList<>();
		if (name != null) {
			Maestro sm = _maestroService.findMaestroByName(name);
			if (sm != null) {
				l.add(sm);
				return new ResponseEntity<List<Maestro>>(l, HttpStatus.OK);
			}
			return new ResponseEntity(new MessageType("No existe una Maestro con nombre " + name),
					HttpStatus.NOT_FOUND);
		}
		l = _maestroService.findAllMaestro();
		if (l.isEmpty()) {
			return new ResponseEntity(new MessageType("No tienes Maestros"), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Maestro>>(l, HttpStatus.OK);

	}

	// Get
	@RequestMapping(value = "/maestros/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Maestro> getMaestroId(@PathVariable("id") Long id_sm) {
		if (id_sm < 0) {
			return new ResponseEntity(new MessageType("Datos insuficientes"), HttpStatus.NO_CONTENT);
		}
		Maestro l = _maestroService.findMaestroById(id_sm);
		if (l == null) {
			return new ResponseEntity(new MessageType("No existe maestro con el ID:" + id_sm), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Maestro>(l, HttpStatus.OK);
	}

	// Update
	@RequestMapping(value = "/maestros/{id}", method = RequestMethod.PATCH, headers = "Accept=application/json")
	public ResponseEntity<Maestro> updateMaestro(@PathVariable("id") Long id_sm, @RequestBody Maestro sm) {
		if (id_sm <= 0) {
			return new ResponseEntity(new MessageType("Datos insuficientes"), HttpStatus.NO_CONTENT);
		}
		Maestro l = _maestroService.findMaestroById(id_sm);
		if (l == null) {
			return new ResponseEntity(new MessageType("No existe Maestro que se quiere hacer PATCH"),
					HttpStatus.NO_CONTENT);
		}
		l.setName(sm.getName());
		l.setAvatar(sm.getAvatar());
		l.setCursos(sm.getCursos());
		l.setMaestroSocialMedias(sm.getMaestroSocialMedias());
		_maestroService.updateMaestro(l);
		return new ResponseEntity<Maestro>(l, HttpStatus.CREATED);
	}

	// Delete
	@RequestMapping(value = "/maestros/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deleteSocialMedias(@PathVariable("id") Long id_sm) {
		if (id_sm <= 0) {
			return new ResponseEntity(new MessageType("Datos insuficientes"), HttpStatus.NO_CONTENT);
		}
		Maestro l = _maestroService.findMaestroById(id_sm);
		if (l == null) {
			return new ResponseEntity(new MessageType("No existe SocialMedia que se quiere borrar"),
					HttpStatus.NO_CONTENT);
		}
		_maestroService.deletMaestro(id_sm);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public static final String MAESTRO_UPLOADED_FOLDER = "images/maestros/";

	// img avatar
	@RequestMapping(value = "/maestros/avatar", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	public ResponseEntity<byte[]> uploadImage(@RequestParam("id_maestro") Long id_m,
			@RequestParam("file") MultipartFile mpf, UriComponentsBuilder uri) {

		if (id_m <= 0) {
			return new ResponseEntity(new MessageType("falta id Maestro"), HttpStatus.NO_CONTENT);
		}
		if (mpf == null || mpf.equals(null) || mpf.isEmpty()) {
			return new ResponseEntity(new MessageType("falta Foto"), HttpStatus.NO_CONTENT);
		}

		Maestro m = _maestroService.findMaestroById(id_m);
		if (m == null) {
			return new ResponseEntity(new MessageType("No se encotro el Maestro"), HttpStatus.NOT_FOUND);
		}
		if (m.getAvatar() != null || !m.getAvatar().equals(null) || !m.getAvatar().isEmpty()) {
			String file = m.getAvatar();
			Path p = Paths.get(file);
			File f = p.toFile();
			if (f.exists()) {
				f.delete();
			}
		}

		try {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String date = sdf.format(d);

			String file = String.valueOf(id_m) + "-fotoNuevaMaestro-" + date + "." + mpf.getContentType().split("/")[1];
			m.setAvatar(MAESTRO_UPLOADED_FOLDER + file);

			byte[] b = mpf.getBytes();
			Path p = Paths.get(MAESTRO_UPLOADED_FOLDER + file);
			Files.write(p, b);

			_maestroService.updateMaestro(m);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(b);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity(
					new MessageType("error al subir " + mpf.getOriginalFilename() + ": " + ex.getMessage()),
					HttpStatus.NOT_FOUND);

		}
	}

	// Get image
	@RequestMapping(value = "/maestros/{id_m}/avatar", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImageMaestro(@PathVariable("id_m") Long idM) {
		if (idM <= 0) {
			return new ResponseEntity(new MessageType("falta id Maestro"), HttpStatus.NO_CONTENT);
		}

		Maestro m = _maestroService.findMaestroById(idM);
		if (m == null) {
			return new ResponseEntity(new MessageType("No se encotro el Maestro"), HttpStatus.NOT_FOUND);
		}

		try {
			String file = m.getAvatar();
			Path p = Paths.get(file);
			File f = p.toFile();
			if (!f.exists()) {
				return new ResponseEntity(new MessageType("error no se encontro la imagen"), HttpStatus.NOT_FOUND);
			}
			byte[] b = Files.readAllBytes(p);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(b);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity(new MessageType("error al mostrar la imagen"), HttpStatus.NOT_FOUND);
		}
	}

	// Delet image
	@RequestMapping(value = "/maestros/{id_m}/avatar", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deleteImageMaestro(@PathVariable("id_m") Long idM) {
		if (idM <= 0) {
			return new ResponseEntity(new MessageType("falta id Maestro"), HttpStatus.NO_CONTENT);
		}

		Maestro m = _maestroService.findMaestroById(idM);
		if (m == null) {
			return new ResponseEntity(new MessageType("No se encotro el Maestro"), HttpStatus.NOT_FOUND);
		}

		if (m.getAvatar() == null || m.getAvatar().equals(null) || m.getAvatar().isEmpty()) {
			return new ResponseEntity(new MessageType("este Maestro no tiene foto"), HttpStatus.NO_CONTENT);
		}

		String file = m.getAvatar();
		Path p = Paths.get(file);
		File f = p.toFile();
		if (f.exists()) {
			f.delete();
		}

		m.setAvatar("");
		_maestroService.updateMaestro(m);
		return new ResponseEntity(new MessageType("se boro la foto del Maestro"), HttpStatus.NO_CONTENT);

	}

	// getR
	@RequestMapping(value = "/maestros/socialMedias", method = RequestMethod.PATCH, headers = "Accept=application/json")
	public ResponseEntity<?> assignMaestroSocialMedia(@RequestBody Maestro m, UriComponentsBuilder uri) {
		if (m == null) {
			return new ResponseEntity(new MessageType("Maestro null"), HttpStatus.NOT_FOUND);
		}
		if (m.getIdMaestro() <= 0) {
			return new ResponseEntity(new MessageType("se necesita idMaestro, idSocialMedia y nickname"),
					HttpStatus.NO_CONTENT);
		}
		Maestro ms = _maestroService.findMaestroById(m.getIdMaestro());
		if (ms == null) {
			return new ResponseEntity(new MessageType("No se encontro el maestro"), HttpStatus.NOT_FOUND);
		}

		if (ms.getMaestroSocialMedias() == null && ms.getMaestroSocialMedias().isEmpty()) {
			return new ResponseEntity(new MessageType("se necesita idMaestro, idSocialMedia y nickname"),
					HttpStatus.NO_CONTENT);
		}

		for (MaestroSocialMedia msm : ms.getMaestroSocialMedias()) {
			if (msm.getSocialMedia().getIdSocialMedia() <= 0 || msm.getNickname() == null) {
				return new ResponseEntity(new MessageType("se necesita idMaestro, idSocialMedia y nickname"),
						HttpStatus.NO_CONTENT);
			}
		}

	}

}
