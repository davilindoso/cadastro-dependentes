package com.company.register.cadastro.domain.exception;

public class CpfNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_CPF_NOT_FOUND = "Não existe entidade com o cpf %d";

	public CpfNotFoundException(String mensagem) {
		super(mensagem);
	}

	public CpfNotFoundException(Long cpf) {
		this(String.format(MESSAGE_CPF_NOT_FOUND, cpf));
	}

}
