package com.company.register.cadastro.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.company.register.cadastro.domain.model.ViaCepDTO;

@Service
@FeignClient(name = "viacepclient", url = "https://viacep.com.br/ws/")
public interface ViaCepClient {

	@GetMapping("{cep}/json")
	Optional<ViaCepDTO> buscarEnderecoPor(@PathVariable("cep") Long cep);

}
