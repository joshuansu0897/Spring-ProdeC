package com.example.demo.prodec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping("/")
	@ResponseBody
	public String index() {
		String r = "Hola, creo que voy entendiendo <a href='https://www.taringa.net'>link</a>";
		return r;
	}

}
