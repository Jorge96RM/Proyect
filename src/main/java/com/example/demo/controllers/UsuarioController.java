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
	private UsuarioRepository repoUsuario;
	@Autowired
	private RolRepository repoRol;
	
	
	@GetMapping("/usuario/registrarse")
	public String registrarse(ModelMap m){
		m.put("view","usuario/crear");
		return("views/_t/main");
	}
	
	@RequestMapping(value = "/usuario/crear", method = RequestMethod.GET)
	public String crear(ModelMap m){ 
		m.put("listaRoles", repoRol.findAll());
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
		Rol rolPorDefecto = (Rol) repoRol.getDefaultRol();
		Usuario u = new Usuario(alias, contrasena, nombre, apellidos, sexo, rolPorDefecto);
		repoUsuario.save(u);
		m.put("alias", alias);
		m.put("view","/usuario/crearPost");
		return "views/_t/main";
	}
	
	/*@GetMapping("/login")
	public String login(ModelMap m){
		m.put("view","login/login");
		return("views/_t/main");
	}*/
	
	/*@PostMapping("/login")
	public String login(@RequestParam("nombre") String nombre, 
			@RequestParam("pwd") String pwd, 
			ModelMap m, 
			HttpSession s){
		if(nombre.equals("admin") && pwd.equals("admin")){
			m.put("view", "login/loginOK");
			s.setAttribute("user", "admin");
		}else{
			s.setAttribute("user", "anonymous");
			m.put("view", "login/loginError");
		}
		return "views/_t/main";
	}*/
	
	@RequestMapping(value = "/usuario/login", method = RequestMethod.GET)
	public String login(ModelMap m){ 
		m.put("view","/usuario/login");
		return "views/_t/main";
	}
	
	@RequestMapping(value = "/usuario/login", method = RequestMethod.POST)
	public String loginOK(@RequestParam("alias") String alias,
			@RequestParam("contrasena") String contrasena,
			ModelMap m,
			HttpSession s){
		boolean usuarioOK = repoUsuario.usuarioOK(alias,contrasena);
		if (!usuarioOK) {
			m.put("view", "usuario/loginError");
		}
		else {
			s.setAttribute("user", alias);
			// inicializar sesión. Aadir nombre usuario o rl id a alguna variable de sesion
			m.put("view", "usuario/loginOK");
		}
		/*
		Usuario u = new Usuario(alias, contrasena, login);
		repoUsuario.save(u);
		//m.put("alias", alias);
		m.put("view","/usuario/loginOK");
		
		*/
		//return "";
		return "views/_t/main";
	}
	
	@GetMapping("/usuario/logout")
	public String logout(ModelMap m, HttpSession session){
		session.invalidate();
		//s.setAttribute("user", "anonymous");
		m.put("view","home/index");
		return "redirect:/";
	}
}
