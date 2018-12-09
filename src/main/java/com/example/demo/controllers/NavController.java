package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Post;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.RespuestaRepository;
import com.example.demo.repositories.UsuarioRepository;

@Controller
public class NavController {
	
	@Autowired
	private UsuarioRepository repoUsuario;
	
	@Autowired
	private PostRepository repoPost;
	
	@Autowired
	private RespuestaRepository repoRespuesta;
	
	@GetMapping("/nav/ranking")
	public String ranking(ModelMap m){
		m.put("usuarios", repoUsuario.todosUsuarios());
		m.put("view","nav/ranking");
		return("views/_t/main");
	}
	
	@RequestMapping(value = "/nav/buscador", method = RequestMethod.POST)
	public String crearRespuestaPost(@RequestParam("filtro") String filtro,
		ModelMap m){
		m.addAttribute("filt", filtro);
		List<Post> posts = repoPost.buscadorPost(filtro);
		Map<Long, Long> nRespuestas = new HashMap<>();
		for(Post p : posts){
			nRespuestas.put(p.getId(), repoRespuesta.contarRespuestasDeCadaPost(p));
		}
		m.addAttribute("nRespuestas", nRespuestas);
		m.addAttribute("postsBuscador", posts);
		m.put("view","nav/listaBuscador");
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
