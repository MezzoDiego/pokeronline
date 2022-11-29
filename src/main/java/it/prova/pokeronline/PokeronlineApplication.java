package it.prova.pokeronline;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.service.RuoloService;
import it.prova.pokeronline.service.TavoloService;
import it.prova.pokeronline.service.UtenteService;


@SpringBootApplication
public class PokeronlineApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;
	@Autowired
	private TavoloService tavoloServiceInstance;
	
	public static void main(String[] args) {
		SpringApplication.run(PokeronlineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", Ruolo.ROLE_ADMIN));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Player", Ruolo.ROLE_PLAYER) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Player", Ruolo.ROLE_PLAYER));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Special Player", Ruolo.ROLE_SPECIAL_PLAYER) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Special Player", Ruolo.ROLE_SPECIAL_PLAYER));
		}

		// a differenza degli altri progetti cerco solo per username perche' se vado
		// anche per password ogni volta ne inserisce uno nuovo, inoltre l'encode della
		// password non lo
		// faccio qui perche gia lo fa il service di utente, durante inserisciNuovo
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", LocalDate.now());
			admin.setEmail("a.admin@prova.it");
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(admin);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("specialplayer") == null) {
			Utente specialPlayer = new Utente("specialplayer", "specialplayer", "Antonio", "Verdi", LocalDate.now());
			specialPlayer.setEmail("sp.specialplayer@prova.it");
			specialPlayer.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Special Player", Ruolo.ROLE_SPECIAL_PLAYER));
			utenteServiceInstance.inserisciNuovo(specialPlayer);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(specialPlayer.getId());
		
		}
		
		if (utenteServiceInstance.findByUsername("player") == null) {
			Utente player = new Utente("player", "player", "Antonioo", "Verdii", LocalDate.now());
			player.setEmail("p.player@prova.it");
			player.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Player", Ruolo.ROLE_PLAYER));
			utenteServiceInstance.inserisciNuovo(player);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(player.getId());
		}

		if (utenteServiceInstance.findByUsername("player1") == null) {
			Utente player1 = new Utente("player1", "player1", "Antoniooo", "Verdiii", LocalDate.now());
			player1.setEmail("p.player1@prova.it");
			player1.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Player", Ruolo.ROLE_PLAYER));
			utenteServiceInstance.inserisciNuovo(player1);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(player1.getId());
		}
		
	}

}
