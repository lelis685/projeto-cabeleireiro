package br.com.cabeleireiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cabeleireiro.domain.Cabeleireiro;

@Repository
public interface CabeleireiroRepository extends JpaRepository<Cabeleireiro, Long> {


	
	
	Cabeleireiro findByEmail(String email);

	@Modifying
	@Query("update Cabeleireiro u set u.nomeEstabelecimento = ?1, u.cnpj = ?2, "
			+ "u.endereco.rua = ?3, u.endereco.bairro = ?4,  "
			+ "u.endereco.cidade = ?5, u.endereco.cep = ?6, u.endereco.numero = ?7," + "u.endereco.complemento = ?8,u.email = ?9 "
			+ ", u.endereco.regiao = ?10, u.valorAdulto=?11,u.valorInfantil=?12  where u.id = ?13")
	void setCabeleireiroInfoById(String nomeEstabelecimento, String cnpj, String rua, String bairro, String cidade,
			String cep, Integer numero, String complemento,String email,String regiao,double valorAdulto, double valorInfantil, Long id);

	@Modifying
	@Query("update Cabeleireiro u set u.ativo = ?1  where u.id = ?2")
	void desativarCabelereiro(int ativo,Long userId);
	
	@Modifying
	@Query("update Cabeleireiro u set u.ativo = ?1  where u.email = ?2")
	void ativarCabeleireiro(int  ativo,String email);
	
	
}
