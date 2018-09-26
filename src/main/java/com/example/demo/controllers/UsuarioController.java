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
	
	
	/*@GetMapping("/usuario/registrarse")
	public String registrarse(ModelMap m){
		m.put("view","usuario/crear");
		return("views/_t/main");
	}*/
	
	@RequestMapping(value = "/home/", method = RequestMethod.GET)
	public String registro(ModelMap m){ 
		m.put("listaRoles", repoRol.findAll());
		m.put("view","/");
		return "views/_t/main";
	}
	
	@RequestMapping(value = "/home/", method = RequestMethod.POST)
	public String registroPost(@RequestParam("alias") String alias,
			@RequestParam("contrasena") String contrasena,
			@RequestParam("confirmarContrasena") String confirmarContrasena,
			@RequestParam("nombre") String nombre,
			@RequestParam("primerApellido") String primerApellido,
			@RequestParam("segundoApellido") String segundoApellido,
			@RequestParam("telefono") String telefono,
			@RequestParam("email") String email,
			@RequestParam("sexo") String sexo,
			ModelMap m){
		Rol rolPorDefecto = (Rol) repoRol.getDefaultRol();
		Usuario u = new Usuario(alias, contrasena, confirmarContrasena, nombre, primerApellido, segundoApellido, telefono, email, sexo, rolPorDefecto);
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
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(ModelMap m){ 
		m.put("view","/");
		return "views/_t/main";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String homeOK(@RequestParam("alias") String alias,
			@RequestParam("contrasena") String contrasena,
			ModelMap m,
			HttpSession s){
		boolean usuarioOK = repoUsuario.usuarioOK(alias,contrasena);
		if (!usuarioOK) {
			m.put("view", "usuario/loginError");
		}
		else {
			s.setAttribute("user", alias);
			// inicializar sesión. Añadir nombre usuario o rl id a alguna variable de sesion
			m.put("view", "/home/index");
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


	@GetMapping("/usuario/perfil")
	public String perfil(ModelMap m){
		m.put("view","usuario/perfil");
		return("views/_t/main");
	}
}