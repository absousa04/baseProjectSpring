package br.com.vr.divulgacao.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Objeto não encontrado");
    }

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
