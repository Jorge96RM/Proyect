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
}
