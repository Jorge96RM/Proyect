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

@Controller
public class HomeController { //implements ErrorController{
	@Autowired
	private PostRepository repoPost;
	
	@Autowired
	private CategoriaRepository repoCategoria;
	
	@GetMapping("/")
	public String index(ModelMap m){
		Map<String, Post> ultimasRespuestas = new HashMap<>();
		Map<String, Long> numeroRespuestas = new HashMap<>();
		Categoria categoria = null;
		
		//Redacciones
		categoria = repoCategoria.getByName(Categoria.REDACCIONES);
		ultimasRespuestas.put(Categoria.REDACCIONES, repoPost.ultimoPost(categoria));
		numeroRespuestas.put(Categoria.REDACCIONES, repoPost.contarPost(categoria));
		
		//Curriculums
		categoria = repoCategoria.getByName(Categoria.CURRICULUMS);
		ultimasRespuestas.put(Categoria.CURRICULUMS, repoPost.ultimoPost(categoria));
		numeroRespuestas.put(Categoria.CURRICULUMS, repoPost.contarPost(categoria));
		
		//Cartas de presentacion
		categoria = repoCategoria.getByName(Categoria.CARTAS);
		ultimasRespuestas.put(Categoria.CARTAS, repoPost.ultimoPost(categoria));
		numeroRespuestas.put(Categoria.CARTAS, repoPost.contarPost(categoria));
		
		//Otras correciones
		categoria = repoCategoria.getByName(Categoria.OTROSC);
		ultimasRespuestas.put(Categoria.OTROSC, repoPost.ultimoPost(categoria));
		numeroRespuestas.put(Categoria.OTROSC, repoPost.contarPost(categoria));
		
		//Expresiones
		categoria = repoCategoria.getByName(Categoria.EXPRESIONES);
		ultimasRespuestas.put(Categoria.EXPRESIONES, repoPost.ultimoPost(categoria));
		numeroRespuestas.put(Categoria.EXPRESIONES, repoPost.contarPost(categoria));
		
		//Gramática
		categoria = repoCategoria.getByName(Categoria.GRAMATICA);
		ultimasRespuestas.put(Categoria.GRAMATICA, repoPost.ultimoPost(categoria));
		numeroRespuestas.put(Categoria.GRAMATICA, repoPost.contarPost(categoria));
		
		//Jerga
		categoria = repoCategoria.getByName(Categoria.JERGA);
		ultimasRespuestas.put(Categoria.JERGA, repoPost.ultimoPost(categoria));
		numeroRespuestas.put(Categoria.JERGA, repoPost.contarPost(categoria));
		
		//Otras dudas
		categoria = repoCategoria.getByName(Categoria.OTROSD);
		ultimasRespuestas.put(Categoria.OTROSD, repoPost.ultimoPost(categoria));
		numeroRespuestas.put(Categoria.OTROSD, repoPost.contarPost(categoria));
		
		m.addAttribute("ultimasRespuestas", ultimasRespuestas);
		m.addAttribute("numeroRespuestas", numeroRespuestas);
		m.put("view","home/index");
		return "views/_t/main";
	}
	
	
	//Cuando se produce un error de cualquier tipo en la página, redirige a la raíz
	//Comentado hasta terminar el proyecto para poder ver los errores
    /*@RequestMapping("/error")
    public String handleError() {
    	return "redirect:/";
    }
 
    public String getErrorPath() {
        return "/error";
    }*/
}
