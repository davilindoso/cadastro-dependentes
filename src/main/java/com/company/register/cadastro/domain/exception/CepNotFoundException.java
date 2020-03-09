package com.company.register.cadastro.domain.exception;

public class CepNotFoundException extends EntityNotFoundException {

	private final static String MENSAGEM_CEP_INVALIDO = "O cep (%d) não é válido";

	public CepNotFoundException(Long cep) {
		super(String.format(MENSAGEM_CEP_INVALIDO, cep));
	}

	private static final long serialVersionUID = 1L;

}
