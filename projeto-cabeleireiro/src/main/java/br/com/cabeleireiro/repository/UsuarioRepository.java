package br.com.cabeleireiro.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cabeleireiro.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);
	
	@Modifying
	@Query("update Usuario u set u.nome = ?1, u.sobreNome = ?2, u.dataNascimento = ?3, u.celular = ?4  where u.id = ?5")
	void setUsuarioInfoById(String nome, String sobreNome,LocalDate dataNascimento,String celular, Long userId);

	@Modifying
	@Query("update Usuario u set u.ativo = ?1  where u.id = ?2")
	void desativarUsuario(int  ativo,Long userId);
	
	@Modifying
	@Query("update Usuario u set u.ativo = ?1  where u.email = ?2")
	void ativarUsuario(int  ativo,String email);
	
}
