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
import com.company.register.cadastro.domain.exception.CpfNotFoundException;
import com.company.register.cadastro.domain.exception.EmailAlreadyExistsException;
import com.company.register.cadastro.domain.exception.EmailNotFoundException;
import com.company.register.cadastro.domain.exception.EntityNotFoundException;
import com.company.register.cadastro.domain.model.Cliente;
import com.company.register.cadastro.domain.model.ViaCepDTO;
import com.company.register.cadastro.domain.repository.ClienteRepository;
import com.company.register.cadastro.util.pdf.GeradorRelatorioClientePdf;

@Service
public class ClienteService {

	ClienteRepository clienteRepository;
	private ViaCepClient viaCepClient;

	@Autowired
	public ClienteService(ClienteRepository clienteRepositoryParam, ViaCepClient viaCepClientParam) {
		this.clienteRepository = clienteRepositoryParam;
		this.viaCepClient = viaCepClientParam;
	}

	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}

	public Cliente buscarPorId(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
	}

	public Cliente buscarPorEmail(String email) {
		return clienteRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException(email));
	}

	public Cliente buscarPorCpf(Long cpf) {
		return clienteRepository.findByCpf(cpf).orElseThrow(() -> new CpfNotFoundException(cpf));
	}

	public Cliente cadastrarCliente(Cliente cliente) {
		impedirDuplicidadeCliente(cliente);
		enderecoClienteDTO(cliente);
		return clienteRepository.save(cliente);
	}

	public Cliente atualizarCadastroCliente(Cliente cliente, Long id) {
		cliente.setId(buscarPorId(id).getId());
		enderecoClienteDTO(cliente);
		return clienteRepository.save(cliente);
	}

	public void deletarCadastroCliente(Long id) {
		clienteRepository.deleteById(buscarPorId(id).getId());
	}

	private Optional<ViaCepDTO> buscarEndereco(Long cep) {
		return viaCepClient.buscarEnderecoPor(cep);
	}

	public ResponseEntity<InputStreamResource> relatorioClientes() {
		List<Cliente> clientes = listarClientes();
		ByteArrayInputStream bis = GeradorRelatorioClientePdf.obterRelatorioClientes(clientes);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=relatorioclientes.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	private void impedirDuplicidadeCliente(Cliente cliente) {
		clienteRepository.findByCpf(cliente.getCpf()).ifPresent(clienteCadastrado -> {
			throw new CpfAlreadyExistsException(clienteCadastrado.getCpf());
		});
		clienteRepository.findByEmail(cliente.getEmail()).ifPresent(clienteCadastrado -> {
			throw new EmailAlreadyExistsException(clienteCadastrado.getEmail());
		});
	}

	private void enderecoClienteDTO(Cliente cliente) {
		buscarEndereco(cliente.getCep()).ifPresent(enderecoCliente -> {
			cliente.getEndereco().setRua(enderecoCliente.getLogradouro());
			cliente.getEndereco().setBairro(enderecoCliente.getBairro());
			cliente.getEndereco().setCidade(enderecoCliente.getLocalidade());
			cliente.getEndereco().setUf(enderecoCliente.getUf());
		});
	}

}
