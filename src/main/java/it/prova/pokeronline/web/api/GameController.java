package it.prova.pokeronline.web.api;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pokeronline.dto.TavoloDTO;
import it.prova.pokeronline.dto.UtenteDTO;
import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.service.UtenteService;
import it.prova.pokeronline.web.api.exceptions.NotFoundException;

@RestController
@RequestMapping("api/play")
public class GameController {

	@Autowired
	private UtenteService utenteService;
	
	@GetMapping("/compraCredito/{money}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addCredito(@PathVariable(required = true) Integer money, Principal principal) {
		utenteService.addCredito(money, principal.getName());
	}
	
}
