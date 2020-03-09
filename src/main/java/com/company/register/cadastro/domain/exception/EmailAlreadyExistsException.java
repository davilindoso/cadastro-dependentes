package com.company.register.cadastro.domain.exception;

public class EmailAlreadyExistsException extends EntityAlreadyExistsException {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_EMAIL_NOT_FOUND = "Já existe entidade com o email %s";

	public EmailAlreadyExistsException(String mensagem, boolean isSuper) {
		super(mensagem);
	}

	public EmailAlreadyExistsException(String email) {
		this(String.format(MESSAGE_EMAIL_NOT_FOUND, email), true);
	}

}
