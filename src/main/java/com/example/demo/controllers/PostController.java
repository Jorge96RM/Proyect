package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
	
	@GetMapping("/post/crearPost")
	public String crearPost(ModelMap m){
		m.put("view","post/crearPost");
		return("views/_t/main");
	}
	
	@GetMapping("/post/redacciones")
	public String redacciones(ModelMap m){
		m.put("view","post/redacciones");
		return("views/_t/main");
	}
	
	
	@GetMapping("/post/curriculums")
	public String curriculums(ModelMap m){
		m.put("view","post/curriculums");
		return("views/_t/main");
	}
}
