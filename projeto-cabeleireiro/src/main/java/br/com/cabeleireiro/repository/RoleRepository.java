package br.com.cabeleireiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cabeleireiro.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByNome(String nome);

}
