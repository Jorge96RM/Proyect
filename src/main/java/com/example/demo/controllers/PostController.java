package com.example.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Post;
import com.example.demo.domain.Usuario;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.PostRepository;


@Controller
public class PostController {
	
	@Autowired
	private PostRepository repoPost;
	
	
	@Autowired
	private CategoriaRepository repoCategoria;
	
	@GetMapping("/post/crearPost")
	public String crearPost(ModelMap m){
		m.put("view","post/crearPost");
		return("views/_t/main");
	}

	@GetMapping("/respuesta/crearRespuesta")
	public String crearRespuesta(ModelMap m){
		m.put("view","respuesta/respuestaPost");
		return("views/_t/main");
	}
	
	@RequestMapping(value="/respuesta/respuesta", method = RequestMethod.POST)
	public String respuesta(ModelMap m){
		m.put("view","respuesta/respuesta");
		return("views/_t/main");
	}
	
	@GetMapping(value = "/respuesta/respuesta/{id}")
	public String respuesta(@PathVariable("id") long id, 
		ModelMap m) {
		System.out.println(id);
		m.put("view","respuesta/respuesta");
		return("views/_t/main");
	}
	
	@GetMapping("/post/redacciones")
	public String redacciones(ModelMap m,
		HttpSession s){
		//Una manera de listar los post de una sola categoria
		s.setAttribute("posts", repoPost.listarPost(repoCategoria.getByName(Categoria.REDACCIONES)));
		s.setAttribute("categoria", Categoria.REDACCIONES);
		m.put("view","post/redacciones");
		return("views/_t/main");
	}
	
	@GetMapping("/post/curriculums")
	public String curriculums(ModelMap m,
		HttpSession s){
		//Otra manera de listar los post de una sola categoria
		s.setAttribute("posts", repoPost.listarPost(repoCategoria.postCurriculums()));
		s.setAttribute("categoria", Categoria.CURRICULUMS);
		m.put("view","post/curriculums");
		return("views/_t/main");
	}
	
	@GetMapping("/post/cartas")
	public String cartas(ModelMap m,
		HttpSession s){
		s.setAttribute("posts", repoPost.listarPost(repoCategoria.postCartas()));
		s.setAttribute("categoria", Categoria.CARTAS);
		m.put("view","post/cartas");
		return("views/_t/main");
	}
	@GetMapping("/post/otrosC")
	public String otrosC(ModelMap m,
		HttpSession s){
		s.setAttribute("posts", repoPost.listarPost(repoCategoria.postOtrosC()));
		s.setAttribute("categoria", Categoria.OTROSC);
		m.put("view","post/otrosC");
		return("views/_t/main");
	}
	
	@GetMapping("/post/expresiones")
	public String expresiones(ModelMap m,
		HttpSession s){
		s.setAttribute("posts", repoPost.listarPost(repoCategoria.postExpresiones()));
		s.setAttribute("categoria", Categoria.EXPRESIONES);
		m.put("view","post/expresiones");
		return("views/_t/main");
	}
	
	@GetMapping("/post/gramatica")
	public String gramatica(ModelMap m,
		HttpSession s){
		s.setAttribute("posts", repoPost.listarPost(repoCategoria.postGramatica()));
		s.setAttribute("categoria", Categoria.GRAMATICA);
		m.put("view","post/gramatica");
		return("views/_t/main");
	}
	
	@GetMapping("/post/jerga")
	public String jerga(ModelMap m,
		HttpSession s){
		s.setAttribute("posts", repoPost.listarPost(repoCategoria.postJerga()));
		s.setAttribute("categoria", Categoria.JERGA);
		m.put("view","post/jerga");
		return("views/_t/main");
	}
	
	@GetMapping("/post/otrosD")
	public String otrosD(ModelMap m,
		HttpSession s){
		s.setAttribute("posts", repoPost.listarPost(repoCategoria.postOtrosD()));
		s.setAttribute("categoria", Categoria.OTROSD);
		m.put("view","post/otrosD");
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
			@RequestParam("categoria") String categoriaName,
			ModelMap m,
			HttpSession s){
		Usuario user = (Usuario) s.getAttribute("userData");
		Categoria categoria = repoCategoria.getByName(categoriaName);
		Post p = new Post(titulo, contenido, user, categoria);
		repoPost.save(p);
		return "redirect:/respuesta/respuesta/" + p.getId();
	}
}
