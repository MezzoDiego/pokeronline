package it.prova.pokeronline.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pokeronline.service.UtenteService;

@RestController
@RequestMapping("api/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;
	
}
