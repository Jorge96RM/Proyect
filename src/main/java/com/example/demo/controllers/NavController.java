package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repositories.UsuarioRepository;

@Controller
public class NavController {
	
	@Autowired
	private UsuarioRepository repoUsuario;
	
	@GetMapping("/nav/ranking")
	public String ranking(ModelMap m){
		m.put("usuarios", repoUsuario.todosUsuarios());
		m.put("view","nav/ranking");
		return("views/_t/main");
	}
	
	@GetMapping("/nav/portal")
	public String portal(ModelMap m){
		m.put("view","nav/portal");
		return("views/_t/main");
	}
	@GetMapping("/nav/normas")
	public String normas(ModelMap m){
		m.put("view","nav/normas");
		return("views/_t/main");
	}
	
	@GetMapping("/nav/ayuda")
	public String ayuda(ModelMap m){
		m.put("view","nav/ayuda");
		return("views/_t/main");
	}
	
	@GetMapping("/nav/queEs")
	public String quienesSomos(ModelMap m){
		m.put("view","nav/queEs");
		return("views/_t/main");
	}
}
