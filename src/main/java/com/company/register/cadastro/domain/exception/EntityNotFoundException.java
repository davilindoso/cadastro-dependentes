package com.company.register.cadastro.domain.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_ID_NOT_FOUND = "Não existe entidade com o id %d";

	public EntityNotFoundException(String mensagem) {
		super(mensagem);
	}

	public EntityNotFoundException(Long id) {
		this(String.format(MESSAGE_ID_NOT_FOUND, id));
	}

}
