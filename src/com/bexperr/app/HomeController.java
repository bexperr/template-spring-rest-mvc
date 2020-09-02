package com.bexperr.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	
	private static final Logger log = LogManager.getLogger(HomeController.class);

	@GetMapping(value = {"","/", "/Home"})
	public String goHome(HttpServletRequest request, HttpServletResponse response ,Model model ) {
		log.info("Entrando al home");
		return "home";
	}
}
