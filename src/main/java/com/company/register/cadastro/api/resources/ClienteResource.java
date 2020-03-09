package com.company.register.cadastro.api.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.register.cadastro.domain.model.Cliente;
import com.company.register.cadastro.domain.service.ClienteService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping("/clientes")
public class ClienteResource {

	ClienteService clienteService;

	@Autowired
	public ClienteResource(ClienteService clienteServiceParam) {
		this.clienteService = clienteServiceParam;
	}

	@GetMapping
	public List<Cliente> listarTodosClientes() {
		return clienteService.listarClientes();
	}

	@GetMapping("/{id}")
	public Cliente buscarClientePorId(@PathVariable Long id) {
		return clienteService.buscarPorId(id);
	}

	@GetMapping("/cpfs/{cpf}")
	public Cliente buscarClientePorCpf(@PathVariable Long cpf) {
		return clienteService.buscarPorCpf(cpf);
	}

	@GetMapping("/emails/{email}")
	public Cliente buscarClientePorEmail(@PathVariable String email) {
		return clienteService.buscarPorEmail(email);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cliente cadastrarCliente(@Valid @RequestBody Cliente cliente) {
		return clienteService.cadastrarCliente(cliente);
	}

	@PutMapping("/{id}")
	public Cliente atualizarCadastroCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		return clienteService.atualizarCadastroCliente(cliente, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarCadastroCliente(@PathVariable Long id) {
		clienteService.deletarCadastroCliente(id);
	}

	@GetMapping("/relatorio")
	public ResponseEntity<InputStreamResource> obterRelatorio() {
		return clienteService.relatorioClientes();
	}
}
