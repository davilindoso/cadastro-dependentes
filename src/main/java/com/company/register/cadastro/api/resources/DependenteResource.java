package com.company.register.cadastro.api.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.register.cadastro.domain.model.Dependente;
import com.company.register.cadastro.domain.service.DependenteService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping("/dependentes")
public class DependenteResource {

	private DependenteService dependenteService;

	@Autowired
	public DependenteResource(DependenteService dependenteServiceParam) {
		this.dependenteService = dependenteServiceParam;
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Dependente cadastrarDependente(@Valid @RequestBody Dependente dependente) {
		return dependenteService.cadastrarDependente(dependente);
	}

	@GetMapping
	public List<Dependente> listarDependentes() {
		return dependenteService.listarDependentes();
	}

	@GetMapping("/relatorio")
	public ResponseEntity<InputStreamResource> obterRelatorioDependentes() {
		return dependenteService.relatorioClientes();
	}

}
