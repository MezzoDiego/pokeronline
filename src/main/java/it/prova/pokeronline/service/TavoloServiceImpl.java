package it.prova.pokeronline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.repository.tavolo.TavoloRepository;
import it.prova.pokeronline.repository.utente.UtenteRepository;
import it.prova.pokeronline.web.api.exceptions.NotFoundException;
import it.prova.pokeronline.web.api.exceptions.NotYourTavoloException;

@Service
@Transactional(readOnly = true)
public class TavoloServiceImpl implements TavoloService {

	@Autowired
	private TavoloRepository repository;

	@Autowired
	private UtenteRepository utenteRepository;

	@Override
	public List<Tavolo> listAllTavoli(String username) {

		Utente utenteInSessione = utenteRepository.findByUsername(username).orElse(null);
		if (utenteInSessione == null)
			throw new NotFoundException("Utente non trovato.");
		if (utenteInSessione.isAdmin())
			return (List<Tavolo>) repository.findAll();
		if (utenteInSessione.isSpecialPlayer())
			return repository.findAllTavoloEager(utenteInSessione.getId());

		return null;

	}

	@Override
	public Tavolo caricaSingoloTavolo(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Tavolo caricaSingoloTavoloConUtenti(Long id, String username) {
		Utente utenteInSessione = utenteRepository.findByUsername(username).orElse(null);
		if (utenteInSessione == null)
			throw new NotFoundException("Utente non trovato.");

		if (utenteInSessione.isAdmin())
			return repository.findSingleTavoloEagerAdmin(id);

		if (utenteInSessione.isSpecialPlayer())
			return repository.findSingleTavoloEagerSpecialPlayer(utenteInSessione.getId(), id);

		return null;
	}

	@Override
	@Transactional
	public Tavolo aggiorna(Tavolo tavoloInstance, String username) {
		Utente utenteInSessione = utenteRepository.findByUsername(username).orElse(null);
		if (utenteInSessione == null)
			throw new NotFoundException("Utente non trovato.");

		Tavolo tavoloReloaded = repository.findById(tavoloInstance.getId()).orElse(null);
		if (tavoloReloaded == null)
			throw new NotFoundException("Tavolo non trovato.");

		if (utenteInSessione.isAdmin() && tavoloReloaded.getUtenti().size() < 1) {
			tavoloReloaded.setUtenteCreazione(utenteInSessione);
			tavoloReloaded.setCifraMinima(tavoloInstance.getCifraMinima());
			tavoloReloaded.setEsperienzaMinima(tavoloInstance.getEsperienzaMinima());
			tavoloReloaded.setDenominazione(tavoloInstance.getDenominazione());
			return repository.save(tavoloReloaded);
		}

		if (utenteInSessione.isSpecialPlayer() && tavoloReloaded.getUtenti().size() < 1) {
			if (!tavoloReloaded.getUtenteCreazione().getId().equals(utenteInSessione.getId()))
				throw new NotYourTavoloException("Non e' possibile modificare il tavolo creato da un'altro player!");

			tavoloReloaded.setUtenteCreazione(utenteInSessione);
			tavoloReloaded.setCifraMinima(tavoloInstance.getCifraMinima());
			tavoloReloaded.setEsperienzaMinima(tavoloInstance.getEsperienzaMinima());
			tavoloReloaded.setDenominazione(tavoloInstance.getDenominazione());
			return repository.save(tavoloReloaded);
		}
		return null;
	}

	@Override
	@Transactional
	public Tavolo inserisciNuovo(Tavolo tavoloInstance, String username) {
		Utente utenteInSessione = utenteRepository.findByUsername(username).orElse(null);
		if (utenteInSessione == null)
			throw new NotFoundException("Utente non trovato.");

		tavoloInstance.setUtenteCreazione(utenteInSessione);
		return repository.save(tavoloInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove, String username) {
		Utente utenteInSessione = utenteRepository.findByUsername(username).orElse(null);
		if (utenteInSessione == null)
			throw new NotFoundException("Utente non trovato.");

		Tavolo tavoloReloaded = repository.findById(idToRemove).orElse(null);
		if (tavoloReloaded == null)
			throw new NotFoundException("Tavolo non trovato.");

		if (utenteInSessione.isAdmin() && tavoloReloaded.getUtenti().size() < 1)
			repository.deleteById(idToRemove);

		if (utenteInSessione.isSpecialPlayer() && tavoloReloaded.getUtenti().size() < 1) {
			if (!tavoloReloaded.getUtenteCreazione().getId().equals(utenteInSessione.getId()))
				throw new NotYourTavoloException("Non e' possibile eliminare il tavolo creato da un'altro player!");

			repository.deleteById(idToRemove);
		}

	}

	@Override
	public List<Tavolo> findByExample(Tavolo example, String username) {
		Utente utenteInSessione = utenteRepository.findByUsername(username).orElse(null);
		if (utenteInSessione == null)
			throw new NotFoundException("Utente non trovato.");
		
		if(utenteInSessione.isAdmin())
			return repository.findByExample(example);
		
		if(utenteInSessione.isSpecialPlayer())
			example.setUtenteCreazione(utenteInSessione);
			return repository.findByExampleEager(example);
		
	}

	@Override
	public List<Tavolo> findAllTavoliPlayable(String username) {
		Utente utenteInSessione = utenteRepository.findByUsername(username).orElse(null);
		if (utenteInSessione == null)
			throw new NotFoundException("Utente non trovato.");
		
		return repository.findAllTavoliPlayable(utenteInSessione.getEsperienzaAccumulata());
	}

}
