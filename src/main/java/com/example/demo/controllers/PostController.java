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

import com.example.demo.domain.Post;
import com.example.demo.domain.Rol;
import com.example.demo.domain.Usuario;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UsuarioRepository;


@Controller
public class PostController {
	
	@Autowired
	private PostRepository repoPost;
	
	@Autowired
	private UsuarioRepository repoUsuario;
	
	@GetMapping("/post/crearPost")
	public String crearPost(ModelMap m){
		m.put("view","post/crearPost");
		return("views/_t/main");
	}
	
	@GetMapping("/post/redacciones")
	public String redacciones(ModelMap m,
		HttpSession s){
		s.setAttribute("posts", repoPost.listarPost());
		m.put("view","post/redacciones");
		return("views/_t/main");
	}
	
	@GetMapping("/post/curriculums")
	public String curriculums(ModelMap m){
		m.put("view","post/curriculums");
		return("views/_t/main");
	}
	
	@RequestMapping(value = "/post/crea", method = RequestMethod.GET)
	public String crea(ModelMap m){ 
		m.put("listaPost", repoPost.findAll());
		m.put("view","/");
		return "views/_t/main";
	}
	
	@RequestMapping(value = "/post/crea", method = RequestMethod.POST)
	public String crearPost(@RequestParam("titulo") String titulo,
			@RequestParam("contenido") String contenido,
			//@RequestParam("alias") String alias,
			ModelMap m,
			HttpSession s){
		Usuario user = (Usuario) s.getAttribute("userData");
		Post p = new Post(titulo, contenido, user);
		System.out.println(user.getAlias());
		repoPost.save(p);
		m.put("view","/post/redacciones");
		return "views/_t/main";
	}
}
