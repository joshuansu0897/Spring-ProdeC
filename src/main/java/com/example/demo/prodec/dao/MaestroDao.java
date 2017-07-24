package com.example.demo.prodec.dao;

import java.util.List;
import com.example.demo.prodec.model.Maestro;

public interface MaestroDao {

	void saveMaestro(Maestro m);

	List<Maestro> findAllMaestro();

	void deletMaestro(Long id_maestro);

	void updateMaestro(Maestro m);

	Maestro findMaestroById(Long id_maestro);

	Maestro findMaestroByName(String name);

}
