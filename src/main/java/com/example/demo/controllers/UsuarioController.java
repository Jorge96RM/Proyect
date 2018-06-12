package com.example.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Rol;
import com.example.demo.domain.Usuario;
import com.example.demo.repositories.RolRepository;
import com.example.demo.repositories.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository RepoUsuario;
	@Autowired
	private RolRepository RepoRol;
	
	
	@GetMapping("/registrarse")
	public String registrarse(ModelMap m){
		m.put("view","usuario/crear");
		return("views/_t/main");
	}
	
	@RequestMapping(value = "/usuario/crear", method = RequestMethod.GET)
	public String crear(ModelMap m){ 
		m.put("listaRoles", RepoRol.findAll());
		m.put("view","/usuario/crear");
		return "views/_t/main";
	}
	
	@RequestMapping(value = "/usuario/crear", method = RequestMethod.POST)
	public String crearPost(@RequestParam("alias") String alias,
			@RequestParam("contrasena") String contrasena,
			@RequestParam("nombre") String nombre,
			@RequestParam("apellidos") String apellidos,
			@RequestParam("sexo") String sexo,
			ModelMap m){
		Rol rolPorDefecto = (Rol) RepoRol.getDefaultRol();
		Usuario u = new Usuario(alias, contrasena, nombre, apellidos, sexo, rolPorDefecto);
		RepoUsuario.save(u);
		m.put("alias", alias);
		m.put("view","/usuario/crearPost");
		return "views/_t/main";
	}
}
