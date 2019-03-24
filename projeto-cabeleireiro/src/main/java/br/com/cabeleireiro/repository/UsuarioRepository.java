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
	@Query("update Usuario u set u.nome = ?1, u.sobreNome = ?2, u.dataNascimento = ?3, u.celular = ?4, u.ativo = ?5  where u.id = ?6")
	void setUsuarioInfoById(String nome, String sobreNome,LocalDate dataNascimento,String celular,int  ativo, Long userId);

}
