package com.company.register.cadastro.domain.service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.register.cadastro.client.ViaCepClient;
import com.company.register.cadastro.domain.exception.CpfAlreadyExistsException;
import com.company.register.cadastro.domain.exception.EmailAlreadyExistsException;
import com.company.register.cadastro.domain.model.Dependente;
import com.company.register.cadastro.domain.model.ViaCepDTO;
import com.company.register.cadastro.domain.repository.DependenteRepository;
import com.company.register.cadastro.util.pdf.GeradorRelatorioDependentePdf;

@Service
public class DependenteService {

	private DependenteRepository dependenteRepository;

	private ViaCepClient viaCepClient;

	@Autowired
	public DependenteService(DependenteRepository dependenteRepositoryParam, ViaCepClient viaCepClientParam) {
		this.dependenteRepository = dependenteRepositoryParam;
		this.viaCepClient = viaCepClientParam;
	}

	public Dependente cadastrarDependente(Dependente dependente) {
		impedirDuplicidadeDependente(dependente);
		enderecoDependenteDTO(dependente);
		return dependenteRepository.save(dependente);
	}

	public List<Dependente> listarDependentes() {
		return dependenteRepository.findAll();
	}

	private Optional<ViaCepDTO> buscarEndereco(Long cep) {
		return viaCepClient.buscarEnderecoPor(cep);
	}

	public ResponseEntity<InputStreamResource> relatorioClientes() {
		List<Dependente> dependentes = listarDependentes();
		ByteArrayInputStream bis = GeradorRelatorioDependentePdf.obterRelatorioDependentes(dependentes);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=relatoriodependentes.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	private void enderecoDependenteDTO(Dependente dependente) {
		buscarEndereco(dependente.getCep()).ifPresent(enderecoCliente -> {
			dependente.getEndereco().setRua(enderecoCliente.getLogradouro());
			dependente.getEndereco().setBairro(enderecoCliente.getBairro());
			dependente.getEndereco().setCidade(enderecoCliente.getLocalidade());
			dependente.getEndereco().setUf(enderecoCliente.getUf());
		});
	}

	private void impedirDuplicidadeDependente(Dependente dependente) {
		dependenteRepository.findByCpf(dependente.getCpf()).ifPresent(dependenteCadastrado -> {
			throw new CpfAlreadyExistsException(dependenteCadastrado.getCpf());
		});

		dependenteRepository.findByEmail(dependente.getEmail()).ifPresent(dependenteCadastrado -> {
			throw new EmailAlreadyExistsException(dependenteCadastrado.getEmail());
		});
	}
}
