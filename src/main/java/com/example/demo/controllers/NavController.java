package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
	
	@GetMapping("/nav/ranking")
	public String ranking(ModelMap m){
		m.put("view","nav/ranking");
		return("views/_t/main");
	}
	
	@GetMapping("/nav/portal")
	public String portal(ModelMap m){
		m.put("view","nav/portal");
		return("views/_t/main");
	}
	@GetMapping("/nav/normas")
	public String normas(ModelMap m){
		m.put("view","nav/normas");
		return("views/_t/main");
	}
}
