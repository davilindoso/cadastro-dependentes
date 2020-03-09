package com.company.register.cadastro.domain.exception;

public class CpfAlreadyExistsException extends EntityAlreadyExistsException {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_CPF_NOT_FOUND = "Já existe entidade com o cpf %d";

	public CpfAlreadyExistsException(String mensagem) {
		super(mensagem);
	}

	public CpfAlreadyExistsException(Long cpf) {
		this(String.format(MESSAGE_CPF_NOT_FOUND, cpf));
	}

}
