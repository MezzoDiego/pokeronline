package it.prova.pokeronline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.repository.tavolo.TavoloRepository;

@Service
public class TavoloServiceImpl implements TavoloService{

	@Autowired
	private TavoloRepository repository;

	@Override
	public List<Tavolo> listAllTavoli() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tavolo caricaSingoloTavolo(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tavolo caricaSingoloTavoloConUtenti(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tavolo aggiorna(Tavolo tavoloInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tavolo inserisciNuovo(Tavolo tavoloInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rimuovi(Long idToRemove) {
		// TODO Auto-generated method stub
		
	}
	
}
