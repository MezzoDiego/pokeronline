package it.prova.pokeronline.service;

import java.util.List;

import it.prova.pokeronline.model.Tavolo;

public interface TavoloService {

	public List<Tavolo> listAllTavoli(String username);

	public Tavolo caricaSingoloTavolo(Long id);

	public Tavolo caricaSingoloTavoloConUtenti(Long id, String username);

	public Tavolo aggiorna(Tavolo tavoloInstance, String username);

	public Tavolo inserisciNuovo(Tavolo tavoloInstance, String username);

	public void rimuovi(Long idToRemove, String username);
	
	public List<Tavolo> findByExample(Tavolo example, String username);
	
	public List<Tavolo> findAllTavoliPlayable(String username);
	
	public void play(Long id, String username);
	
	public void leave(Long id, String username);

	
}
