package it.prova.pokeronline.web.api.exceptions;

public class PlayerBusyToAnotherTableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayerBusyToAnotherTableException() {

	}

	public PlayerBusyToAnotherTableException(String message) {
		super(message);
	}

}
