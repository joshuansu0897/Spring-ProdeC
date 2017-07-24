package com.example.demo.prodec.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.prodec.dao.MaestroDao;
import com.example.demo.prodec.model.Maestro;

@Service("maestroService")
@Transactional
public class MaestroServiceImpl implements MaestroService {

	@Autowired
	private MaestroDao _maestro;

	@Override
	public void saveMaestro(Maestro m) {
		// TODO Auto-generated method stub
		_maestro.saveMaestro(m);
	}

	@Override
	public List<Maestro> findAllMaestro() {
		// TODO Auto-generated method stub
		return _maestro.findAllMaestro();
	}

	@Override
	public void deletMaestro(Long id_maestro) {
		// TODO Auto-generated method stub
		_maestro.deletMaestro(id_maestro);
	}

	@Override
	public void updateMaestro(Maestro m) {
		// TODO Auto-generated method stub
		_maestro.updateMaestro(m);
	}

	@Override
	public Maestro findMaestroById(Long id_maestro) {
		// TODO Auto-generated method stub
		return _maestro.findMaestroById(id_maestro);
	}

	@Override
	public Maestro findMaestroByName(String name) {
		// TODO Auto-generated method stub
		return _maestro.findMaestroByName(name);
	}

}
