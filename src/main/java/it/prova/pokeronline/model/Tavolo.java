package it.prova.pokeronline.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tavolo")
public class Tavolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "esperienzaMinima")
	private Integer esperienzaMinima;

	@Column(name = "cifraMinima")
	private Integer cifraMinima;

	@Column(name = "denominazione")
	private String denominazione;

	@Column(name = "dataCreazione")
	private LocalDate dataCreazione;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tavolo")
	private Set<Utente> utenti = new HashSet<>(0);

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id", referencedColumnName = "ID", nullable = false)
	private Utente utenteCreazione;

	public Tavolo() {
		super();
	}

	public Tavolo(Integer esperienzaMinima, Integer cifraMinima, String denominazione, LocalDate dataCreazione) {
		super();
		this.esperienzaMinima = esperienzaMinima;
		this.cifraMinima = cifraMinima;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
	}

	public Tavolo(Long id, Integer esperienzaMinima, Integer cifraMinima, String denominazione, LocalDate dataCreazione,
			Set<Utente> utenti, Utente utenteCreazione) {
		super();
		this.id = id;
		this.esperienzaMinima = esperienzaMinima;
		this.cifraMinima = cifraMinima;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
		this.utenti = utenti;
		this.utenteCreazione = utenteCreazione;
	}
	
	public Tavolo(Long id, Integer esperienzaMinima, Integer cifraMinima, String denominazione,
			LocalDate dataCreazione) {
		super();
		this.id = id;
		this.esperienzaMinima = esperienzaMinima;
		this.cifraMinima = cifraMinima;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEsperienzaMinima() {
		return esperienzaMinima;
	}

	public void setEsperienzaMinima(Integer esperienzaMinima) {
		this.esperienzaMinima = esperienzaMinima;
	}

	public Integer getCifraMinima() {
		return cifraMinima;
	}

	public void setCifraMinima(Integer cifraMinima) {
		this.cifraMinima = cifraMinima;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public LocalDate getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDate dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Set<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(Set<Utente> utenti) {
		this.utenti = utenti;
	}

	public Utente getUtenteCreazione() {
		return utenteCreazione;
	}

	public void setUtenteCreazione(Utente utenteCreazione) {
		this.utenteCreazione = utenteCreazione;
	}

}
