package com.company.register.cadastro.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.register.cadastro.domain.model.Dependente;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Long> {

	Optional<Dependente> findByCpf(Long cpf);

	Optional<Dependente> findByEmail(String email);

}
