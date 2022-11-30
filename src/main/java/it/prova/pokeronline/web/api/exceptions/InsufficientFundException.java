package it.prova.pokeronline.web.api.exceptions;

public class InsufficientFundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientFundException() {

	}

	public InsufficientFundException(String message) {
		super(message);
	}

}
