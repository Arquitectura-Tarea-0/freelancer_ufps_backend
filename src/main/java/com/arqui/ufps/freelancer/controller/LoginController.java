package com.arqui.ufps.freelancer.controller;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PATCH,RequestMethod.PUT})
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
