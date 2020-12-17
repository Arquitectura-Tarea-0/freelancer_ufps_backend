package com.arqui.ufps.freelancer.models.controllers;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	protected final Log logger = LogFactory.getLog(this.getClass());
	@GetMapping("/login")
	public @ResponseBody String login(Principal principal) {
		
		if(principal != null) {
			logger.info("ENTRO AL LOGIN CONTROLLER");
			return "redirect:/";
		}	
		return "login";
	}
}
