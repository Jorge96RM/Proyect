package com.example.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(ModelMap m){
		m.put("view","login/login");
		return("views/_t/main");
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("nombre") String nombre, 
			@RequestParam("pwd") String pwd, 
			ModelMap m, 
			HttpSession s){
		if(nombre.equals("admin") && pwd.equals("admin")){
			m.put("view", "login/loginOK");
			s.setAttribute("user", "admin");
		}else{
			s.setAttribute("user", "anonymous");
			m.put("view", "login/loginError");
		}
		return "views/_t/main";
	}
	
	@GetMapping("/logout")
	public String logout(ModelMap m, HttpSession session){
		session.invalidate();
		//s.setAttribute("user", "anonymous");
		m.put("view","home/index");
		return "redirect:/";
	}
	

}
