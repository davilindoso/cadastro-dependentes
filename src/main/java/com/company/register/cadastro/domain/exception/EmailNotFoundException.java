package com.company.register.cadastro.domain.exception;

public class EmailNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_EMAIL_NOT_FOUND = "Não existe entidade com o email %s";

	public EmailNotFoundException(String mensagem) {
		super(mensagem);
	}

	public EmailNotFoundException(Long cpf) {
		this(String.format(MESSAGE_EMAIL_NOT_FOUND, cpf));
	}

}
