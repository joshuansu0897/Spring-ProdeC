package com.example.demo.prodec.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractSession {

	@Autowired
	private SessionFactory SF;

	protected Session getSession() {
		return SF.getCurrentSession();
	}

}
