package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Mail;
import com.example.demo.domain.Mensaje;
import com.example.demo.domain.Rol;
import com.example.demo.domain.Usuario;
import com.example.demo.repositories.RolRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.repositories.MensajeRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.RespuestaRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repoUsuario;
	@Autowired
	private RolRepository repoRol;
	@Autowired
	private MensajeRepository repoMensaje;
	@Autowired
	private PostRepository repoPost;
	@Autowired
	private RespuestaRepository repoRespuesta;
	
	@Autowired
	Mail mail;
	
	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public String registro(ModelMap m){ 
		m.put("listaRoles", repoRol.findAll());
		m.put("view","/");
		return "views/_t/main";
	}
	
	@RequestMapping(value = "/registro", method = RequestMethod.POST)
	public String registroPost(@RequestParam("alias") String alias,
			@RequestParam("contrasena") String contrasena,
			@RequestParam("nombre") String nombre,
			@RequestParam("primerApellido") String primerApellido,
			@RequestParam("segundoApellido") String segundoApellido,
			@RequestParam("telefono") String telefono,
			@RequestParam("email") String email,
			@RequestParam("sexo") String sexo,
			ModelMap m)  {
		
		
		Rol rolPorDefecto = (Rol) repoRol.getDefaultRol();
		Usuario u = new Usuario(alias, contrasena, nombre, primerApellido, segundoApellido, telefono, email, sexo, rolPorDefecto);
        try {    
            if (repoUsuario.datosPerfil(alias) != null) {
                m.put("view", "/_t/error");
            }
        } catch (Exception e) {
        	String asuntoCorreo = "Bienvenido al foro Correct English";
        	String contenidoCorreo = "<h3>Bienvenido a Correct English.</h3> \n<p> Este es un mensaje de bienvenida al foro, por favor no lo conteste. Un saludo y que disfrute de la página.</p>";
        	m.put("alias", alias);
			m.put("view", "/usuario/usuarioCreado");
			mail.sendEmail(email, asuntoCorreo, contenidoCorreo);
	    	repoUsuario.save(u);
        	System.out.println(e.getMessage());
        }
		return "views/_t/main";
			}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String home(ModelMap m){ 
		m.put("view","/");
		return "views/_t/main";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String homeOK(@RequestParam("alias") String alias,
			@RequestParam("contrasena") String contrasena,
			ModelMap m,
			HttpSession s){
		boolean usuarioOK = repoUsuario.usuarioOK(alias,contrasena);
		try {
			int esActivo = repoUsuario.esActivo(alias);
			
			if (!usuarioOK) {
				m.put("view", "usuario/loginError");
			} else if(esActivo == 0) {
				m.put("view", "usuario/usuarioSuspendido");
			}
			else {
				s.setAttribute("user", alias);
				s.setAttribute("userData", repoUsuario.datosPerfil(alias));
				s.setAttribute("votado", new HashMap<Long, List<Long>>());
				return "redirect:/";
			}
		} catch (Exception e){
			m.put("view", "usuario/loginError");
		}
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
	public String perfil(ModelMap m,
		HttpSession s){
		Usuario u = (Usuario) s.getAttribute("userData");
		if(u == null){
			return "redirect:/";
		}
		s.setAttribute("nmensajes", repoMensaje.contarMensajesNoLeidos(u));
		s.setAttribute("todosPost", repoPost.todosPostUsuario(u.getId()));
		s.setAttribute("todasRespuestas", repoRespuesta.todasRespuestas(u.getId()));
		try {
		s.setAttribute("mejorRespuesta", repoRespuesta.mejorRespuesta(u.getId()));
		} catch(NullPointerException e){
			e.getMessage();
		}
		m.put("view","usuario/perfil");
		return("views/_t/main");
	}
	
	@RequestMapping(value = "/perfil", method = RequestMethod.POST)
	public String actualizarPerfil(@RequestParam("nombre") String nombre,
			@RequestParam("primerApellido") String primerApellido,
			@RequestParam("segundoApellido") String segundoApellido,
			@RequestParam("telefono") String telefono,
			@RequestParam("email") String email,
			@RequestParam("imgString") String imgString,
			ModelMap m,
			HttpSession s){
		Usuario u = (Usuario) s.getAttribute("userData");
		if(u == null){
			return "redirect:/";
		}		
		u.setNombre(nombre);
		u.setPrimerApellido(primerApellido);
		u.setSegundoApellido(segundoApellido);
		u.setTelefono(telefono);
		u.setEmail(email);
		u.setFoto(imgString);
		repoUsuario.save(u);
		m.put("view","usuario/perfil");
		return "views/_t/main";
	}
	
	@GetMapping(value = "/usuario/perfilAjeno/{id}")
	public String perfilAjeno(@PathVariable("id") long id,
		ModelMap m,
		HttpSession s) {
		Usuario u = (Usuario) s.getAttribute("userData");
		m.put("ajeno", repoUsuario.usuarioPorId(id));
		m.put("todosPost", repoPost.todosPostUsuario(id));
		m.put("todasRespuestas", repoRespuesta.todasRespuestas(id));
		try {
			m.put("mejorRespuesta", repoRespuesta.mejorRespuesta(id));
		} catch (NullPointerException e){
			e.getMessage();
		}
		if((u == null) || (u.getId() != repoUsuario.usuarioPorId(id).getId())){
			m.put("view","usuario/perfilAjeno");
		}else{
			return "redirect:/usuario/perfil";
		}
		return("views/_t/main");
	}

	@RequestMapping(value="/usuario/borrarMensaje/{id}",method = RequestMethod.GET)  
    public String borrarMensaje(@PathVariable("id") Long id,
    	ModelMap m) {
        repoMensaje.delete(id);
        return "redirect:/usuario/buzonMensajes";
    } 
	
	@GetMapping("/usuario/buzonMensajes")
	public String buzon(ModelMap m,
		HttpSession s){
		Usuario u = (Usuario) s.getAttribute("userData");
		if(u == null){
			return "redirect:/";
		}
		m.addAttribute("mensajes", repoMensaje.listarMensajes(u));
		m.put("view","usuario/buzonMensajes");
		return("views/_t/main");
	}
	
	@GetMapping("/usuario/verMensaje/{id}")
	public String verMensaje(@PathVariable("id") long id, 
		ModelMap m){
		Mensaje men = repoMensaje.contenidoMensaje(id);
		men.setLeido(true);
		repoMensaje.save(men);
		m.addAttribute("mensaje", men);
		m.put("view","usuario/verMensaje");
		return("views/_t/main");
	}
	
	@GetMapping("/usuario/mensajePrivado/{id}")
	public String mensajePrivado(@PathVariable("id") Long id,
		ModelMap m){
		m.put("ajeno", repoUsuario.usuarioPorId(id));
		m.put("view","usuario/enviarMensaje");
		return("views/_t/main");
	}

	@RequestMapping(value = "/usuario/mensajePrivado/{id}", method = RequestMethod.POST)
	public String mensajePrivadoPost(@PathVariable("id") Usuario mensajeA, //Se recoge el usuario del perfil al que nos metemos
			@RequestParam("titulo") String titulo,
			@RequestParam("contenido") String contenido,
			@RequestParam("mensajeDe") Usuario mensajeDe, //Se recoge el usuario logueado
			ModelMap m,
			HttpSession s){
		Mensaje men = new Mensaje(titulo, contenido, mensajeDe, mensajeA);
		repoMensaje.save(men);
		return "redirect:/usuario/perfilAjeno/" + mensajeA.getId();
	}
	
	@GetMapping("/usuario/cambiarContrasena")
	public String cambiarContrasena(ModelMap m){
		m.put("view","usuario/cambiarContrasena");
		return("views/_t/main");
	}
	
	@RequestMapping(value = "/cambiarContrasena", method = RequestMethod.POST)
	public String cambiarContrasena(@RequestParam("contrasena") String contrasena,
			@RequestParam("contrasenaNueva") String contrasenaNueva,
			ModelMap m,
			HttpSession s){
		Usuario u = (Usuario) s.getAttribute("userData");
		if (u.getContrasena().equals(contrasena)) {
			u.setContrasena(contrasenaNueva);
			repoUsuario.save(u);
			return "redirect:/usuario/perfil";
		}
		else {
			m.put("view", "usuario/contrasenaErronea");
			return "views/_t/main";
		}
	}
	
	@RequestMapping(value="/borrarUsuario/{id}",method = RequestMethod.POST)  
    public String borrarUsuario(@PathVariable("id") Long id,
    	@RequestParam("contrasena") String contrasena,
    	ModelMap m,
    	HttpSession s) {
		Usuario u = (Usuario) s.getAttribute("userData");
		if (u.getContrasena().equals(contrasena)) {
        repoUsuario.delete(id);
        s.invalidate();
		}
		else {
			m.put("view", "usuario/contrasenaErronea");
			return "views/_t/main";
		}
        m.put("view", "usuario/borrarUsuario");
        return "views/_t/main";
    } 
	
	@RequestMapping(value="/suspenderCuenta/{id}/{activo}")
	public String suspenderCuenta(@PathVariable("id")Long id,@PathVariable("activo") int activo) {
		Usuario u = repoUsuario.usuarioPorId(id);
		if (activo == 0) {
			u.setActivo(1);
		} else {
			u.setActivo(0);
		}
		repoUsuario.save(u);
		return "redirect:/";
	}
}