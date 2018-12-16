package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Post;
import com.example.demo.domain.Respuesta;
import com.example.demo.domain.Usuario;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.RespuestaRepository;
import com.example.demo.repositories.UsuarioRepository;


@Controller
public class PostController {
	
	@Autowired
	private PostRepository repoPost;
	
	@Autowired
	private RespuestaRepository repoRespuesta;
	
	@Autowired
	private CategoriaRepository repoCategoria;
	
	@Autowired
	private UsuarioRepository repoUsuario;
	
	@GetMapping("/post/crearPost")
	public String crearPost(ModelMap m){
		m.put("view","post/crearPost");
		return("views/_t/main");
	}

	@GetMapping("/respuesta/crearRespuesta/{id}")
	public String crearRespuesta(@PathVariable("id") Long id,
		ModelMap m){
		m.addAttribute("post", repoPost.todosPost(id));
		m.put("view","respuesta/respuestaPost");
		return("views/_t/main");
	}
	
	@RequestMapping(value = "/respuesta/crearRespuesta/{id}", method = RequestMethod.POST)
	public String crearRespuestaPost(@PathVariable("id") Post postRespuesta,
			@RequestParam("contenido") String contenido,
			@RequestParam("respuestaSuya") Usuario respuestaSuya,
			ModelMap m,
			HttpSession s){
		Respuesta resp = new Respuesta(postRespuesta, contenido, respuestaSuya);
		repoRespuesta.save(resp);
		return "redirect:/respuesta/respuesta/" + postRespuesta.getId();
	}
	
	@GetMapping(value = "/respuesta/respuesta/{id}")
	public String respuesta(@PathVariable("id") long id, 
		@PathVariable("id") Post pid, 
		ModelMap m) {
		Post p = repoPost.todosPost(id);
		p.setnVisitas(p.getnVisitas()+1);
		repoPost.save(p);		
		m.addAttribute("post", p);
		m.addAttribute("respuestas", repoRespuesta.listarRespuestasDeCadaPost(pid));
		//System.out.println(repoRespuesta.listarRespuestasDeCadaPost(id));
		m.put("view","respuesta/respuesta");
		return("views/_t/main");
	}
	
	@GetMapping("/post/redacciones")
	public String redacciones(ModelMap m,
		HttpSession s){
		List<Post> posts = repoPost.listarPost(repoCategoria.getByName(Categoria.REDACCIONES));
		Map<Long, Long> nRespuestas = new HashMap<>();
		for(Post p : posts){
			nRespuestas.put(p.getId(), repoRespuesta.contarRespuestasDeCadaPost(p));
		}
		m.addAttribute("nRespuestas", nRespuestas);
		m.addAttribute("posts", posts);
		s.setAttribute("categoria", Categoria.REDACCIONES);
		m.put("view","post/redacciones");
		return("views/_t/main");
	}
	
	@GetMapping("/post/curriculums")
	public String curriculums(ModelMap m,
		HttpSession s){
		List<Post> posts = repoPost.listarPost(repoCategoria.getByName(Categoria.CURRICULUMS));
		Map<Long, Long> nRespuestas = new HashMap<>();
		for(Post p : posts){
			nRespuestas.put(p.getId(), repoRespuesta.contarRespuestasDeCadaPost(p));
		}
		m.addAttribute("nRespuestas", nRespuestas);
		m.addAttribute("posts", posts);
		s.setAttribute("categoria", Categoria.CURRICULUMS);
		m.put("view","post/curriculums");
		return("views/_t/main");
	}
	
	@GetMapping("/post/cartas")
	public String cartas(ModelMap m,
		HttpSession s){
		List<Post> posts = repoPost.listarPost(repoCategoria.getByName(Categoria.CARTAS));
		Map<Long, Long> nRespuestas = new HashMap<>();
		for(Post p : posts){
			nRespuestas.put(p.getId(), repoRespuesta.contarRespuestasDeCadaPost(p));
		}
		m.addAttribute("nRespuestas", nRespuestas);
		m.addAttribute("posts", posts);
		s.setAttribute("categoria", Categoria.CARTAS);
		m.put("view","post/cartas");
		return("views/_t/main");
	}
	
	@GetMapping("/post/otrosC")
	public String otrosC(ModelMap m,
		HttpSession s){
		List<Post> posts = repoPost.listarPost(repoCategoria.getByName(Categoria.OTROSC));
		Map<Long, Long> nRespuestas = new HashMap<>();
		for(Post p : posts){
			nRespuestas.put(p.getId(), repoRespuesta.contarRespuestasDeCadaPost(p));
		}
		m.addAttribute("nRespuestas", nRespuestas);
		m.addAttribute("posts", posts);
		s.setAttribute("categoria", Categoria.OTROSC);
		m.put("view","post/otrosC");
		return("views/_t/main");
	}
	
	@GetMapping("/post/expresiones")
	public String expresiones(ModelMap m,
		HttpSession s){
		List<Post> posts = repoPost.listarPost(repoCategoria.getByName(Categoria.EXPRESIONES));
		Map<Long, Long> nRespuestas = new HashMap<>();
		for(Post p : posts){
			nRespuestas.put(p.getId(), repoRespuesta.contarRespuestasDeCadaPost(p));
		}
		m.addAttribute("nRespuestas", nRespuestas);
		m.addAttribute("posts", posts);
		s.setAttribute("categoria", Categoria.EXPRESIONES);
		m.put("view","post/expresiones");
		return("views/_t/main");
	}
	
	@GetMapping("/post/gramatica")
	public String gramatica(ModelMap m,
		HttpSession s){
		List<Post> posts = repoPost.listarPost(repoCategoria.getByName(Categoria.GRAMATICA));
		Map<Long, Long> nRespuestas = new HashMap<>();
		for(Post p : posts){
			nRespuestas.put(p.getId(), repoRespuesta.contarRespuestasDeCadaPost(p));
		}
		m.addAttribute("nRespuestas", nRespuestas);
		m.addAttribute("posts", posts);
		s.setAttribute("categoria", Categoria.GRAMATICA);
		m.put("view","post/gramatica");
		return("views/_t/main");
	}
	
	@GetMapping("/post/jerga")
	public String jerga(ModelMap m,
		HttpSession s){
		List<Post> posts = repoPost.listarPost(repoCategoria.getByName(Categoria.JERGA));
		Map<Long, Long> nRespuestas = new HashMap<>();
		for(Post p : posts){
			nRespuestas.put(p.getId(), repoRespuesta.contarRespuestasDeCadaPost(p));
		}
		m.addAttribute("nRespuestas", nRespuestas);
		m.addAttribute("posts", posts);
		s.setAttribute("categoria", Categoria.JERGA);
		m.put("view","post/jerga");
		return("views/_t/main");
	}
	
	@GetMapping("/post/otrosD")
	public String otrosD(ModelMap m,
		HttpSession s){
		List<Post> posts = repoPost.listarPost(repoCategoria.getByName(Categoria.OTROSD));
		Map<Long, Long> nRespuestas = new HashMap<>();
		for(Post p : posts){
			nRespuestas.put(p.getId(), repoRespuesta.contarRespuestasDeCadaPost(p));
		}
		m.addAttribute("nRespuestas", nRespuestas);
		m.addAttribute("posts", posts);
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
		user.setPuntos(user.getPuntos() + 3);
		repoUsuario.save(user);
		Categoria categoria = repoCategoria.getByName(categoriaName);
		Post p = new Post(titulo, contenido, user, categoria);
		repoPost.save(p);
		return "redirect:/respuesta/respuesta/" + p.getId();
	}
	
	@GetMapping("/post/modificarPost/{id}")
	public String actualizar(@PathVariable("id") Long id,
		ModelMap m){
		m.addAttribute("pos", repoPost.todosPost(id));
		m.put("view","post/modificarPost");
		return("views/_t/main");
	}
	
	@RequestMapping(value = "/post/modificarPost/{id}", method = RequestMethod.POST)
	public String actualizarPost(@PathVariable("id") Long id,
			@RequestParam("titulo") String titulo,
			@RequestParam("contenido") String contenido,
			ModelMap m,
			HttpSession s){
		Post p = repoPost.todosPost(id);
		if(p == null){
			return "redirect:/";
		}
		p.setTitulo(titulo);
		p.setContenido(contenido);
		repoPost.save(p);
		return "redirect:/respuesta/respuesta/" + p.getId();
	}
	
	@GetMapping("/respuesta/modificarRespuesta/{id}")
	public String actualizarRespuesta(@PathVariable("id") Long id,
		ModelMap m){
		m.addAttribute("respuesta", repoRespuesta.respuestaPorId(id));
		m.put("view","respuesta/modificarRespuesta");
		return("views/_t/main");
	}
	
	@RequestMapping(value = "/respuesta/modificarRespuesta/{id}", method = RequestMethod.POST)
	public String actualizarRespuestaPost(@PathVariable("id") Long id,
			@RequestParam("contenido") String contenido,
			ModelMap m,
			HttpSession s){
		Respuesta r = repoRespuesta.respuestaPorId(id);
		if(r == null){
			return "redirect:/";
		}
		r.setContenido(contenido);
		repoRespuesta.save(r);
		return "redirect:/respuesta/respuesta/" + r.getPostRespuesta().getId();
	}
	
	@RequestMapping(value = "/post/modificarCategoria/{id}", method = RequestMethod.POST)
	public String modificarCategoria(@PathVariable("id") Long id,
			@RequestParam("selectCambiarCategoria") Categoria categoria,
			ModelMap m,
			HttpSession s){
		Post p = repoPost.todosPost(id);
		if(p == null){
			return "redirect:/";
		}
		p.setNombre_categoria(categoria);
		repoPost.save(p);
		return "redirect:/";
	}
	
	@GetMapping(value = "/respuesta/like/{id}/{idRes}")
	public String darLike(@PathVariable("id")Long id,
		@PathVariable("idRes")Long idRes,
		HttpSession s){
		Usuario user  = repoUsuario.usuarioPorId(id);
		Respuesta res = repoRespuesta.respuestaPorId(idRes);
		
		@SuppressWarnings("unchecked")
		Map<Long, List<Long>> postsVotados = (Map<Long, List<Long>>) s.getAttribute("votado");
		if(postsVotados == null){
			System.out.println("No se puede votar sin estar logueado"); //En principio no debería usarse
			return "redirect:/respuesta/respuesta/" + res.getPostRespuesta().getId();
		}
		
		List<Long> respuestasVotadas = postsVotados.get(id);
		if(respuestasVotadas == null){
			respuestasVotadas = new ArrayList<Long>();
			postsVotados.put(id, respuestasVotadas);
		}else if(respuestasVotadas.contains(idRes)){
			return "redirect:/respuesta/respuesta/" + res.getPostRespuesta().getId();
		}
		respuestasVotadas.add(idRes);		
		
		user.setPuntos(user.getPuntos() + 1);
		res.setPuntos(res.getPuntos() + 1);
		res.setLikes(res.getLikes() + 1);
		repoUsuario.save(user);
		repoRespuesta.save(res);
		return "redirect:/respuesta/respuesta/" + res.getPostRespuesta().getId();
		}
	
	@GetMapping(value = "/respuesta/dislike/{id}/{idRes}")
	public String darDisLike(@PathVariable("id")Long id,
		@PathVariable("idRes")Long idRes,
		HttpSession s){
		Usuario user  = repoUsuario.usuarioPorId(id);
		Respuesta res = repoRespuesta.respuestaPorId(idRes);
		
		@SuppressWarnings("unchecked")
		Map<Long, List<Long>> postsVotados = (Map<Long, List<Long>>) s.getAttribute("votado");
		if(postsVotados == null){
			System.out.println("No se puede votar sin estar logueado"); //En principio no debería usarse
			return "redirect:/respuesta/respuesta/" + res.getPostRespuesta().getId();
		}
		
		List<Long> respuestasVotadas = postsVotados.get(id);
		if(respuestasVotadas == null){
			respuestasVotadas = new ArrayList<Long>();
			postsVotados.put(id, respuestasVotadas);
		}else if(respuestasVotadas.contains(idRes)){
			return "redirect:/respuesta/respuesta/" + res.getPostRespuesta().getId();
		}
		respuestasVotadas.add(idRes);
		
		user.setPuntos(user.getPuntos() - 1);
		res.setPuntos(res.getPuntos() - 1);
		res.setDislikes(res.getDislikes() + 1);
		repoUsuario.save(user);
		repoRespuesta.save(res);
		return "redirect:/respuesta/respuesta/" + res.getPostRespuesta().getId();
	}
	
	
	/*************************** PARTE DE ADMINISTRACION ***************************/
	@RequestMapping(value="/admin/borrarPost/{id}",method = RequestMethod.GET)  
    public String borrarMensaje(@PathVariable("id") Long id,
    	ModelMap m) {
		repoRespuesta.borrarRespuestaPorId(id);
		repoPost.delete(id);
        return "redirect:/";
    }
}
