package it.prova.pokeronline.web.api.exceptions;

public class NotEnoughExperienceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughExperienceException() {

	}

	public NotEnoughExperienceException(String message) {
		super(message);
	}

}
