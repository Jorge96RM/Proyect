package com.example.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Post;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.RespuestaRepository;

@Controller
public class HomeController implements ErrorController{
	@Autowired
	private PostRepository repoPost;
	
	@Autowired
	private CategoriaRepository repoCategoria;
	
	@Autowired
	private RespuestaRepository repoRespuesta;
	
	@GetMapping("/")
	public String index(ModelMap m){
		Map<String, Post> ultimasRespuestas = new HashMap<>();
		Map<String, Long> numeroPosts = new HashMap<>();
		Map<String, Long> numeroRespuestas = new HashMap<>();
		Categoria categoria = null;
		
		//Redacciones
		categoria = repoCategoria.getByName(Categoria.REDACCIONES);
		ultimasRespuestas.put(Categoria.REDACCIONES, repoPost.ultimoPost(categoria));
		numeroPosts.put(Categoria.REDACCIONES, repoPost.contarPost(categoria));
		numeroRespuestas.put(Categoria.REDACCIONES, repoRespuesta.contarRespuestasDeCadaCategoria(categoria));
		
		//Curriculums
		categoria = repoCategoria.getByName(Categoria.CURRICULUMS);
		ultimasRespuestas.put(Categoria.CURRICULUMS, repoPost.ultimoPost(categoria));
		numeroPosts.put(Categoria.CURRICULUMS, repoPost.contarPost(categoria));
		numeroRespuestas.put(Categoria.CURRICULUMS, repoRespuesta.contarRespuestasDeCadaCategoria(categoria));
		
		//Cartas de presentacion
		categoria = repoCategoria.getByName(Categoria.CARTAS);
		ultimasRespuestas.put(Categoria.CARTAS, repoPost.ultimoPost(categoria));
		numeroPosts.put(Categoria.CARTAS, repoPost.contarPost(categoria));
		numeroRespuestas.put(Categoria.CARTAS, repoRespuesta.contarRespuestasDeCadaCategoria(categoria));
		
		//Otras correciones
		categoria = repoCategoria.getByName(Categoria.OTROSC);
		ultimasRespuestas.put(Categoria.OTROSC, repoPost.ultimoPost(categoria));
		numeroPosts.put(Categoria.OTROSC, repoPost.contarPost(categoria));
		numeroRespuestas.put(Categoria.OTROSC, repoRespuesta.contarRespuestasDeCadaCategoria(categoria));
		
		//Expresiones
		categoria = repoCategoria.getByName(Categoria.EXPRESIONES);
		ultimasRespuestas.put(Categoria.EXPRESIONES, repoPost.ultimoPost(categoria));
		numeroPosts.put(Categoria.EXPRESIONES, repoPost.contarPost(categoria));
		numeroRespuestas.put(Categoria.EXPRESIONES, repoRespuesta.contarRespuestasDeCadaCategoria(categoria));
		
		//Gramática
		categoria = repoCategoria.getByName(Categoria.GRAMATICA);
		ultimasRespuestas.put(Categoria.GRAMATICA, repoPost.ultimoPost(categoria));
		numeroPosts.put(Categoria.GRAMATICA, repoPost.contarPost(categoria));
		numeroRespuestas.put(Categoria.GRAMATICA, repoRespuesta.contarRespuestasDeCadaCategoria(categoria));
		
		//Jerga
		categoria = repoCategoria.getByName(Categoria.JERGA);
		ultimasRespuestas.put(Categoria.JERGA, repoPost.ultimoPost(categoria));
		numeroPosts.put(Categoria.JERGA, repoPost.contarPost(categoria));
		numeroRespuestas.put(Categoria.JERGA, repoRespuesta.contarRespuestasDeCadaCategoria(categoria));
		
		//Otras dudas
		categoria = repoCategoria.getByName(Categoria.OTROSD);
		ultimasRespuestas.put(Categoria.OTROSD, repoPost.ultimoPost(categoria));
		numeroPosts.put(Categoria.OTROSD, repoPost.contarPost(categoria));
		numeroRespuestas.put(Categoria.OTROSD, repoRespuesta.contarRespuestasDeCadaCategoria(categoria));
		
		m.addAttribute("ultimasRespuestas", ultimasRespuestas);
		m.addAttribute("numeroPosts", numeroPosts);
		m.addAttribute("numeroRespuestas", numeroRespuestas);
		m.put("view","home/index");
		return "views/_t/main";
	}
	
	
	//Cuando se produce un error de cualquier tipo en la página, redirige a la raíz
    @RequestMapping("/error")
    public String handleError(ModelMap m) {
		m.put("view","_t/erroresPagina");
		return "views/_t/main";
    }
 
    public String getErrorPath() {
        return "/error";
    }
}
